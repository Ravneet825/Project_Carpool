package example.nosql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.Endpoint;

import com.cloudant.client.api.Database;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

@Path("/favorites")
/**
 * CRUD service of todo list table. It uses REST style.
 */
public class ResourceServlet {

	public ResourceServlet() {
	}

	
	@POST
	public Response create(@QueryParam("id") Long id, @FormParam("name") String name, @FormParam("value") String value)
			throws Exception {

		Database db = null;
		try {
			db = getDB();
		} catch (Exception re) {
			re.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		String idString = id == null ? null : id.toString();
		JsonObject resultObject = create(db, idString, name, value, null, null);

		System.out.println("Create Successful.");

		return Response.ok(resultObject.toString()).build();
	}
	
	

	
	@POST
	public Response createCarPool(@QueryParam("carpoolId") String carpoolId, @FormParam("userId") String userId,@FormParam("carpoolerName") String carpoolerName, @FormParam("SeatsAvailable") String seatsAvailable, @FormParam("carInfo") String carInfo, @FormParam("price") String price, @FormParam("carpoolDate") String carpoolDate, @FormParam("startCity") String startCity, @FormParam("endCity") String endCity, @FormParam("startPoint") String StartPoint, @FormParam("dropPoint") String dropPoint, @FormParam("carpoolerNumber") String carpoolerNumber,@FormParam("carpoolTime") String carpoolTime )
			throws Exception {
		System.out.println("Entered createCarPool");
		Database db = null;
		
			db = getDB();

		String idString = carpoolId == null ? null : carpoolId.toString();
		System.out.println("Created connection with  idString value :"+idString);
		JsonObject resultObject = createCar(db,null,carpoolId,userId,carpoolerName,seatsAvailable,carInfo, price, carpoolDate, startCity, endCity, StartPoint, dropPoint,carpoolerNumber,carpoolTime,null,null);

		System.out.println("Create Successful.");

		return Response.ok(resultObject.toString()).build();
	}	

	public JsonObject createCar(Database db, String id, String carpoolId, String userId,String carpoolerName, String seatsAvailable,String carInfo, String price, String carpoolDate, String startCity, String endCity, String startPoint,String dropPoint,String carpoolerNumber,String carpoolTime, Part part, String fileName)
			throws IOException {

		// check if document exist
		HashMap<String, Object> obj = (id == null) ? null : db.find(HashMap.class, id);
		if(obj == null){
		System.out.println("daaaa object is null: "+obj);
		}else{
			System.out.println("daaaa object values"+obj.values());
		}
		if (obj == null) {
			// if new document

			id = carpoolId;

			// create a new document
			System.out.println("Creating new document with id : " + id);
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("_id", id);
			data.put("userId", userId);
			data.put("carpoolerName", carpoolerName);
			data.put("carpoolId", carpoolId);
			data.put("seatsAvailable", seatsAvailable);
			data.put("carInfo", carInfo);
			data.put("price", price);
			data.put("carpoolDate", carpoolDate);
			data.put("carpoolTime", carpoolTime);
			data.put("startCity", startCity);
			data.put("endCity", endCity);
			data.put("startPoint", startPoint);
			data.put("dropPoint",dropPoint );
			data.put("carpoolerNumber",carpoolerNumber );
			data.put("creation_date", new Date().toString());
			db.save(data);

			// attach the attachment object
			//obj = db.find(HashMap.class, id);
			saveAttachment(db, id, part, fileName, data);
		} else {
			// if existing document
			// attach the attachment object
			saveAttachment(db, carpoolId, part, fileName, obj);

			// update other fields in the document
			obj = db.find(HashMap.class, carpoolId);
			obj.put("carInfo", carInfo);
			obj.put("seatsAvailable", seatsAvailable);
			db.update(obj);
		}

		obj = db.find(HashMap.class, carpoolId);

		JsonObject resultObject = toJsonObject(obj);

		return resultObject;
	}
	// for register user
	public JsonObject registerUser(Database db,String id, String userId, String name, String username, String password, String contactNo,String postalCode, Part part, String fileName)
			throws IOException {

		// check if document exist
		HashMap<String, Object> obj = (id == null) ? null : db.find(HashMap.class, userId);

		if (obj == null) {
			// if new document

			id = userId;

			// create a new document
			System.out.println("Creating new document with id : " + userId);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("userId", userId);
			data.put("_id", userId);
			data.put("name", name);
			data.put("username", username);
			data.put("password", password);
			data.put("contactNo", contactNo);
			data.put("postalCode", postalCode);
			data.put("creation_date", new Date().toString());
			db.save(data);

			// attach the attachment object
			obj = db.find(HashMap.class, userId);
			saveAttachment(db, userId, part, fileName, obj);
		} else {
			// if existing document
			// attach the attachment object
			saveAttachment(db, userId, part, fileName, obj);

			// update other fields in the document
			obj = db.find(HashMap.class, userId);
			obj.put("password", password);
			obj.put("username", username);
			db.update(obj);
		}

		obj = db.find(HashMap.class, userId);

		JsonObject resultObject = toJsonObject(obj);

		return resultObject;
	}

	
	
	
	
	
	
	public JsonObject create(Database db, String id, String name, String value, Part part, String fileName)
			throws IOException {

		// check if document exist
		HashMap<String, Object> obj = (id == null) ? null : db.find(HashMap.class, id);

		if (obj == null) {
			// if new document

			id = String.valueOf(System.currentTimeMillis());

			// create a new document
			System.out.println("Creating new document with id : " + id);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("name", name);
			data.put("_id", id);
			data.put("value", value);
			data.put("creation_date", new Date().toString());
			db.save(data);

			// attach the attachment object
			obj = db.find(HashMap.class, id);
			saveAttachment(db, id, part, fileName, obj);
		} else {
			// if existing document
			// attach the attachment object
			saveAttachment(db, id, part, fileName, obj);

			// update other fields in the document
			obj = db.find(HashMap.class, id);
			obj.put("name", name);
			obj.put("value", value);
			db.update(obj);
		}

		obj = db.find(HashMap.class, id);

		JsonObject resultObject = toJsonObject(obj);

		return resultObject;
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") Long id, String username,String password) throws Exception {

		Database db = null;
		try {
			db = getDB();
		} catch (Exception re) {
			re.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		JsonObject resultObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();

		if (id == null) {
			try {
				// get all the document present in database
				List<HashMap> allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
						.getDocsAs(HashMap.class);

				if (allDocs.size() == 0) {
					allDocs = initializeSampleData(db);
				}

				for (HashMap doc : allDocs) {
					HashMap<String, Object> obj = db.find(HashMap.class, doc.get("_id") + "");
					JsonObject jsonObject = new JsonObject();
					LinkedTreeMap<String, Object> attachments = (LinkedTreeMap<String, Object>) obj.get("_attachments");

					if (attachments != null && attachments.size() > 0) {
						JsonArray attachmentList = getAttachmentList(attachments, obj.get("_id") + "");
						jsonObject.addProperty("id", obj.get("_id") + "");
						jsonObject.addProperty("name", obj.get("name") + "");
						jsonObject.addProperty("value", obj.get("value") + "");
						jsonObject.add("attachements", attachmentList);

					} else {
						jsonObject.addProperty("id", obj.get("_id") + "");
						jsonObject.addProperty("name", obj.get("name") + "");
						jsonObject.addProperty("value", obj.get("value") + "");
					}

					jsonArray.add(jsonObject);
				}
			} catch (Exception dnfe) {
				System.out.println("Exception thrown : " + dnfe.getMessage());
			}

			resultObject.addProperty("id", "all");
			resultObject.add("body", jsonArray);

			return Response.ok(resultObject.toString()).build();
		}

		// check if document exists
		HashMap<String, Object> obj = db.find(HashMap.class, username,password);
		
		if (obj != null) {
			JsonObject jsonObject = toJsonObject(obj);
			return Response.ok(jsonObject.toString()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	

	@DELETE
	public Response delete(@QueryParam("id") long id) {

		Database db = null;
		try {
			db = getDB();
		} catch (Exception re) {
			re.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		// check if document exist
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");

		if (obj == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			db.remove(obj);

			System.out.println("Delete Successful.");

			return Response.ok("OK").build();
		}
	}

	@PUT
	public Response update(@QueryParam("id") long id, @QueryParam("name") String name,
			@QueryParam("value") String value) {

		Database db = null;
		try {
			db = getDB();
		} catch (Exception re) {
			re.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		// check if document exist
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");

		if (obj == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			obj.put("name", name);
			obj.put("value", value);

			db.update(obj);

			System.out.println("Update Successful.");

			return Response.ok("OK").build();
		}
	}

	private JsonArray getAttachmentList(LinkedTreeMap<String, Object> attachmentList, String docID) {
		JsonArray attachmentArray = new JsonArray();

		for (Object key : attachmentList.keySet()) {
			LinkedTreeMap<String, Object> attach = (LinkedTreeMap<String, Object>) attachmentList.get(key);

			JsonObject attachedObject = new JsonObject();
			// set the content type of the attachment
			attachedObject.addProperty("content_type", attach.get("content_type").toString());
			// append the document id and attachment key to the URL
			attachedObject.addProperty("url", "attach?id=" + docID + "&key=" + key);
			// set the key of the attachment
			attachedObject.addProperty("key", key + "");

			// add the attachment object to the array
			attachmentArray.add(attachedObject);
		}

		return attachmentArray;
	}

	private JsonObject toJsonObject(HashMap<String, Object> obj) {
		JsonObject jsonObject = new JsonObject();
		LinkedTreeMap<String, Object> attachments = (LinkedTreeMap<String, Object>) obj.get("_attachments");
		if (attachments != null && attachments.size() > 0) {
			JsonArray attachmentList = getAttachmentList(attachments, obj.get("_id") + "");
			jsonObject.add("attachements", attachmentList);
		}
		jsonObject.addProperty("id", obj.get("_id") + "");
		jsonObject.addProperty("name", obj.get("name") + "");
		jsonObject.addProperty("value", obj.get("value") + "");
		return jsonObject;
	}

	private void saveAttachment(Database db, String id, Part part, String fileName, HashMap<String, Object> obj)
			throws IOException {
		if (part != null) {
			InputStream inputStream = part.getInputStream();
			try {
				db.saveAttachment(inputStream, fileName, part.getContentType(), id, (String) obj.get("_rev"));
			} finally {
				inputStream.close();
			}
		}
	}

	/*
	 * Create a document and Initialize with sample data/attachments
	 */
	private List<HashMap> initializeSampleData(Database db) throws Exception {

		long id = System.currentTimeMillis();
		String name = "Sample category";
		String value = "List of sample files";

		// create a new document
		System.out.println("Creating new document with id : " + id);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", name);
		data.put("_id", id + "");
		data.put("value", value);
		data.put("creation_date", new Date().toString());
		db.save(data);

		// attach the object
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");

		// attachment#1
		File file = new File("Sample.txt");
		file.createNewFile();
		PrintWriter writer = new PrintWriter(file);
		writer.write("This is a sample file...");
		writer.flush();
		writer.close();
		FileInputStream fileInputStream = new FileInputStream(file);
		db.saveAttachment(fileInputStream, file.getName(), "text/plain", id + "", (String) obj.get("_rev"));
		fileInputStream.close();

		return db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(HashMap.class);

	}

	public Database getDB() {
		return CloudantClientMgr.getDB();
	}

}

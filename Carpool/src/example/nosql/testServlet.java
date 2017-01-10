package example.nosql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Document;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.cloudant.*;
/**
 * Servlet implementation class testServlet
 */
@WebServlet("/testServlet")

 
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private CloudantClient cldclient = null;
public String passwrds = System.getenv().get("PASSWORD");
	/**
     * @see HttpServlet#HttpServlet()
     */

	public testServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
	
		String name, value;
		new CloudantClientMgr();
	//	response.getWriter().append(new CloudantClientMgr().getDB().getDBUri().toString());
		Database db= new CloudantClientMgr().getDB();
		

		//String idString = id == null ? null : id.toString();
//		 JsonObject resultObject = new ResourceServlet().createCar(db, null,"34569", "1234", "2", "lemo", "2000", "12/10", "windsor", "windsor", "endpoint", "windsor", null, null);

	//	System.out.println("Create Successful.");
	//	Response.ok(resultObject.toString()).build();
		
		//List<Dbtest> allFoos = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Dbtest.class);
		
		  
		try {
			List<Carpool> d  = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Carpool.class);
			for(int i=0; i<d.size();i++){
			response.getWriter().append((d.get(i).getStartCity()));
			}


			} catch (Exception e) {
			// TODO Auto-generated catch block
				response.getWriter().append(e.getMessage());
			}
		

		
	/*	BufferedReader bf = new BufferedReader(new InputStreamReader(db.find("1478287535621")));
		StringBuilder str = new StringBuilder();
		String line;
		while(null!=(line= bf.readLine()))
		{
			str.append(line);
		}
		
		response.getWriter().append(str);
		
		try {
			new ResourceServlet().get(null, null);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().append(e.getMessage());
		}
		
*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		System.out.println("sadfdjskfhsdjkndfjkldsfsdkhfkjfnkhkfldskjskfdskfsdhffjsdklhfjkdhkj");
		
		new CloudantClientMgr();
		
		
		
	}

}

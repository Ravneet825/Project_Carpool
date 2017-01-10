package example.dao;

import java.util.List;

import example.domain.Register;
import com.cloudant.client.api.Database;
import com.google.gson.JsonObject;

import example.nosql.Carpool;
import example.nosql.CloudantClientMgr;
import example.nosql.Dbtest;
import example.nosql.ResourceServlet;

public class UserDao {

	
	//	response.getWriter().append(new CloudantClientMgr().getDB().getDBUri().toString());
		Database db= new CloudantClientMgr().getDB();
	
	
	public void registerNewUser(Register registerUser) {
		Register reg = new Register();
		String name, password, username,contactno,postalcode;
		name = reg.getName();
		password = reg.getPassword();
		username = reg.getUsername();
		contactno = reg.getContactNumber();
		postalcode = reg.getPostalcode();
		
		
		try {
			String idString = registerUser.getUserId() == null ? null : registerUser.getUserId();
			
			JsonObject resultObject = new ResourceServlet().registerUser(db, null, "123", name, username, password, contactno, postalcode, null,null);

			List<Carpool> d  = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Carpool.class);
			
			for(int i=0; i<d.size();i++){
				
				
			//response.getWriter().append((d.get(i).getName()));
			}


			} catch (Exception e) {
			// TODO Auto-generated catch block
				//response.getWriter().append(e.getMessage());
			}
		
		
	}
	
	public Register loginUser()
	{
		Register r = new Register();
		
		return r;
		
	}

	
}

package example.nosql;

import com.cloudant.client.api.Database;

public class DataInitialization {
	
	public DataInitialization() {
		
		new CloudantClientMgr();
		//	response.getWriter().append(new CloudantClientMgr().getDB().getDBUri().toString());
			Database db= new CloudantClientMgr().getDB();
		
	}
}

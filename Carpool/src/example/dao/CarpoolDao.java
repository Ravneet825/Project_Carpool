package example.dao;

import java.util.ArrayList;
import java.util.List;

import com.cloudant.client.api.Database;

import example.domain.Carpool;
import example.nosql.CloudantClientMgr;
import example.nosql.ResourceServlet;

public class CarpoolDao {

	public void addCarpool(Carpool carpool) {

		Database db = null;
		try {
			db = CloudantClientMgr.getDB();

			ResourceServlet servlet = new ResourceServlet();
			String idString = (carpool.getUserId() == null && carpool.getCarpoolerNumber() == null) ? null : (carpool.getUserId()+"_"+carpool.getCarpoolerNumber()+"_"+System.currentTimeMillis());
			System.out.println("id: "+idString);
			carpool.setCarpoolId(idString);
			System.out.println("In dao carpol date: "+carpool.getCarpoolDate());
			javax.ws.rs.core.Response response = servlet.createCarPool(carpool.getCarpoolId(),carpool.getUserId(), carpool.getCarpoolerName(), carpool.getSeatsAvailable(), carpool.getCarInfo(), carpool.getPrice(), carpool.getCarpoolDate(), carpool.getStartCity(), carpool.getEndCity(), carpool.getStartPoint(), carpool.getDropPoint(), carpool.getCarpoolerNumber(),carpool.getCarpoolTime());
			System.out.println("Upload completed."+response.getStatus());		

		} catch (Exception re) {
			re.printStackTrace();
			return;
		}

	}

	public ArrayList<Carpool> searchCarpool(Carpool carpool) {
		Database db = null;
		ArrayList<Carpool> carpools= null;
		try {
			db = CloudantClientMgr.getDB();

			ResourceServlet servlet = new ResourceServlet();
			List<Carpool> d  = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Carpool.class);

				System.out.println("carpool list:"+d);
				if(d != null && d.size() > 0 ){
					System.out.println("carpool list:"+d.size());
					carpools= new ArrayList<Carpool>();
				}
			
			for(int i=0; i<d.size();i++){

				if( (d.get(i).getCarpoolDate()).equals(carpool.getCarpoolDate())){
					carpools.add(d.get(i));
				}else if( carpool.getStartPoint() != null && (carpool.getStartPoint()).equals(d.get(i).getStartPoint()) ){
					carpools.add(d.get(i));
				}else if( carpool.getSeatsAvailable() != null && (carpool.getSeatsAvailable()).equals(d.get(i).getSeatsAvailable()) ){
					carpools.add(d.get(i));
				}else if( carpool.getEndCity() != null && (carpool.getEndCity()).equals(d.get(i).getEndCity()) ){
					carpools.add(d.get(i));
				}

			}
			if(carpools != null){
				System.out.println("search list size"+carpools.size());
			}

		} catch (Exception re) {
			re.printStackTrace();

		}finally{
			return carpools;
		}



	}
}

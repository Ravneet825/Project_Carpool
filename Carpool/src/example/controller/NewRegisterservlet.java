package example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Request;

import com.cloudant.client.api.Database;
import com.google.gson.JsonObject;

import example.domain.Register;
import example.nosql.Carpool;
import example.nosql.CloudantClientMgr;
import example.nosql.ResourceServlet;

/**
 * Servlet implementation class NewRegisterservlet
 */
@WebServlet("/NewRegisterservlet")
public class NewRegisterservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Database db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRegisterservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username=  request.getParameter("username");
		String name=  request.getParameter("name");
		String email = request.getParameter("email");
		String password=  request.getParameter("password");
		String contactNo = request.getParameter("contactNo");
		String postalCode=  request.getParameter("postalCode");
		 System.out.println("username"+username+"pasword"+password+"email"+email+"contactNo"+contactNo+"postalCode"+postalCode);
		 Register registerUser = new Register();
		 if(username != null && name !=null && email != null && password != null && contactNo !=null && postalCode !=null){
			 registerUser.setName(name);
			 registerUser.setEmail(email);
			 registerUser.setContactNumber(contactNo);
			 registerUser.setPassword(password);
			 registerUser.setPostalcode(postalCode);
			 

				try {
					db = new CloudantClientMgr().getDB();
					String idString = registerUser.getUserId() == null ? null : registerUser.getUserId();
					
					JsonObject resultObject = new ResourceServlet().registerUser(db, null, username, name, username, password, contactNo, postalCode, null,null);
				//	JsonObject resultObject = new ResourceServlet().createCar(db, null,"34569", "1234", "2", "lemo", "2000", "12/10", "windsor", "windsor", "endpoint", "windsor", null, null);
				

					} catch (Exception e) {
					// TODO Auto-generated catch block
						response.getWriter().append(e.getMessage());
						response.getWriter().append("username"+username+"pasword"+password+"email"+email+"contactNo"+contactNo+"postalCode"+postalCode);
					}
			 
			// service.registerNewUser(registerUser);
		 }
		
		 response.addHeader("message", "registration successfully");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

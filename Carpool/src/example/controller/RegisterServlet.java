package example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.domain.Register;
import example.service.Service;

public class RegisterServlet extends HttpServlet {

	Service service = new Service();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,
		       HttpServletResponse res) throws IOException
		{
			String username=  req.getParameter("username");
			String name=  req.getParameter("name");
			String email = req.getParameter("email");
			String password=  req.getParameter("password");
			String contactNo = req.getParameter("contactNo");
			String postalCode=  req.getParameter("postalCode");
			 System.out.println("username"+username+"pasword"+password+"email"+email+"contactNo"+contactNo+"postalCode"+postalCode);
			 Register registerUser = new Register();
			 if(username != null && name !=null && email != null && password != null && contactNo !=null && postalCode !=null){
				 registerUser.setName(name);
				 registerUser.setEmail(email);
				 registerUser.setContactNumber(contactNo);
				 registerUser.setPassword(password);
				 registerUser.setPostalcode(postalCode);
				 service.registerNewUser(registerUser);
			 }

		}
}
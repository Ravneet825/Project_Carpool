package example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	
	

	public void service(HttpServletRequest req,
		       HttpServletResponse res) throws IOException
		{
			String username=  req.getParameter("username");
			String password = req.getParameter("password");
			 System.out.println("username"+username);
			 System.out.println("pasword"+password);
			 
			 
			 
			 HttpSession session = req.getSession();
			 session.setAttribute("user", username);
			 
			
		}
}

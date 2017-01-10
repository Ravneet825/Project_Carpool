package example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloudant.client.api.Database;

import example.nosql.Carpool;
import example.nosql.CloudantClientMgr;

/**
 * Servlet implementation class NewLoginController
 */
@WebServlet("/NewLoginController")
public class NewLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Database db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username, password;
		username = request.getParameter("username");
		password = request.getParameter("password");
		
		try {
			db = new CloudantClientMgr().getDB();
			Carpool pool  = db.find(Carpool.class, username);
			if(!(pool.getUsername().equals("")))
			{
			//for(int i=0; i<d.size();i++){
			//response.getWriter().append((d.get(i).getStartCity()));
				if(username.equals(pool.getUsername()) && password.equals(pool.getPassword()))
				{
					System.out.println("login successfull");
					
					HttpSession session = request.getSession();
					session.setAttribute("session_id", pool.getUsername());
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					
					
				}
				else
			{
				//	System.out.println(d.get(i).getPassword());
					response.addHeader("message", "invalid username or password");
					request.getRequestDispatcher("/login.jsp?msg=invalid username or password").forward(request, response);
				}
			}
			else if(pool.getUsername().equals(null))
			{
				request.getRequestDispatcher("/login.jsp?msg="+username+" not registered as a user").forward(request, response);
				
			}


			} catch (Exception e) {
			// TODO Auto-generated catch block
				response.getWriter().append(e.getMessage());
				request.getRequestDispatcher("/login.jsp?msg="+username+" not registered as a user").forward(request, response);
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

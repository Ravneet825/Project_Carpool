package example.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.domain.Carpool;
import example.service.Service;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(description = "for sarching yhe carpool", urlPatterns = { "/SearchRide" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Service service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        service = new Service();
      }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String startPoint = request.getParameter("startPoint");
		String endCity =  request.getParameter("endCity");
		String carpoolDate =  request.getParameter("carpoolDate");
		String seatsAvailable =  request.getParameter("seatsAvailable");
		Carpool searchCarpool= new Carpool();
		if(startPoint != null && !startPoint.equals("From")){
			searchCarpool.setStartPoint(startPoint);
		}
		if(endCity != null && !endCity.equals("To")){
			searchCarpool.setEndCity(endCity);
		}
		if(carpoolDate != null ){
			searchCarpool.setCarpoolDate(carpoolDate);
		}
		if(seatsAvailable != null && !seatsAvailable.equals("Number of seats")){
			searchCarpool.setSeatsAvailable(seatsAvailable);
		}
		ArrayList<Carpool> carpools = service.searchCarpool(searchCarpool);
		response.addHeader("message", "carpool added successfully");
		request.setAttribute("CarpoolsList", carpools);
		request.getRequestDispatcher("/rides.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

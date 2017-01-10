package example.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.domain.Carpool;
import example.service.Service;

/**
 * Servlet implementation class Carpool
 */
@WebServlet("/Carpool")
public class CarpoolServlet extends HttpServlet {
	Service service;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarpoolServlet() {
        super();
        service = new Service();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Carpool carpool = new Carpool();
		
		String carpoolId=  request.getParameter("carpoolId");
		String carpolerName = request.getParameter("carpoolerName");
		String userId = request.getParameter("userId");
		String carInfo = request.getParameter("carInfo");
		String carpooldate = request.getParameter("carpoolDate");
		String startCity = request.getParameter("startCity");
		String endCity = request.getParameter("endCity");
		String startPoint = request.getParameter("startPoint");
		String price = request.getParameter("price");
		String dropPoint ="bus stop";
		String carpoolerNumber =request.getParameter("carpoolerNumber");
		String carpoolTime = request.getParameter("carpoolTime");
		String seatsAvailable= request.getParameter("seatsAvailable");
		
		carpool.setCarpoolDate(carpooldate);
		carpool.setCarpoolerName(carpolerName);
		carpool.setStartPoint(startPoint);
		carpool.setEndCity(endCity);
		carpool.setPrice(price);
		carpool.setCarInfo(carInfo);
		carpool.setUserId(userId);
		carpool.setCarpoolId(carpoolId);
		carpool.setDropPoint(dropPoint);
		carpool.setStartCity(startCity);
		carpool.setCarpoolerNumber(carpoolerNumber);
		carpool.setCarpoolTime(carpoolTime);
		carpool.setSeatsAvailable(seatsAvailable);
		service.addCarpool(carpool);
		 System.out.println("carpooldate"+carpooldate);
/*		System.out.println("carpoolid"+carpoolId);
		 System.out.println("carpolerName"+carpolerName);
		 System.out.println("userId"+userId);
		 System.out.println("carInfo"+carInfo); 
		 System.out.println("carpooldate"+carpooldate);
		 System.out.println("startCity"+startCity);
		 System.out.println("endCity"+endCity);
		 System.out.println("startPoint"+startPoint);*/
		response.addHeader("message", "carpool added successfully");
		request.getRequestDispatcher("/add-ride.jsp").forward(request, response);
		//response.getWriter().append("Served at: carpool added successfully ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carpolerName = request.getParameter("carpoolerName");
		String userId = request.getParameter("userId");
		String carInfo = request.getParameter("carInfo");
		String carpooldate = request.getParameter("carpoolDate");
		
		System.out.println("carpolerName"+carpolerName);
		 System.out.println("userId"+userId);
		 System.out.println("carInfo"+carInfo);
		 System.out.println("carpooldate"+carpooldate);
		// TODO Auto-generated method stu
		doGet(request, response);
		
	}

}

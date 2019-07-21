package parkingManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/stuFacHomePageController")
public class StuFacHomePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.setAttribute("parkingspots", null);	
		session.setAttribute("selectedcart", null);	
		session.setAttribute("selectedcamera", null);	
		session.setAttribute("selectedhistory", null);	
		session.setAttribute("totalcost", null);
		session.setAttribute("parkingArea",null);
		session.setAttribute("reservationfromtime",null);
		session.setAttribute("reservationtotime",null);
		session.setAttribute("availabilitymap", null);			
				
		getServletContext().getRequestDispatcher("/student_faculty.jsp").forward(request, response);
	}
}

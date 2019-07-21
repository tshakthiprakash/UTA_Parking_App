package parkingManagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parkingManagement.data.*;
import parkingManagement.model.*;
import javax.servlet.http.HttpSession;
@WebServlet("/ReservedSpotsController")
public class ReservedSpotsController extends HttpServlet {
	String url="";
	String errMsg="";
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		ReservedSpotsDao reservedspotsdb = new ReservedSpotsDao();
		List<ReservedSpots> reservedspots = new ArrayList<ReservedSpots>();
		List<ReservedSpots> reserved = new ArrayList<ReservedSpots>();
		User temp = (User)session.getAttribute("user_info");
		
		
		if(action.equals("getreservationsforcancellation")){
			reserved = reservedspotsdb.getReservationsForCancellation(temp.getUsername());
			session.setAttribute("reservationsforcancellationlist", reserved);
			getServletContext().getRequestDispatcher("/cancelmyreservation.jsp").forward(request, response);
			
		} if(action.equals("getreservationsforview")) {
			reservedspots = reservedspotsdb.viewReservedSpots(temp.getUsername());
			session.setAttribute("reservedspotlist", reservedspots);
			getServletContext().getRequestDispatcher("/view_my_reservedspots.jsp").forward(request, response);
			
		} if(action.equals("SearchByUserName")) {
			
			UserErrorMsgs errMsgs = new UserErrorMsgs();
			LoginUserDao regDb = new LoginUserDao();
			session.removeAttribute("errorMessage");
			session.removeAttribute("incorrectpass");
			User dbuser = new User();
			session.setAttribute("username", request.getParameter("search_username"));
			
			if((request.getParameter("search_username").equals("")))
			{
				url ="/deleteReservation.jsp";
				errMsg = "Please enter the Username";
				errMsgs.setUsernameError(errMsg);
				session.setAttribute("errorMessage", errMsgs);
				session.setAttribute("reservationsforcancellationlist", new ArrayList<ReservedSpots>());
				getServletContext().getRequestDispatcher(url).forward(request, response);
			} else {
				String userName = request.getParameter("search_username");
				dbuser = regDb.searchUser(userName);
				if("".equals(dbuser.getUsername())) {
					url ="/deleteReservation.jsp";
					errMsg = "User name is not in the system";
					errMsgs.setUsernameError(errMsg);
					session.setAttribute("errorMessage", errMsgs);
					session.setAttribute("reservationsforcancellationlist", new ArrayList<ReservedSpots>());
					getServletContext().getRequestDispatcher(url).forward(request, response);
				} else {					
					reserved = reservedspotsdb.getReservationsForCancellation(userName);
					if(!reserved.isEmpty())
						session.setAttribute("reservationsforcancellationlist", reserved);
					else
						session.setAttribute("reservationsforcancellationlist", "none");
					getServletContext().getRequestDispatcher("/deleteReservation.jsp").forward(request, response);		
				}
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("in post!!");
		String action = request.getParameter("action");
		System.out.println("in post!!");
		String cancelmyreservation = request.getParameter("cancelmyreservation");
		System.out.println("in post!!");
		String modifymyreservation = request.getParameter("modifymyreservation");
		System.out.println("in post!!");
		ReservedSpotsDao reservedspotsdb = new ReservedSpotsDao();
		if(action.equals("deletereservation")) {
			int reservationId = Integer.parseInt(request.getParameter("reservationid"));
			reservedspotsdb.deleteReservation(reservationId);
			String username = request.getParameter("search_username");
			response.sendRedirect("ReservedSpotsController?action=SearchByUserName&search_username="+username);
			
		}
		if(/*cancelmyreservation != null && */action.equals("Delete")) {
			int reservationId = Integer.parseInt(request.getParameter("reservationid"));
			reservedspotsdb.deleteReservation(reservationId);
			response.sendRedirect("ReservedSpotsController?action=getreservationsforcancellation");
			
		}
		
		if(/*modifymyreservation!=null && */action.equals("Modify")) {
			
			HttpSession session = request.getSession();
			String reservationid = request.getParameter("reservationid");
			session.setAttribute("reservationid", reservationid);
			response.sendRedirect("parkingspotController?action=searchparkingspotload&reservationid="+reservationid);
		}
		
		if(action.equals("modifyreservation")) {
			HttpSession session = request.getSession();
			LoginUserDao userDao = new LoginUserDao();
			User dbuser = userDao.searchUser(request.getParameter("username"));
			String reservationid = request.getParameter("reservationid");
			String username = request.getParameter("username");
			session.setAttribute("reservationid", reservationid);
			session.setAttribute("user_info", dbuser);
			session.setAttribute("username", username);
			response.sendRedirect("parkingspotController?action=searchparkingspotload&reservationid="+reservationid+"&username="+username);
			
		}
	}
}
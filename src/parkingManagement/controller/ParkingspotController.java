package parkingManagement.controller;

import parkingManagement.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parkingManagement.data.ParkingspotDao;
import parkingManagement.data.ReservationDao;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/parkingspotController")
public class ParkingspotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User login_user = (User) session.getAttribute("user_info");
		
		session.setAttribute("parkingspots", null);	
		String action = request.getParameter("action");
		
		ParkingspotDao parkingSpotDao = new ParkingspotDao();
		ReservationDao reservationDao = new ReservationDao();
		
		ParkingArea parkingarea = new ParkingArea();
		Reservation reservation = new Reservation();
//		List parkingspots
		if (action.equalsIgnoreCase("searchparkingspot")) {
			String[] cart = request.getParameterValues("selectedcart");
			String[] camera = request.getParameterValues("selectedcamera"); 
			String[] history = request.getParameterValues("selectedhistory");	

			String selectedCart ="unchecked";
			String selectedoptions = "";
		    if(cart !=null){//If checkbox is checked then assign it with true or 1       
		    	selectedCart="checked";  
		    	selectedoptions = selectedoptions + "Cart, ";
		    }

		    String selectedCamera ="unchecked";
		    if(camera !=null){//If checkbox is checked then assign it with true or 1       
		    	selectedCamera="checked";  
		    	selectedoptions = selectedoptions + "Camera, ";
		    }

		    String selectedHistory ="unchecked";
		    if(history !=null){//If checkbox is checked then assign it with true or 1       
		    	selectedHistory="checked";  
		    	selectedoptions = selectedoptions + "History";
		    }
		 
		    session.setAttribute("selectedoptions", selectedoptions);
			parkingarea.setParkingarea_name(request.getParameter("parkingarea"));
			parkingarea.setParkingtype(request.getParameter("parkingtype"));
			
			java.util.Date utilDate = new java.util.Date();
			Date today = new Date(utilDate.getTime());
			
			Time from = null;
			Time to = null;
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			try {
				from = new Time(formatter.parse(request.getParameter("reservationfrom")).getTime());
				to = new Time(formatter.parse(request.getParameter("reservationto")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String rawFrom = request.getParameter("reservationfrom");
			String rawTo = request.getParameter("reservationto");
			
			ParkingAreaErrorMsgs errorMsgs = new ParkingAreaErrorMsgs();
			parkingarea.ValidateSearchParkingSpot(errorMsgs);
			ReservationErrorMsgs reservationErrorMsgs = new ReservationErrorMsgs();
			reservation.ValidateSearchParkingSpotTimings(reservationErrorMsgs, rawFrom, rawTo);
			session.setAttribute("parkingArea",parkingarea);
			session.setAttribute("reservationfromtime",rawFrom);
			session.setAttribute("reservationtotime",rawTo);
			session.setAttribute("searchParkingErrorMsgs",errorMsgs);
			session.setAttribute("reservationErrorMsgs", reservationErrorMsgs);	
			
if (errorMsgs.getErrormsg().equals("") && reservationErrorMsgs.getErrormsg().equals("")) {
				
				ArrayList<ParkingArea> parkingAreaList = new ArrayList<ParkingArea>();
				ArrayList<Integer> parkingAreaIdList = new ArrayList<Integer>();
				parkingAreaList=parkingSpotDao.getParkingAreaList(parkingarea);
				for(ParkingArea pa : parkingAreaList){
					parkingAreaIdList.add(pa.getParkingarea_id());
				}
				Map<Integer, Integer> parkingsReservedcountMap = new HashMap<Integer, Integer>();
				Map<Integer, Integer> parkingsUnavailablecountMap = new HashMap<Integer, Integer>();
				int reservationsCount = 0;
				parkingsReservedcountMap = reservationDao.getParkingAreaCountList(parkingAreaIdList, from, to, today);
				String usernameToGetReservationsCount = login_user.getUsername();
				reservationsCount = reservationDao.getReservationCount(usernameToGetReservationsCount);

				
				//Reservation reservation = new Reservation();
				String usernameToValidateReservationsCount = login_user.getUsername();
				
				reservation.validateReservedCount(reservationErrorMsgs, usernameToValidateReservationsCount);
				parkingsUnavailablecountMap = parkingSpotDao.getUnAvailableParkingsCountList(parkingAreaIdList);

				Map<Integer, Integer> availabilitycountMap = new HashMap<Integer, Integer>();
				int reserved = 0;
				int unavailable = 0;
				for(ParkingArea pa : parkingAreaList){
						reserved = parkingsReservedcountMap.get(pa.getParkingarea_id());
						unavailable = parkingsUnavailablecountMap.get(pa.getParkingarea_id());
					availabilitycountMap.put(pa.getParkingarea_id(), (Integer.parseInt(pa.getCapacity())-reserved-unavailable));
				}
				session.setAttribute("parkingspots", parkingAreaList);
				session.setAttribute("totalcost", request.getParameter("totalcost"));
				System.out.println("total cost : "+request.getParameter("totalcost"));
				session.setAttribute("selectedcart", selectedCart);
				session.setAttribute("selectedcamera", selectedCamera);
				session.setAttribute("selectedhistory", selectedHistory);
				session.setAttribute("availabilitymap", availabilitycountMap);	
				session.setAttribute("reservationId", request.getParameter("reservationid"));
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("reservationsCount", reservationsCount);
				
				System.out.println("reservation is reserved spot contr : "+request.getParameter("reservationid"));
				
			}
			getServletContext().getRequestDispatcher("/searchparkingspot.jsp").forward(request, response);
		}
		
		if(action.equals("modifyparkingarea")){
			ParkingspotDao parkDao = new ParkingspotDao();
			ArrayList<String> parkingareanames = new ArrayList<String>();
			parkingareanames = parkDao.getparkingareaname();
			session.removeAttribute("updateparkingerror");
			session.setAttribute("successmode", 4);
			session.removeAttribute("addparkError");
			session.setAttribute("modifyparkingAreaNames", parkingareanames);
			session.setAttribute("onloads", 0);
			session.removeAttribute("parkinfo");
			response.sendRedirect("parkingarea.jsp");
			
		}
		if(action.equals("searchparkingspotload")){
			ParkingspotDao parkDao = new ParkingspotDao();
			ArrayList<String> parkingareanames = new ArrayList<String>();
			parkingareanames = parkDao.getparkingareaname();
			session.setAttribute("searchparkingnames", parkingareanames);
			System.out.println("reservationId is : "+session.getAttribute("reservationid"));
			System.out.println("reservationId is from param : "+request.getParameter("reservationid"));
			session.setAttribute("reservationid", session.getAttribute("reservationid"));
			session.setAttribute("onloads", 0);
			session.removeAttribute("parkinfo");
			response.sendRedirect("searchparkingspot.jsp");
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ParkingspotDao parkDao = new ParkingspotDao();
		ParkingArea newaddpark = new ParkingArea();
		
		HttpSession session = request.getSession();
		if(action.equals("addparking"))
		{
			newaddpark.setParkingarea_name(request.getParameter("park_name"));
			newaddpark.setFloor(request.getParameter("park_floor"));
			newaddpark.setCapacity(request.getParameter("park_cap"));
			newaddpark.setParkingtype(request.getParameter("park_type"));
			ParkingAreaErrorMsgs addParkErro = new ParkingAreaErrorMsgs();
			newaddpark.validateNewParkingArea(newaddpark,addParkErro, request.getParameter("park_cap"), request.getParameter("park_floor"));
			if(addParkErro.getErrormsg().equals(""))
			{	
				session.setAttribute("successmode", 1);
				session.removeAttribute("mode");
				parkDao.addParkingArea(newaddpark);
				response.sendRedirect("parkingspotController?action=modifyparkingarea");
			}
			else{
				session.setAttribute("successmode", 0);
				session.setAttribute("addparkError", addParkErro);
				session.setAttribute("mode", "addpark");
				response.sendRedirect("parkingarea.jsp");
				}
		}
		if(action.equals("modifyparkingareas"))
		{
			
			String parkname = request.getParameter("parkingareaname");
			ArrayList<ParkingArea> parklist = new ArrayList<ParkingArea>();
			parklist = parkDao.fetch_parking_details(parkname);
			session.setAttribute("parkinfo",parklist);
			session.setAttribute("onloads", 1);
			session.removeAttribute("mode");
			response.sendRedirect("parkingarea.jsp");
		}
		if(action.equals("updateparkingarea"))
		{
			ParkingArea parkarea = new ParkingArea();
			parkarea.setParkingarea_name(request.getParameter("parkingname"));
			parkarea.setFloor(request.getParameter("floor"));
			parkarea.setCapacity(request.getParameter("capacity"));
			parkarea.setParkingtype(request.getParameter("type"));
			String updateError="";
			updateError = parkarea.validateCapacity(parkarea.getCapacity()+"");
			if(updateError.equals(""))
			{
				session.setAttribute("successmode", 2);
				session.removeAttribute("mode");
				session.removeAttribute("updateparkingerror");
				System.out.println("true update");
				parkDao.updateParkarea(parkarea);
				response.sendRedirect("parkingspotController?action=modifyparkingarea");
			}
			else{
				session.setAttribute("successmode", 0);
				session.setAttribute("updateparkingerror", updateError);
				session.setAttribute("mode", "updatepark");
				response.sendRedirect("parkingarea.jsp");
			}
		}
		if(action.equals("changename"))
		{
			String ChangeNameError = "";
			String oldname = request.getParameter("oldname");
			String newname = request.getParameter("newname");
			ParkingArea changepark = new ParkingArea();
			ParkingAreaErrorMsgs changeNameErr = new ParkingAreaErrorMsgs();
			ChangeNameError = changepark.validateParkingNameforChangename(newname,changeNameErr);
			if(ChangeNameError.equals("")){
				session.removeAttribute("mode");
				session.setAttribute("successmode", 3);
				parkDao.updateName(oldname, newname);
				response.sendRedirect("parkingspotController?action=modifyparkingarea");
			}
			else
			{
				session.setAttribute("successmode", 0);
				session.setAttribute("changenameerror", ChangeNameError);
				session.setAttribute("mode", "changename");
				response.sendRedirect("parkingarea.jsp");
				
			}
		}
	}
	
}

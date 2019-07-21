package parkingManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import parkingManagement.model.*;

import parkingManagement.data.ParkingspotDao;

@WebServlet("/viewAvailSpotController")
public class ViewAvailSpotController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		ParkingspotDao parkDao = new ParkingspotDao();
		HttpSession session = request.getSession();
		if(action.equals("numberavailable")){
		ArrayList<String> parkingareanames = new ArrayList<String>();
		parkingareanames = parkDao.getparkingareaname();
		
		session.removeAttribute("avail_spots");
		session.removeAttribute("unavailable_list");
		session.setAttribute("parkingAreaNames", parkingareanames);
		response.sendRedirect("view_avail_spot.jsp");
		}
		if(action.equals("spotdetails"))
		{
			session.setAttribute("displayspotdeatil", 0);
			session.removeAttribute("noreservationmsg");
			session.setAttribute("hidereservationlist", 0);
			session.removeAttribute("spotdetailserror");
			ArrayList<String> parkingareanames = new ArrayList<String>();
			parkingareanames = parkDao.getparkingareaname();
			session.setAttribute("parkingAreaNames", parkingareanames);
			session.removeAttribute("spotdetailslist");
			response.sendRedirect("viewspotdetails.jsp");	
			
		}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action");
		ParkingspotDao parkDao = new ParkingspotDao();
		HttpSession session= request.getSession();
		
		if(action.equals("noavailspots")){
		String parkingareaname = request.getParameter("parkingareaname");
		String fromTime = request.getParameter("fromtime");
		String toTime = request.getParameter("totime");
		String permit_type=request.getParameter("permit_type");
		UnavailableSpotErrorMsgs noavailableerror = new UnavailableSpotErrorMsgs();
		UnavailableSpot unavail = new UnavailableSpot();
		unavail.ValidateAvailSpot(fromTime, toTime, noavailableerror);
		if(noavailableerror.getErrorMsg().equals(""))
		{
			int avilable_spots=parkDao.number_avail_spot(parkingareaname, fromTime, toTime,permit_type);
			session.removeAttribute("noavailerror");
			session.setAttribute("avail_spots",avilable_spots );
			response.sendRedirect("view_avail_spot.jsp");
		}
		else
		{
			session.setAttribute("modes", "noavail");
			session.setAttribute("noavailerror", noavailableerror);
			response.sendRedirect("view_avail_spot.jsp");
		}
		
		}
		
		if(action.equals("makeunavailable"))
		{
			UnavailableSpotErrorMsgs unavailspotError = new UnavailableSpotErrorMsgs();
			UnavailableSpot unavailspot = new UnavailableSpot();
			unavailspot.setParkingName(request.getParameter("parkingareaname"));
			unavailspot.setType(request.getParameter("type"));
			unavailspot.setSpot_no(request.getParameter("spotno"));
			unavailspot.ValidateSpot(unavailspot,unavailspotError);
			if(unavailspotError.getspotNumErrMsg().equals(""))
			{
				parkDao.setParkspotunavail(unavailspot);
				session.removeAttribute("makespotunavailerror");
				response.sendRedirect("viewAvailSpotController?action=numberavailable");
			}
			else
			{
				session.setAttribute("modes", "makeunavail");
				session.setAttribute("makespotunavailerror", unavailspotError);
				response.sendRedirect("view_avail_spot.jsp");
			}
		}
		
		if(action.equals("searchspotdetails"))
		{
		int floor;
		UnavailableSpot unavail = new UnavailableSpot();
		ArrayList<Reservation> reservlist = new ArrayList<Reservation>();
		String parkname = request.getParameter("parkingareaname");
		String type = request.getParameter("type");
		String spotdetailError = unavail.validateSpotnofordetails(request.getParameter("spotno"));
		if(spotdetailError.equals(""))
		{
			int spotno = Integer.parseInt(request.getParameter("spotno"));
			floor = parkDao.fetch_floor_details(parkname, type);
			System.out.println(floor);
			session.setAttribute("floor", floor);
			session.setAttribute("parknamedetail", parkname);
			session.setAttribute("typedetail",type);
			if((parkDao.check_spot_avail(parkname, type, spotno)) == 1)
				{
				session.setAttribute("displayspotdeatil", 1);
				session.removeAttribute("spotdetailserror");
				session.setAttribute("isavailable", 1);
				response.sendRedirect("viewspotdetails.jsp");
				}
				else
				{
					session.setAttribute("displayspotdeatil", 1);
					reservlist = parkDao.fetch_reservation_details(parkname, type, spotno);
					if(reservlist.isEmpty()){
						session.setAttribute("noreservationmsg", "There is no reservation on this spot today");
						session.setAttribute("hidereservationlist", 1);
						session.setAttribute("isavailable", 0);
						response.sendRedirect("viewspotdetails.jsp");
					}
					else{
						session.removeAttribute("noreservationmsg");
						session.setAttribute("spotdetailslist", reservlist);
						session.setAttribute("hidereservationlist", 0);
						session.setAttribute("isavailable", 0);
						response.sendRedirect("viewspotdetails.jsp");
					}
				}
		}
		else{
			session.setAttribute("spotdetailserror", spotdetailError);
			response.sendRedirect("viewspotdetails.jsp");
			
		}
	}
}

}

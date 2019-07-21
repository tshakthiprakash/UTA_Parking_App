package parkingManagement.controller;

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

//import com.mysql.cj.util.StringUtils;

import com.mysql.jdbc.StringUtils;

import parkingManagement.data.ReservationDao;
import parkingManagement.model.Reservation;
import parkingManagement.model.User;

@WebServlet("/reserveParkingspotController")
public class ReserveParkingspotController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	String url="";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReservationDao reservationDao = new ReservationDao();
		Reservation reservation = new Reservation();
		HttpSession session = request.getSession();
		User sessionUser =  (User) session.getAttribute("user_info");
		
		java.util.Date utilDate = new java.util.Date();
		Date today = new Date(utilDate.getTime());
		
		Time from = null;
		Time to = null;
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		try {
			from = new Time(formatter.parse(request.getParameter("reservationfromtime")).getTime());
			to = new Time(formatter.parse(request.getParameter("reservationtotime")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("confirmreservation") != null){
		
			String selectedoptions = "";
		    
		    session.setAttribute("parkingareaname", request.getParameter("parkingareaname"));
			session.setAttribute("selectedoptions", selectedoptions);
			session.setAttribute("parkingtype", request.getParameter("parkingtype"));
			session.setAttribute("parkingareafloor", request.getParameter("parkingareafloor"));
			session.setAttribute("reservationfromtime", request.getParameter("reservationfromtime"));
			session.setAttribute("reservationtotime", request.getParameter("reservationtotime"));
			session.setAttribute("reservationid", request.getParameter("reservationid"));
			session.setAttribute("username", request.getParameter("username"));
			
			if(request.getParameter("username")=="")
				reservation.setUsername(sessionUser.getUsername());
			else
				reservation.setUsername(request.getParameter("username"));
			reservation.setParkingarea_id(Integer.parseInt(request.getParameter("parkingareaid")));
			reservation.setReservation_id(-1);
			
			System.out.println("reserve parking spot dfasd contr : "+request.getParameter("reservationid"));
			reservation.setCart(false);
			reservation.setCamera(false);
			reservation.setHistory(false);
			reservation.setFrom_time(from);
			reservation.setTo_time(to);
			reservation.setReservation_date(today);
				
			reservationDao.reserveParkingSlot(reservation);
			getServletContext().getRequestDispatcher("/reservationconfirmed.jsp").forward(request, response);
		}
		if(request.getParameter("makepayment") != null){
			
			boolean selectedCart =false;
			String selectedoptions = "";
		    	selectedCart=true;  
		    	selectedoptions = selectedoptions + "Cart, ";
		    
		    boolean selectedCamera =false;
		    	selectedCamera=true;  
		    	selectedoptions = selectedoptions + "Camera, ";
		    
		    boolean selectedHistory =false;
		    	selectedHistory=true;  
		    	selectedoptions = selectedoptions + "History";
		    System.out.println("Selected options are : "+selectedoptions);
		    session.setAttribute("parkingareaname", request.getParameter("parkingareaname"));
			session.setAttribute("selectedoptions", selectedoptions);
			session.setAttribute("parkingtype", request.getParameter("parkingtype"));
			session.setAttribute("parkingareafloor", request.getParameter("parkingareafloor"));
			session.setAttribute("reservationfromtime", request.getParameter("reservationfromtime"));
			session.setAttribute("reservationtotime", request.getParameter("reservationtotime"));
			session.setAttribute("reservationid", request.getParameter("reservationid"));
			session.setAttribute("username", request.getParameter("username"));
			session.setAttribute("totalcost", request.getParameter("totalcost"));
			System.out.println("reservation is reserved spot contr : "+request.getParameter("reservationid"));
			
			
			System.out.println("request.getParameter(\"parkingareaid\") is "+request.getParameter("parkingareaid"));
			reservation.setUsername(sessionUser.getUsername());
			reservation.setParkingarea_id(Integer.parseInt(request.getParameter("parkingareaid")));
			
            reservation.setReservation_id(-1);
			reservation.setCart(selectedCart);
			reservation.setCamera(selectedCamera);
			reservation.setHistory(selectedHistory);
			reservation.setFrom_time(from);
			reservation.setTo_time(to);
			reservation.setReservation_date(today);
				
			session.setAttribute("reservation", reservation);	
				
			getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
		}
	}
}

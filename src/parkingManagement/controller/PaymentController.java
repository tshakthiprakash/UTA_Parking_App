package parkingManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parkingManagement.data.ReservationDao;
import parkingManagement.model.PaymentDetails;
import parkingManagement.model.PaymentErrorMsgs;
import parkingManagement.model.Reservation;

@WebServlet("/paymentController")
public class PaymentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	String url="";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReservationDao reservationDao = new ReservationDao();
		HttpSession session = request.getSession();
		Reservation reservation = new Reservation();
		PaymentDetails paymentDetails=null;
		paymentDetails = new PaymentDetails(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("address"), 
		request.getParameter("type"), request.getParameter("cardnum"), request.getParameter("exp_month"), request.getParameter("exp_year"), request.getParameter("cvv"));
		
	    PaymentErrorMsgs errorMsgs = new PaymentErrorMsgs();
		paymentDetails.validateUser(paymentDetails, errorMsgs);
		session.setAttribute("parkingErrorMsgs", errorMsgs);
	    session.setAttribute("paymentdetails", paymentDetails);
	    session.setAttribute("parkingareaname", request.getParameter("parkingareaname"));
		session.setAttribute("selectedoptions", request.getParameter("selectedoptions"));
		session.setAttribute("parkingtype", request.getParameter("parkingtype"));
		session.setAttribute("floor", request.getParameter("parkingareafloor"));
		session.setAttribute("reservationfromtime", request.getParameter("reservationfromtime"));
		session.setAttribute("reservationtotime", request.getParameter("reservationtotime"));
		session.setAttribute("totalprice", session.getAttribute("totalprice"));
		reservation = (Reservation) session.getAttribute("reservation");
		session.setAttribute("reservation", reservation);
		
	    if (errorMsgs.getErrorMsg().equals("")) {
			reservationDao.reserveParkingSlot(reservation);
			session.removeAttribute("reservation");		
			session.removeAttribute("totalprice");	
			session.removeAttribute("paymentdetails");
			getServletContext().getRequestDispatcher("/reservationconfirmed.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
		}
	}

}


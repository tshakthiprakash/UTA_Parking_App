package parkingManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parkingManagement.model.*;
import parkingManagement.data.*;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User(request.getParameter("firstname"), request.getParameter("lastname"),
				request.getParameter("username"), request.getParameter("password"),request.getParameter("password"), request.getParameter("uta_id"),
				request.getParameter("user_role"), request.getParameter("phone"), request.getParameter("email"),
				request.getParameter("saddress"), request.getParameter("city"), request.getParameter("state"),
				request.getParameter("zip"), request.getParameter("car_num_plate"), request.getParameter("permit_id"),
				request.getParameter("permit_type"));
		
		UserErrorMsgs ProfileErrorMsgs = new UserErrorMsgs();
		user.validateUser(user, ProfileErrorMsgs,"myprofile");
		session.setAttribute("user",user);
		session.setAttribute("profileerrorMsgs",ProfileErrorMsgs);
		if (ProfileErrorMsgs.getErrorMsg().equals("")){
			
			ProfileDao profileDao = new ProfileDao();
			profileDao.updateUser(user);
			session.removeAttribute("myprofileCount");
			session.setAttribute("myprofileCount", 0);
			int count = (int)session.getAttribute("myprofileCount");
			//System.out.println("correct"+count);
			response.sendRedirect("ProfileController");
		}
		else
		{
			session.removeAttribute("my_profile");
			session.setAttribute("my_profile", user);
			session.removeAttribute("myprofileCount");
			session.setAttribute("myprofileCount", 2);
			int count = (int)session.getAttribute("myprofileCount");
			//System.out.println("wrong update="+count);
			response.sendRedirect("ProfileController");	
		}
		
		

	}

}

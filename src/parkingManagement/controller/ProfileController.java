package parkingManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parkingManagement.data.*;
import parkingManagement.model.*;
import javax.servlet.http.HttpSession;
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ProfileDao profileDao = new ProfileDao();
		User user;
		User temp = (User)session.getAttribute("user_info");
		user = profileDao.fetchUserProfile(temp.getUsername());
		//session.setAttribute("my_profile", user);
		int count = (int)session.getAttribute("myprofileCount");
		//System.out.println(count);
		if(count==0){
		session.removeAttribute("myprofileCount");
		session.setAttribute("myprofileCount", 1);
		session.setAttribute("my_profile", user);
		}
		response.sendRedirect("myprofile.jsp");
	}
}

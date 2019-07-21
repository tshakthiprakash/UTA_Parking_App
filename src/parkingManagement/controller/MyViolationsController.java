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
@WebServlet("/MyViolationsController")
public class MyViolationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ProfileDao profileDao = new ProfileDao();
		User user;
		User temp = (User)session.getAttribute("user_info");
		user = profileDao.fetchUserViolations(temp.getUsername());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("noshows", user.getNoshows());
		session.setAttribute("overdue", user.getOverdue());
		getServletContext().getRequestDispatcher("/view_violations.jsp").forward(request, response);
	}
}

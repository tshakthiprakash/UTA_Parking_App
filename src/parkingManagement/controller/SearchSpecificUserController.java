package parkingManagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parkingManagement.data.SearchUserDao;
import parkingManagement.model.User;

@WebServlet("/searchSpecificUserController")
public class SearchSpecificUserController extends HttpServlet {
	String url="";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request,response);	
		SearchUserDao searchDb = new SearchUserDao();
		HttpSession session = request.getSession();
		User search_user = new User();
		User dbuser = new User();
		
		search_user.setUsername(request.getParameter("search_username"));
		dbuser = searchDb.searchSpecificUser(request.getParameter("search_username"));
		session.setAttribute("search_user", dbuser);
		getServletContext().getRequestDispatcher("/searchSpecificUser.jsp").forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("search_user");
		SearchUserDao searchDb = new SearchUserDao();
		if(action.equals("setNoshow")){
			searchDb.setNoshow(user.getNoshows()+1,user.getUsername());
			response.sendRedirect("searchSpecificUserController?search_username="+user.getUsername());
			
		}
		if(action.equals("setOverdue")){
			searchDb.setOverDue(user.getOverdue()+1, user.getUsername());
			response.sendRedirect("searchSpecificUserController?search_username="+user.getUsername());
			
		}
		if(action.equals("editUserRole"))
		{
			searchDb.editUserRole(user.getUsername(), request.getParameter("user_role"));
			response.sendRedirect("searchSpecificUserController?search_username="+user.getUsername());
		}
		if(action.equals("editUserviolations"))
		{
			int noshow = Integer.parseInt(request.getParameter("noshows"));
			int overdue = Integer.parseInt(request.getParameter("overdue"));
			searchDb.editUserViolation(user.getUsername(), noshow, overdue);
			response.sendRedirect("searchSpecificUserController?search_username="+user.getUsername());
		}	

	}
}


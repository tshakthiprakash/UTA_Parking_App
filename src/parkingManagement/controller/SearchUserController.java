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
import parkingManagement.model.UserErrorMsgs;

@WebServlet("/searchUserController")
public class SearchUserController extends HttpServlet {
	String url="";
	String errMsg="";
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserErrorMsgs searchUserErr = new UserErrorMsgs();
		SearchUserDao searchDb = new SearchUserDao();
		HttpSession session = request.getSession();
		User search_user = new User();
		List<User> userList = new ArrayList<User>();
		String action = request.getParameter("action");
		
			search_user.validateSearchUserLastName(request.getParameter("search_lastname"), searchUserErr);
			if(!searchUserErr.getLastnameError().equals(""))
			{
				url ="/search_user.jsp";
				session.setAttribute("errorMessage", searchUserErr);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else
			{
				userList = searchDb.searchUser(request.getParameter("search_lastname"));
				session.setAttribute("userList", userList);
				session.setAttribute("errorMessage", searchUserErr);
				getServletContext().getRequestDispatcher("/searchUser_results.jsp").forward(request, response);
					
			}
	}
}

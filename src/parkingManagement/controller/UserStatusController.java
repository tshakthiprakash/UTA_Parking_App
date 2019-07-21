package parkingManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parkingManagement.data.UserStatusDao;
import parkingManagement.model.UserErrorMsgs;

@WebServlet("/userStatusController")
public class UserStatusController extends HttpServlet {
	String url="";
	String errMsg="";
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String action = request.getParameter("action");
		String userStatus = "";
		String successMsg = "";
		UserErrorMsgs searchUserError = new UserErrorMsgs();
		UserStatusDao userStatusDb = new UserStatusDao();
		UserStatusDao changeUserStatusDb = new UserStatusDao();
		UserStatusDao resetViolationsDb = new UserStatusDao();
		HttpSession session = request.getSession();
		session.removeAttribute("successmessage");
		session.removeAttribute("modess");
		session.removeAttribute("UserStatuserrorMessage");
			String username = request.getParameter("search_username");
			if(username.equals(""))
			{
				url ="/activate_user.jsp";
				errMsg = "Please enter the Username";
				searchUserError.setUsernameError(errMsg);
				session.setAttribute("modess","error");
				session.removeAttribute("successmessage");
				session.setAttribute("UserStatuserrorMessage", searchUserError);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else
			{
				userStatus = userStatusDb.getUserStatus(username);
				if(!(userStatus.equals("")))
				{
					if(userStatus.equalsIgnoreCase("Active"))
					{
						url ="/activate_user.jsp";
						errMsg = "User is already Active";
						successMsg = "";
						session.setAttribute("successmessage",successMsg);
						session.removeAttribute("successmessage");
						session.setAttribute("modess", "error");
						searchUserError.setUsernameError(errMsg);
						session.setAttribute("UserStatuserrorMessage", searchUserError);
						getServletContext().getRequestDispatcher(url).forward(request, response);	
					}
					else
					{
						url ="/activate_user.jsp";
						changeUserStatusDb.changeUserStatus("Active", username);
						resetViolationsDb.resetViolations(username);
						session.setAttribute("successmessage","User has been activated!");
						session.removeAttribute("UserStatuserrorMessage");
						session.setAttribute("modess", "success");
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}
					
				}
				else
				{
					url ="/activate_user.jsp";
					errMsg = "User not found";
					successMsg = "";
					session.setAttribute("successmessage",successMsg);
					session.removeAttribute("successmessage");
					session.setAttribute("modess", "error");
					searchUserError.setUsernameError(errMsg);
					session.setAttribute("UserStatuserrorMessage", searchUserError);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userStatus = "";
		String successMsg = "";
		UserErrorMsgs searchUserErrorMsgs = new UserErrorMsgs();
		UserStatusDao userStatusDb = new UserStatusDao();
		UserStatusDao changeUserStatusDb = new UserStatusDao();
		HttpSession session = request.getSession();
		session.removeAttribute("successmessage");
		session.removeAttribute("modess");
		session.removeAttribute("UserStatuserrorMessage");
			String username = request.getParameter("search_username");
			if(username.equals(""))
			{
				url ="/revoke_user.jsp";
				errMsg = "Please enter the Username";
				searchUserErrorMsgs.setUsernameError(errMsg);
				session.setAttribute("modess","error");
				session.removeAttribute("successmessage");
				session.setAttribute("UserStatuserrorMessage", searchUserErrorMsgs);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else
			{
				userStatus = userStatusDb.getUserStatus(username);
				if(!(userStatus.equals("")))
				{
					if(userStatus.equalsIgnoreCase("Revoked"))
					{
						url ="/revoke_user.jsp";
						errMsg = "User is already Revoked";
						successMsg = "";
						session.setAttribute("successmessage",successMsg);
						session.removeAttribute("successmessage");
						session.setAttribute("modess", "error");
						searchUserErrorMsgs.setUsernameError(errMsg);
						session.setAttribute("UserStatuserrorMessage", searchUserErrorMsgs);
						getServletContext().getRequestDispatcher(url).forward(request, response);	
					}
					else
					{
						url ="/revoke_user.jsp";
						changeUserStatusDb.changeUserStatus("Revoked", username);
						session.setAttribute("successmessage","User has been revoked!");
						session.removeAttribute("UserStatuserrorMessage");
						session.setAttribute("modess", "success");
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}
				}
				else
				{
					url ="/revoke_user.jsp";
					errMsg = "User not found";
					successMsg = "";
					session.setAttribute("successmessage",successMsg);
					session.removeAttribute("successmessage");
					session.setAttribute("modess", "error");
					searchUserErrorMsgs.setUsernameError(errMsg);
					session.setAttribute("UserStatuserrorMessage", searchUserErrorMsgs);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			}
	}
}
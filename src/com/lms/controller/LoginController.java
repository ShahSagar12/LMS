package com.lms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.entity.User;
import com.lms.model.RoleTypes;
import com.lms.service.UserService;
import com.lms.serviceimpl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("webapp/login.html");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService=new UserServiceImpl();
		String email = req.getParameter("email");
		String password=req.getParameter("password");
		String roleType = req.getParameter("role");
		User authenticatedUser = userService.isAuthenticated(email, password);
		if(authenticatedUser!=null) {
			switch (RoleTypes.roles.get(authenticatedUser.getRoleid())) {
			case "Admin":
				HttpSession session = req.getSession();
				session.setAttribute("authenticatedUser", authenticatedUser);
				resp.sendRedirect("/lms/admin-dashboard");
				break;
			case "User":
				HttpSession sessions = req.getSession();
				sessions.setAttribute("authenticatedUser", authenticatedUser);
				resp.sendRedirect("/lms/student-dashboard");
				break;
			default:
				break;
			}
		}else {
			req.setAttribute("errorMessage", "user or password not matched");
			req.getRequestDispatcher("/login.html").forward(req, resp);
			
		}
	}

}

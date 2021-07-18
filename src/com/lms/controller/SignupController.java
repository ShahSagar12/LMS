package com.lms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.daoimpl.UserDAOImpl;
import com.lms.entity.Role;
import com.lms.entity.User;
import com.lms.service.RoleService;
import com.lms.service.UserService;
import com.lms.serviceimpl.RoleServiceImpl;
import com.lms.serviceimpl.UserServiceImpl;

@WebServlet("/signup")
public class SignupController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("signup.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserService userService=new UserServiceImpl();
			RoleService roleService=new RoleServiceImpl();
			User user=new User();
			user.setFirstName(req.getParameter("firstname"));
			user.setLastName(req.getParameter("lastname"));
			user.setEmail(req.getParameter("email"));
			user.setUserPassword(req.getParameter("password"));
			Role role = roleService.findByRole(req.getParameter("role"));
			user.setRoleid(role.getRoleid());
			userService.register(user);
			resp.sendRedirect("/lms/login");
		} catch (SQLException e) {
			LOGGER.info("ERROR:"+e.getMessage());
		}
	}

}

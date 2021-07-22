package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lms.entity.User;
import com.lms.model.RoleTypes;
import com.lms.model.dtos.CredentialsDto;
import com.lms.model.response.StandardResponse;
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
		ObjectMapper mapper = new ObjectMapper();
		CredentialsDto credentialsDto = mapper.readValue(req.getInputStream(), CredentialsDto.class);
		User authenticatedUser = userService.isAuthenticated(credentialsDto.getEmail(), credentialsDto.getPassword());
		if(authenticatedUser!=null) {
			switch (RoleTypes.roles.get(authenticatedUser.getRoleid())) {
			case "Admin":
				HttpSession session = req.getSession();
				session.setAttribute("authenticatedUser", authenticatedUser);
				RequestDispatcher rs = req.getRequestDispatcher("/lms/admin-dashboard");
	            rs.forward(req, resp);
//				resp.sendRedirect("/lms/admin-dashboard");
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
			StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Wrong Credentials");
			retunResponse(resp, sr);
		}
	}
	
	private void retunResponse(HttpServletResponse resp, Object object) throws IOException {
		Gson gson = new Gson();
		String userJsonString = gson.toJson(object);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(userJsonString);
		out.flush();
	}

}

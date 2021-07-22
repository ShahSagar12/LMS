package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lms.daoimpl.UserDAOImpl;
import com.lms.entity.Role;
import com.lms.entity.User;
import com.lms.model.dtos.UserDto;
import com.lms.model.response.StandardResponse;
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
		resp.sendRedirect("webapp/signup.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserDto userDto = mapper.readValue(req.getInputStream(), UserDto.class);
			UserService userService=new UserServiceImpl();
			RoleService roleService=new RoleServiceImpl();
			User user=new User();
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setUserPassword(userDto.getPassword());
			Role role = roleService.findByRole(userDto.getRegisterAs());
			user.setRoleid(role.getRoleid());
			if(userService.register(user)) {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_OK, "Saved Successfully");
				retunResponse(resp, sr);
			}else {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Cannot Save Successfully");
				retunResponse(resp, sr);
			}
			resp.sendRedirect("/lms/login");
		} catch (SQLException e) {
			StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Error In Data");
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

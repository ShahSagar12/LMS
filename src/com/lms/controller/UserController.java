package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lms.model.response.StandardResponse;
import com.lms.service.UserService;
import com.lms.serviceimpl.UserServiceImpl;

@WebServlet("/users/all")
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!(req.getHeader("user-info").equals("null"))) {
		UserService userService=new UserServiceImpl();
		int parameterValues = req.getParameter("id")==null?0:Integer.parseInt(req.getParameter("id"));
		try {
			if(parameterValues!=0) {
				retunResponse(resp, userService.findById(parameterValues));
			}else {
				retunResponse(resp, userService.findAll());
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} }
		else {
			StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_FORBIDDEN, "Unable to get user");
			retunResponse(resp, standardResponse);
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

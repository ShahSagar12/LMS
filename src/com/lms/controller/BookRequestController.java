package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lms.model.UserInfo;
import com.lms.service.UserService;
import com.lms.serviceimpl.UserServiceImpl;

@WebServlet("/book/request")
public class BookRequestController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService=new UserServiceImpl();
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserInfo userInfo = mapper.readValue(req.getHeader("user-info"), UserInfo.class);
			retunResponse(resp, userService.getBookOwnerByOfAdmin(userInfo.getId()));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
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

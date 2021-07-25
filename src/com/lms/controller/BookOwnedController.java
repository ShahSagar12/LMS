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
import com.lms.entity.User;
import com.lms.model.UserInfo;
import com.lms.model.dtos.BookOrderDto;
import com.lms.model.response.StandardResponse;
import com.lms.service.BookService;
import com.lms.serviceimpl.BookServiceImpl;

@WebServlet("/books/owned")
public class BookOwnedController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!(req.getHeader("user-info").equals("null"))) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserInfo userInfo = mapper.readValue(req.getHeader("user-info"), UserInfo.class);
			BookService bookService=new BookServiceImpl();
			retunResponse(resp, bookService.getBookOwnedByUser(userInfo.getId()));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} }
		else {
			StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_FORBIDDEN, "Unable to get owned books");
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

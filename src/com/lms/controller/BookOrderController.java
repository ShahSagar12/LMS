package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lms.entity.Book;
import com.lms.entity.BookUser;
import com.lms.entity.User;
import com.lms.model.UserInfo;
import com.lms.model.dtos.BookOrderDto;
import com.lms.model.response.StandardResponse;
import com.lms.service.BookService;
import com.lms.service.BookUserService;
import com.lms.serviceimpl.BookServiceImpl;
import com.lms.serviceimpl.BookUserServiceImpl;

@WebServlet("/order")
public class BookOrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("webapp/student-dashboard.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!(req.getHeader("user-info").equals("null"))) {
		BookUserService bookUserService=new BookUserServiceImpl();
		BookService bookService=new BookServiceImpl();
		ObjectMapper mapper = new ObjectMapper();
		UserInfo userInfo = mapper.readValue(req.getHeader("user-info"), UserInfo.class);
		BookOrderDto bookOrder = mapper.readValue(req.getInputStream(), BookOrderDto.class);
		try {
			BookUser bookOrderAlready=bookUserService.getByUserIdAndBook(userInfo.getId(), Integer.parseInt(bookOrder.getBookId()));
			if(bookOrderAlready!=null) {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_NOT_ACCEPTABLE, "This book has already been ordered");
				retunResponse(resp, sr);
				return;
				
		}
			BookUser bookUser=new BookUser();
			bookUser.setBookId(Integer.parseInt(bookOrder.getBookId()));
			bookUser.setBookStatus(bookOrder.getBookStatus());
			bookUser.setBookTakenFor(Integer.parseInt(bookOrder.getBookTakenFor()));
			bookUser.setUserId(userInfo.getId());
			bookUser.setBookStatus("OnRequest");
			Book book = bookService.get(Integer.parseInt(bookOrder.getBookId()));
			book.setBookQty(book.getBookQty()-1);
			if(bookUserService.save(bookUser) && bookService.update(book)) {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_OK, "Book Ordered Successfully");
				retunResponse(resp, sr);
			}else {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Cannot Save Successfully");
				retunResponse(resp, sr);
			}
	} catch (SQLException e) {
		StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Error In Data");
		retunResponse(resp, sr);
	}}
		else {
			StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_FORBIDDEN, "Unable to order book");
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

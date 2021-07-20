package com.lms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.entity.BookUser;
import com.lms.entity.User;
import com.lms.model.dtos.BookOrderDto;
import com.lms.service.BookUserService;
import com.lms.serviceimpl.BookUserServiceImpl;

@WebServlet("/order")
public class BookOrderController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("student-dashboard.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookUserService bookUserService=new BookUserServiceImpl();
		HttpSession session = req.getSession();
		User user=(User) session.getAttribute("authenticatedUser");
		ObjectMapper mapper = new ObjectMapper();
		BookOrderDto bookOrder = mapper.readValue(req.getInputStream(), BookOrderDto.class);
		BookUser bookUser=new BookUser();
		bookUser.setBookId(bookUser.getBookId());
		bookUser.setBookStatus(bookOrder.getBookStatus());
		bookUser.setBookTakenFor(Integer.parseInt(bookOrder.getBookTakenFor()));
		bookUser.setUserId(user.getId());
		try {
			bookUserService.save(bookUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("student-dashboard.jsp");

	}


}

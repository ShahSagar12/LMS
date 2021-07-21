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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lms.daoimpl.UserDAOImpl;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.service.BookService;
import com.lms.serviceimpl.BookServiceImpl;

@WebServlet("/admin-dashboard")
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();

	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authenticatedUser");
		if(user!=null) {
			resp.sendRedirect("webapp/admin/admin-dashboard.html");
		}else {
			resp.sendRedirect("404Error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Book book = mapper.readValue(req.getInputStream(), Book.class);
			BookService bookService=new BookServiceImpl();
			User user=(User)req.getSession().getAttribute("authenticatedUser");
			book.setAdminId(user.getId());
			System.out.println(book);
			if(book.getBookId()==0) {
				if(bookService.save(book)) {
					String userJsonString = this.gson.toJson(book);

			        PrintWriter out = resp.getWriter();
			        resp.setContentType("application/json");
			        resp.setCharacterEncoding("UTF-8");
			        out.print(userJsonString);
			        out.flush();

//					req.getRequestDispatcher("webapp/admin/add-book.html").forward(req, resp);
				};
			}else {
				bookService.update(book);
			}
		}catch(SQLException exp){
			LOGGER.info("Error on sql :"+exp.getMessage());
		}
		
	}
}

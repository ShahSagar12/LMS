package com.lms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.daoimpl.UserDAOImpl;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.service.BookService;
import com.lms.serviceimpl.BookServiceImpl;

@WebServlet("/books")
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authenticatedUser");
		if(user!=null) {
			resp.sendRedirect("add-book.jsp");
		}else {
			resp.sendRedirect("404Error.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookService bookService=new BookServiceImpl();
		String bookId = req.getParameter("book-id");
		if(bookId=="") {
			Book book=new Book();
			book.setBookTitle(req.getParameter("book-title"));
			book.setBookAuthor(req.getParameter("book-author"));
			book.setBookStatusType(req.getParameter("book-status"));
			book.setnOfPages(Integer.parseInt(req.getParameter("no-Of-Pages")));
			book.setPublishedYear(req.getParameter("published-year"));
			book.setPublisher(req.getParameter("book-publisher"));
			try {
				bookService.save(book);
			} catch (SQLException e) {
				LOGGER.info("Error: sql "+e.getMessage());
			}
		}else {
			try {
				Book book=new Book();
				book.setBookId(Integer.parseInt(bookId));
				book.setBookTitle(req.getParameter("book-title"));
				book.setBookAuthor(req.getParameter("book-author"));
				book.setBookStatusType(req.getParameter("book-status"));
				book.setnOfPages(Integer.parseInt(req.getParameter("no-Of-Pages")));
				book.setPublishedYear(req.getParameter("published-year"));
				book.setPublisher(req.getParameter("book-publisher"));
				bookService.update(book);
			} catch (SQLException e) {
				LOGGER.info("Error: sql "+e.getMessage());
			}
		}
		resp.sendRedirect("add-book.jsp");
	}
}

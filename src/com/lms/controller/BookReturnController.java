package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lms.entity.Book;
import com.lms.entity.BookUser;
import com.lms.model.response.StandardResponse;
import com.lms.service.BookService;
import com.lms.service.BookUserService;
import com.lms.serviceimpl.BookServiceImpl;
import com.lms.serviceimpl.BookUserServiceImpl;

@WebServlet("/return/book")
public class BookReturnController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BookUserService bookUserService=new BookUserServiceImpl();
			BookService bookService=new BookServiceImpl();
			ObjectMapper mapper = new ObjectMapper();
			int parameterValues = req.getParameter("book-id")==null?0:Integer.parseInt(req.getParameter("book-id"));
			BookUser bookUser = bookUserService.get(parameterValues);
			bookUser.setBookStatus("Shelf");
			Book book = bookService.get(parameterValues);
			book.setBookQty(book.getBookQty()+1);
			if(bookService.update(book) && bookUserService.update(bookUser)) {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_OK, "Return Successfully");
				retunResponse(resp, sr);
			}else {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Cannot Return now please try again later");
				retunResponse(resp, sr);
			}
		}catch(Exception exp) {
			StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Connection Error");
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

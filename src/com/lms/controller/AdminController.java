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
import com.lms.entity.BookUser;
import com.lms.entity.User;
import com.lms.model.UserInfo;
import com.lms.model.response.StandardResponse;
import com.lms.service.BookService;
import com.lms.serviceimpl.BookServiceImpl;

@WebServlet("/admin-dashboard")
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    
			resp.sendRedirect("webapp/admin/admin-dashboard.html");
		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	if(!(req.getHeader("user-info").equals("null"))) {
	int id = req.getParameter("id")==null?0:Integer.parseInt(req.getParameter("id"));
		    try {
				boolean isDeleted=new BookServiceImpl().delete(id);
				if(isDeleted) {
					StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_OK, "Deleted Successfully");
					retunResponse(resp, standardResponse);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Unable to delete");
				retunResponse(resp, standardResponse);
				// TODO Auto-generated catch block
			}}
     else {
	StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_FORBIDDEN, "Unable to delete");
	retunResponse(resp, standardResponse);
	
        }
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!(req.getHeader("user-info").equals("null"))) {
		ObjectMapper mapper = new ObjectMapper();
		UserInfo userInfo = mapper.readValue(req.getHeader("user-info"), UserInfo.class);
		System.out.println(userInfo);
		Book book = mapper.readValue(req.getInputStream(), Book.class);
		try {
			boolean isUpdate=new BookServiceImpl().update(book);
			if(isUpdate) {
				StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_OK, "Updated Successfully");
				retunResponse(resp, standardResponse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Cannot update book");
			retunResponse(resp, standardResponse);
		}}
		
			else {
				StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_FORBIDDEN, "Unable to update");
				retunResponse(resp, standardResponse);
				}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!(req.getHeader("user-info").equals("null"))) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserInfo userInfo = mapper.readValue(req.getHeader("user-info"), UserInfo.class);
			System.out.println(userInfo);
			Book book = mapper.readValue(req.getInputStream(), Book.class);
			BookService bookService=new BookServiceImpl();
			book.setAdminId(userInfo.getId());
			if(bookService.getByBookTitle(book.getBookTitle()).getBookId()==0) {
				if(book.getBookId()==0) {
					if(bookService.save(book)) {
						StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_OK, "Saved Successfully");
						retunResponse(resp, standardResponse);
					}
				}else {
					if(bookService.update(book)) {
						StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_ACCEPTED, "Edited Successfully");
						retunResponse(resp, standardResponse);
					}
				}
			}else {
				StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Book Already exist");
				retunResponse(resp, standardResponse);
			}
			
		}catch(SQLException exp){
			StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST, "Cannot Save Object");
			retunResponse(resp, standardResponse);
		}}
		else {
			StandardResponse standardResponse=new StandardResponse(HttpServletResponse.SC_FORBIDDEN, "Unable to save book");
			retunResponse(resp, standardResponse);	
		}
		
	}

	private void retunResponse(HttpServletResponse resp, StandardResponse standardResponse) throws IOException {
		Gson gson = new Gson();
		String userJsonString = gson.toJson(standardResponse);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(userJsonString);
		out.flush();
	}
}

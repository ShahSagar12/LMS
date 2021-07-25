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
import com.lms.entity.BookUser;
import com.lms.model.response.StandardResponse;
import com.lms.service.BookUserService;
import com.lms.serviceimpl.BookUserServiceImpl;

@WebServlet("/bookrequest/accept")
public class AdminAcceptController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!(req.getHeader("user-info").equals("null"))) {
		BookUserService bookUserService=new BookUserServiceImpl();
		int bookUserId = Integer.parseInt(req.getParameter("id"));
		try {
			BookUser bu=bookUserService.get(bookUserId);
			bu.setBookStatus("Onloan");
			if(bookUserService.update(bu)) {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_OK,"Accepted Successfully");
				retunResponse(resp, sr);
			}else {
				StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST,"Cannot update");
				retunResponse(resp, sr);
			}
		} catch (SQLException e) {
			StandardResponse sr=new StandardResponse(HttpServletResponse.SC_BAD_REQUEST,"DB Connection Failure");
			retunResponse(resp, sr);
		}}else {
			StandardResponse sr=new StandardResponse(HttpServletResponse.SC_FORBIDDEN,"Credentials Error");
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

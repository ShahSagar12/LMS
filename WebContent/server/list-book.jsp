<%@ page language="java" import="com.lms.entity.Book" %>
<%@ page language="java" import="com.lms.service.BookService" %>
<%@ page language="java" import="com.lms.serviceimpl.BookServiceImpl" %>

<%
BookService bookService=new BookServiceImpl();
response.setHeader("Content-Type","application/json");
if(request.getParameter("id")==null){
String output="";
for(Book book:bookService.findAll()){
	if(output.equals("")){
		output+=book.toJson();
	}else{
		output+=","+book.toJson();
	}
}
out.println("["+output+"]");
}else{
	int id=Integer.parseInt(request.getParameter("id"));
	Book book=bookService.get(id);
	out.println(book.toJson());
}

%>

package com.lms.entity;

public class Book {
	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private String publisher;
	private String publishedYear;
	private int nOfPages;
	private String bookStatusType;
	private int userId;

	public Book() {
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}
	public int getnOfPages() {
		return nOfPages;
	}
	public void setnOfPages(int nOfPages) {
		this.nOfPages = nOfPages;
	}
	public String getBookStatusType() {
		return bookStatusType;
	}
	public void setBookStatusType(String bookStatusType) {
		this.bookStatusType = bookStatusType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Book(String bookTitle, String bookAuthor, String publisher, String publishedYear, int nOfPages,
			String bookStatusType, int userId) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.publisher = publisher;
		this.publishedYear = publishedYear;
		this.nOfPages = nOfPages;
		this.bookStatusType = bookStatusType;
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + ", publisher="
				+ publisher + ", publishedYear=" + publishedYear + ", nOfPages=" + nOfPages + ", bookStatusType="
				+ bookStatusType + ", userId=" + userId + "]";
	}
	
	public String toJson() {
		String json="{";
				json+="\"bookId\":\""+bookId+"\",";
				json+="\"bookTitle\":\""+bookTitle+"\",";
				json+="\"bookAuthor\":\""+bookAuthor+"\",";
				json+="\"publisher\":\""+publisher+"\",";
				json+="\"publishedYear\":\""+publishedYear+"\",";
				json+="\"nOfPages\":\""+nOfPages+"\",";
				json+="\"bookStatusType\":\""+bookStatusType+"\",";
				json+="\"userId\":\""+userId+"\"";
				json+="}";
		return json;		
	}
	
	
	
}

package com.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
	
	@JsonProperty("bookId")
	private int bookId;
	@JsonProperty("bookTitle")
	private String bookTitle;
	@JsonProperty("bookAuthor")
	private String bookAuthor;
	@JsonProperty("bookPublisher")
	private String publisher;
	@JsonProperty("publishedYear")
	private String publishedYear;
	@JsonProperty("noOfPages")
	private int nOfPages;
	@JsonProperty("bookQty")
	private int bookQty;
	
	@JsonIgnore
	private int adminId;

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


	public int getBookQty() {
		return bookQty;
	}


	public void setBookQty(int bookQty) {
		this.bookQty = bookQty;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	

	public Book(String bookTitle, String bookAuthor, String publisher, String publishedYear, int nOfPages, int bookQty,
			int adminId) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.publisher = publisher;
		this.publishedYear = publishedYear;
		this.nOfPages = nOfPages;
		this.bookQty = bookQty;
		this.adminId = adminId;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + ", publisher="
				+ publisher + ", publishedYear=" + publishedYear + ", nOfPages=" + nOfPages + ", bookQty=" + bookQty
				+ ", adminId=" + adminId + "]";
	}


	public String toJson() {
		String json="{";
				json+="\"bookId\":\""+bookId+"\",";
				json+="\"bookTitle\":\""+bookTitle+"\",";
				json+="\"bookAuthor\":\""+bookAuthor+"\",";
				json+="\"publisher\":\""+publisher+"\",";
				json+="\"publishedYear\":\""+publishedYear+"\",";
				json+="\"nOfPages\":\""+nOfPages+"\",";
				json+="\"bookQty\":\""+bookQty+"\",";
				json+="\"userId\":\""+adminId+"\"";
				json+="}";
		return json;		
	}
	
	
	
}

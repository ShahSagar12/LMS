package com.lms.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookOrderDto {
	@JsonProperty("bookId")
	private String bookId;
	@JsonProperty("bookStatus")
	private String bookStatus;
	@JsonProperty("bookTakenFor")
	private String bookTakenFor;
	
	public BookOrderDto() {
		
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	public String getBookTakenFor() {
		return bookTakenFor;
	}
	public void setBookTakenFor(String bookTakenFor) {
		this.bookTakenFor = bookTakenFor;
	}
	public BookOrderDto(String bookId, String bookStatus, String bookTakenFor) {
		this.bookId = bookId;
		this.bookStatus = bookStatus;
		this.bookTakenFor = bookTakenFor;
	}
	@Override
	public String toString() {
		return "BookOrderDto [bookId=" + bookId + ", bookStatus=" + bookStatus + ", bookTakenFor=" + bookTakenFor + "]";
	}
}

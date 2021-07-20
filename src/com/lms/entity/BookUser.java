package com.lms.entity;

public class BookUser {
	private int id;
	private int userId;
	private int bookId;
	private String bookStatus;
	private String bookTakenAt;
	private int bookTakenFor;
	
	public BookUser() {
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	public String getBookTakenAt() {
		return bookTakenAt;
	}
	public void setBookTakenAt(String bookTakenAt) {
		this.bookTakenAt = bookTakenAt;
	}
	
	public int getBookTakenFor() {
		return bookTakenFor;
	}
	public void setBookTakenFor(int bookTakenFor) {
		this.bookTakenFor = bookTakenFor;
	}
	public BookUser(int userId, int bookId, String bookStatus, String bookTakenAt, int bookTakenFor) {
		this.userId = userId;
		this.bookId = bookId;
		this.bookStatus = bookStatus;
		this.bookTakenAt = bookTakenAt;
		this.bookTakenFor = bookTakenFor;
	}
	
	@Override
	public String toString() {
		return "BookUser [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", bookStatus=" + bookStatus
				+ ", bookTakenAt=" + bookTakenAt + ", bookTakenFor=" + bookTakenFor + "]";
	}
}

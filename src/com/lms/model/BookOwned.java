package com.lms.model;

public class BookOwned {
	private int id;
	private int bookId;
	private int userId;
	private String bookTakenAt;
	private int bookTakenFor;
	private String bookTitle;
	private String bookAuthor;
	private String bookOwnedStatus;
	
	public BookOwned() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getBookOwnedStatus() {
		return bookOwnedStatus;
	}
	public void setBookOwnedStatus(String bookOwnedStatus) {
		this.bookOwnedStatus = bookOwnedStatus;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public BookOwned(int bookId, int userId, String bookTakenAt, int bookTakenFor, String bookTitle,
			String bookAuthor) {
		this.bookId = bookId;
		this.userId = userId;
		this.bookTakenAt = bookTakenAt;
		this.bookTakenFor = bookTakenFor;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
	}
	
	@Override
	public String toString() {
		return "BookOwned [bookId=" + bookId + ", userId=" + userId + ", bookTakenAt=" + bookTakenAt + ", bookTakenFor="
				+ bookTakenFor + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + "]";
	}
	
	
	

}

package com.lms.model.dtos;

public class BookRequestDtos {
	
	private int bookUserId;
	private String studentName;
	private String bookTitle;
	private String bookAuthor;
	private String bookTakenAt;
	private int bookTakenFor;
	private String bookStatus;
	
	public BookRequestDtos() {
		// TODO Auto-generated constructor stub
	}
	
	public int getBookUserId() {
		return bookUserId;
	}

	public void setBookUserId(int bookUserId) {
		this.bookUserId = bookUserId;
	}

	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	
	
	@Override
	public String toString() {
		return "BookRequestDtos [bookUserId=" + bookUserId + ", studentName=" + studentName + ", bookTitle=" + bookTitle
				+ ", bookAuthor=" + bookAuthor + ", bookTakenAt=" + bookTakenAt + ", bookTakenFor=" + bookTakenFor
				+ ", bookStatus=" + bookStatus + "]";
	}
	
	
}

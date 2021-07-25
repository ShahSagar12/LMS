package com.lms.model;

public class BookRequest {
	
	private int bookUserId;
	private String studentName;
	private String bookTitle;
	private String bookAuthor;
	private String bookTakenAt;
	private int bookTakenFor;
	private String bookStatus;
	private Long remainingDays;
	private String calibratedFine;
	
	public BookRequest() {
	
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
	public Long getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(Long remainingDays) {
		this.remainingDays = remainingDays;
	}
	public String getCalibratedFine() {
		return calibratedFine;
	}
	public void setCalibratedFine(String d) {
		this.calibratedFine = d;
	}
	
	public BookRequest(int bookUserId, String studentName, String bookTitle, String bookAuthor, String bookTakenAt,
			int bookTakenFor, String bookStatus, Long remainingDays, String calibratedFine) {
		this.bookUserId = bookUserId;
		this.studentName = studentName;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookTakenAt = bookTakenAt;
		this.bookTakenFor = bookTakenFor;
		this.bookStatus = bookStatus;
		this.remainingDays = remainingDays;
		this.calibratedFine = calibratedFine;
	}
	
	@Override
	public String toString() {
		return "BookRequest [bookUserId=" + bookUserId + ", studentName=" + studentName + ", bookTitle=" + bookTitle
				+ ", bookAuthor=" + bookAuthor + ", bookTakenAt=" + bookTakenAt + ", bookTakenFor=" + bookTakenFor
				+ ", bookStatus=" + bookStatus + ", remainingDays=" + remainingDays + ", calibratedFine="
				+ calibratedFine + "]";
	}

	
}

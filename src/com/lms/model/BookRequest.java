package com.lms.model;

public class BookRequest {
	
	private int bookId;
	private String studentName;
	private String bookTitle;
	private String bookAuthor;
	private String bookTakenAt;
	private int bookTakenFor;
	private String bookStatus;
	private Long remainingDays;
	private double calibratedFine;
	
	public BookRequest() {
	
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	public double getCalibratedFine() {
		return calibratedFine;
	}
	public void setCalibratedFine(double d) {
		this.calibratedFine = d;
	}
	
	public BookRequest(int bookId, String studentName, String bookTitle, String bookAuthor, String bookTakenAt,
			int bookTakenFor, String bookStatus, Long remainingDays, double calibratedFine) {
		this.bookId = bookId;
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
		return "BookRequest [bookId=" + bookId + ", studentName=" + studentName + ", bookTitle=" + bookTitle
				+ ", bookAuthor=" + bookAuthor + ", bookTakenAt=" + bookTakenAt + ", bookTakenFor=" + bookTakenFor
				+ ", bookStatus=" + bookStatus + ", remainingDays=" + remainingDays + ", calibratedFine="
				+ calibratedFine + "]";
	}
}

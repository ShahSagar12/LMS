package com.lms.model.dtos;

public class BookOwnedDto {
	private int bookId;
	private int userId;
	private String bookTitle;
	private String bookAuthor;
	private String bookTakenAt;
	private int bookTakenFor;
	private Long remainingDays;
	private String fineCalibrated;
	
	public BookOwnedDto() {
		
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
	public String getFineCalibrated() {
		return fineCalibrated;
	}
	public void setFineCalibrated(String fineCalibrated) {
		this.fineCalibrated = fineCalibrated;
	}
	public Long getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(Long remainingDays) {
		this.remainingDays = remainingDays;
	}
	public BookOwnedDto(int bookId, int userId, String bookTitle, String bookAuthor, String bookTakenAt,
			int bookTakenFor, Long remainingDays, String fineCalibrated) {
		this.bookId = bookId;
		this.userId = userId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookTakenAt = bookTakenAt;
		this.bookTakenFor = bookTakenFor;
		this.remainingDays = remainingDays;
		this.fineCalibrated = fineCalibrated;
	}
	
	@Override
	public String toString() {
		return "BookOwnedDto [bookId=" + bookId + ", userId=" + userId + ", bookTitle=" + bookTitle + ", bookAuthor="
				+ bookAuthor + ", bookTakenAt=" + bookTakenAt + ", bookTakenFor=" + bookTakenFor + ", remainingDays="
				+ remainingDays + ", fineCalibrated=" + fineCalibrated + "]";
	}
}

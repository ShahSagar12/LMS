package com.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.Book;
import com.lms.model.BookOwned;
import com.lms.model.dtos.BookOwnedDto;

public interface BookService {
	boolean save(Book book) throws SQLException;
	List<Book> findAll() throws SQLException;
	Book get(int id) throws SQLException;
	boolean update(Book book) throws SQLException;
	boolean delete(int id) throws SQLException;
//	boolean alreadyTakenSufficientBooks(Integer userId);
	List<BookOwnedDto> getBookOwnedByUser(int userId) throws SQLException;
	Book getByBookTitle(String title) throws SQLException;
}

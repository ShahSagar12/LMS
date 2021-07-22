package com.lms.dao;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.Book;
import com.lms.model.BookOwned;

public interface BookDAO {
	boolean save(Book e) throws SQLException;
	List<Book> findAll() throws SQLException;
	Book get(int id) throws SQLException;
	boolean update(Book book) throws SQLException;
	boolean delete(int id) throws SQLException;
	List<BookOwned> getBookOwnedByUser(int userId) throws SQLException;
}

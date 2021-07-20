package com.lms.dao;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.BookUser;

public interface BookUserDAO {
	boolean save(BookUser bookUser) throws SQLException;
	List<BookUser> findAll() throws SQLException;
	BookUser get(int id) throws SQLException;
	boolean update(BookUser bookUser) throws SQLException;
	boolean delete(int id) throws SQLException;

}

package com.lms.dao;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.User;
import com.lms.model.dtos.BookRequestDtos;

public interface UserDAO {
	boolean save(User user) throws SQLException;
	List<User> findAll() throws SQLException;
	User get(int id) throws SQLException;
	boolean update(User user) throws SQLException;
	boolean delete(int id) throws SQLException;
	User getByEmail(String email) throws SQLException;
	List<BookRequestDtos> getBookOwnerByOfAdmin(int adminId) throws SQLException;
}

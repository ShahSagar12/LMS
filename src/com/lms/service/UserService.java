package com.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.User;
import com.lms.model.BookRequest;
import com.lms.model.dtos.BookRequestDtos;

public interface UserService {
	public boolean register(User user) throws SQLException;
	public User isAuthenticated(String email,String password);
	public List<User> findAll() throws SQLException;
	public User findById(int id) throws SQLException;
	User getByEmail(String email) throws SQLException;
	public boolean update(User user) throws SQLException;
	boolean delete(int id) throws SQLException;
	List<BookRequest> getBookOwnerByOfAdmin(int adminId) throws SQLException;
}

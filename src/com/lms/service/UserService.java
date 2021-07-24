package com.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.User;

public interface UserService {
	public boolean register(User user) throws SQLException;
	public User isAuthenticated(String email,String password);
	public List<User> findAll() throws SQLException;
	public User findById(int id) throws SQLException;
	User getByEmail(String email) throws SQLException;
}

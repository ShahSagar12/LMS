package com.lms.service;

import java.sql.SQLException;

import com.lms.entity.User;

public interface UserService {
	public boolean register(User user) throws SQLException;
	public User isAuthenticated(String email,String password);
}

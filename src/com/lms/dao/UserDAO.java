package com.lms.dao;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.User;

public interface UserDAO {
	boolean save(User user) throws SQLException;
	List<User> findAll() throws SQLException;
	User get(int id) throws SQLException;
	boolean update(User user) throws SQLException;
	boolean delete(int id) throws SQLException;

}

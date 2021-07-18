package com.lms.dao;

import java.sql.SQLException;
import java.util.List;

import com.lms.entity.Role;

public interface RoleDAO {
	boolean save(Role role) throws SQLException;
	List<Role> findAll() throws SQLException;
	Role get(int id) throws SQLException;
	boolean update(Role role) throws SQLException;
	boolean delete(int id) throws SQLException;

}

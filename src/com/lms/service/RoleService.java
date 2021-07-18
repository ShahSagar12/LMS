package com.lms.service;

import java.sql.SQLException;

import com.lms.entity.Role;

public interface RoleService {
	
	public Role findByRole(String role) throws SQLException;

}

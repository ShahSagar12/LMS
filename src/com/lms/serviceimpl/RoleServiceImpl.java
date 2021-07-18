package com.lms.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.lms.dao.RoleDAO;
import com.lms.daoimpl.RoleDAOImpl;
import com.lms.entity.Role;
import com.lms.service.RoleService;

public class RoleServiceImpl implements RoleService{
	
	RoleDAO roleDao=new RoleDAOImpl();

	@Override
	public Role findByRole(String role) throws SQLException {
		List<Role> roles=roleDao.findAll();
		roles.removeIf(rol-> !role.equalsIgnoreCase(rol.getRoleType()));
		return roles.get(0);
	}

}

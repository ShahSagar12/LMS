package com.lms.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.lms.dao.UserDAO;
import com.lms.daoimpl.RoleDAOImpl;
import com.lms.daoimpl.UserDAOImpl;
import com.lms.entity.User;
import com.lms.service.UserService;

public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = Logger.getLogger(RoleDAOImpl.class.getName());
	UserDAO userDao=new UserDAOImpl();

	@Override
	public boolean register(User user) throws SQLException {
		
		return userDao.save(user);
	}

	@Override
	public User isAuthenticated(String email, String password) {
		List<User> users=new ArrayList<>();
		try {
			users= userDao.findAll().stream().filter(user->user.getEmail().equalsIgnoreCase(email)).filter(u->u.getUserPassword().equalsIgnoreCase(password)).collect(Collectors.toList());
		} catch (SQLException e) {
			LOGGER.info("Error :"+e.getMessage());
		}
		if(users.size()>0) {
			return users.get(0);	
		}
		return null;
		
	}

	@Override
	public List<User> findAll() throws SQLException {
		
		return userDao.findAll();
	}

	@Override
	public User findById(int id) throws SQLException {
		
		return userDao.get(id);
	}

	
	

}

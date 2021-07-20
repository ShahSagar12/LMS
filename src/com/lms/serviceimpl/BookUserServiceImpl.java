package com.lms.serviceimpl;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.lms.dao.BookUserDAO;
import com.lms.daoimpl.BookUserDAOImpl;
import com.lms.entity.BookUser;
import com.lms.exception.AlreadyTakenSixBooksException;
import com.lms.service.BookUserService;

public class BookUserServiceImpl implements BookUserService {
	
	private static final Logger LOGGER = Logger.getLogger(BookUserDAOImpl.class.getName());
	
	BookUserDAO bookUserDao=new BookUserDAOImpl();

	@Override
	public boolean save(BookUser bookUser) throws SQLException {
		if(!alreadyTakenSufficientBooks(bookUser.getUserId())) {
			bookUserDao.save(bookUser);
		}
		return true; 
	}

	@Override
	public List<BookUser> findAll() throws SQLException {
		
		return bookUserDao.findAll();
	}

	@Override
	public BookUser get(int id) throws SQLException {
		
		return bookUserDao.get(id);
	}

	@Override
	public boolean update(BookUser bookUser) throws SQLException {
		
		return bookUserDao.update(bookUser);
	}

	@Override
	public boolean delete(int id) throws SQLException {
		
		return bookUserDao.delete(id);
	}
	
	private boolean alreadyTakenSufficientBooks(Integer userId) {
		try {
			if(findAll().stream().filter(bookuser->(bookuser.getUserId()==userId)).count()>=6) {
			   throw new AlreadyTakenSixBooksException("Already taken sixs book");	
			}else {
				return false;
			}
		} catch (SQLException e) {
			LOGGER.info("Error in syntax" +e.getMessage());
		}
		return false;
	}

}

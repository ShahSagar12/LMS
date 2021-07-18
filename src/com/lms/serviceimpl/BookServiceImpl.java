package com.lms.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.lms.dao.BookDAO;
import com.lms.daoimpl.BookDAOImpl;
import com.lms.entity.Book;
import com.lms.service.BookService;

public class BookServiceImpl implements BookService{
	
	private BookDAO bookDao=new BookDAOImpl();

	@Override
	public boolean save(Book book) throws SQLException {
		
		return bookDao.save(book);
	}

	@Override
	public List<Book> findAll() throws SQLException {
		
		return bookDao.findAll();
	}

	@Override
	public Book get(int id) throws SQLException{
		
		return bookDao.get(id);
	}

	@Override
	public boolean update(Book book) throws SQLException {
		
		return bookDao.update(book);
	}

	@Override
	public boolean delete(int id) throws SQLException{
		
		return bookDao.delete(id);
	}
	

}

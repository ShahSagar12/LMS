package com.lms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lms.dao.BookDAO;
import com.lms.dbutils.MySqlConnector;
import com.lms.entity.Book;
import com.lms.model.BookOwned;


public class BookDAOImpl implements BookDAO {
	private static final Logger LOGGER = Logger.getLogger(BookDAOImpl.class.getName());

	@Override
	public boolean save(Book book) throws SQLException {
		boolean saved=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="INSERT INTO book(booktitle,bookauthor,bookpublisher,publishedyear,totalpages,bookqty,adminid)VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, book.getBookTitle());
			preparedStatement.setString(2, book.getBookAuthor());
			preparedStatement.setString(3, book.getPublisher());
			preparedStatement.setString(4, book.getPublishedYear());
			preparedStatement.setInt(5, book.getnOfPages());
			preparedStatement.setInt(6, book.getBookQty());
			preparedStatement.setInt(7, book.getAdminId());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
	        if (rs.next()) {
	            int key = rs.getInt(1);
	        }
			saved=true;
		}catch(Exception ex) {
			LOGGER.info("Error Saving book to Database "+ex.getMessage());
		}finally {
			connection.close();
		}
		return saved;
	}

	@Override
	public List<Book> findAll() throws SQLException {
		List<Book> list=new ArrayList<>();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM book";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Book book=new Book();
				book.setBookId(resultSet.getInt("bookId"));
				book.setBookTitle(resultSet.getString("booktitle"));
				book.setBookAuthor(resultSet.getString("bookauthor"));
				book.setPublisher(resultSet.getString("bookpublisher"));
				book.setPublishedYear(resultSet.getString("publishedyear"));
				book.setnOfPages(resultSet.getInt("totalpages"));
				book.setBookQty(resultSet.getInt("bookqty"));
				book.setAdminId(resultSet.getInt("adminid"));
				list.add(book);
			}
		} catch (Exception ex) {
			LOGGER.info("Error Getting All book "+ex.getMessage());
		}finally {
			connection.close();
		}

		return list;
	}

	@Override
	public Book get(int id) throws SQLException {
		Book book=new Book();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM book WHERE bookid=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				book.setBookId(resultSet.getInt("bookId"));
				book.setBookTitle(resultSet.getString("booktitle"));
				book.setBookAuthor(resultSet.getString("bookauthor"));
				book.setPublisher(resultSet.getString("bookpublisher"));
				book.setPublishedYear(resultSet.getString("publishedyear"));
				book.setBookQty(resultSet.getInt("bookqty"));
				book.setnOfPages(resultSet.getInt("totalpages"));
				book.setAdminId(resultSet.getInt("adminid"));
			}
		} catch (Exception ex) {
			LOGGER.info("Error getting book by id "+ex.getMessage());
		}finally {
			connection.close();
		}
		return book;

	}
	
	@Override
	public Book getByBookTitle(String title) throws SQLException {
		Book book=new Book();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM book WHERE booktitle=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,title);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				book.setBookId(resultSet.getInt("bookId"));
				book.setBookTitle(resultSet.getString("booktitle"));
				book.setBookAuthor(resultSet.getString("bookauthor"));
				book.setPublisher(resultSet.getString("bookpublisher"));
				book.setPublishedYear(resultSet.getString("publishedyear"));
				book.setBookQty(resultSet.getInt("bookqty"));
				book.setnOfPages(resultSet.getInt("totalpages"));
				book.setAdminId(resultSet.getInt("adminid"));
			}
		} catch (Exception ex) {
			LOGGER.info("Error getting book by booktitle "+ex.getMessage());
		}finally {
			connection.close();
		}
		return book;
	}

	@Override
	public boolean update(Book book) throws SQLException {
		boolean exists=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="UPDATE book SET booktitle=?, bookauthor=?, bookpublisher=?, publishedyear=?,bookqty=?,totalpages=?, adminid=? where bookid="+book.getBookId();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getBookTitle());
			preparedStatement.setString(2, book.getBookAuthor());
			preparedStatement.setString(3, book.getPublisher());
			preparedStatement.setString(4, book.getPublishedYear());
			preparedStatement.setInt(5, book.getBookQty());
			preparedStatement.setInt(6, book.getnOfPages());
			preparedStatement.setInt(7, book.getAdminId());
			preparedStatement.executeUpdate();
			exists=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:BOOK UPDATE "+ex.getMessage());
		}finally {
			connection.close();
		}
		return exists;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean exists=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="DELETE book WHERE id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeQuery();
			exists=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:Book delete :"+ex.getMessage());
		}finally {
			connection.close();
		}
		return exists;
	}

	@Override
	public List<BookOwned> getBookOwnedByUser(int userId) throws SQLException {
		List<BookOwned> list=new ArrayList<>();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT bu.id,bu.bookid,bu.bookstatus,bu.booktakenat,bu.booktakenfor,bk.booktitle,bk.bookauthor FROM bookuser bu INNER JOIN book bk on bu.bookid=bk.bookid WHERE bu.userid="+userId;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				BookOwned bookowned=new BookOwned();
				bookowned.setId(resultSet.getInt("id"));
				bookowned.setBookId(resultSet.getInt("bookid"));
				bookowned.setBookTitle(resultSet.getString("booktitle"));
				bookowned.setBookAuthor(resultSet.getString("bookauthor"));
				bookowned.setBookTakenAt(resultSet.getString("booktakenat"));
				bookowned.setBookTakenFor(Integer.parseInt(resultSet.getString("booktakenfor")));
				bookowned.setUserId(userId);
				list.add(bookowned);
			}
		} catch (Exception ex) {
			LOGGER.info("Error Getting All book "+ex.getMessage());
		}finally {
			connection.close();
		}
		return list;
	}

}

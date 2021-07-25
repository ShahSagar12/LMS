package com.lms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.lms.dao.BookUserDAO;
import com.lms.dbutils.MySqlConnector;
import com.lms.entity.BookUser;

public class BookUserDAOImpl implements BookUserDAO{
	
	private static final Logger LOGGER = Logger.getLogger(BookUserDAOImpl.class.getName());
	
	@Override
	public boolean save(BookUser bookUser) throws SQLException {
		boolean saved=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="INSERT INTO bookuser(bookid,userid,bookstatus,booktakenat,booktakenfor)VALUES(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, bookUser.getBookId());
			preparedStatement.setInt(2, bookUser.getUserId());
			preparedStatement.setString(3, bookUser.getBookStatus());
			preparedStatement.setString(4, formatter(new Date()));
			preparedStatement.setInt(5, bookUser.getBookTakenFor());
			preparedStatement.executeUpdate();
			saved=true;
		}catch(Exception ex) {
			LOGGER.info("Error Saving book to Database "+ex.getMessage());
		}finally {
			connection.close();
		}
		return saved;
	}

	@Override
	public List<BookUser> findAll() throws SQLException {
		List<BookUser> list=new ArrayList<>();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM bookuser";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				BookUser bookUser=new BookUser();
				bookUser.setId(Integer.parseInt(resultSet.getString("id")));
				bookUser.setBookId(resultSet.getInt("bookid"));
				bookUser.setUserId(Integer.parseInt(resultSet.getString("userid")));
				bookUser.setBookTakenAt(resultSet.getString("booktakenat"));
				bookUser.setBookTakenFor(Integer.parseInt(resultSet.getString("booktakenfor")));
				bookUser.setBookStatus(resultSet.getString("bookstatus"));
				list.add(bookUser);
			}
		} catch (Exception ex) {
			LOGGER.info("Error Getting All book "+ex.getMessage());
		}finally {
			connection.close();
		}
		return list;
	}

	@Override
	public BookUser get(int id) throws SQLException {
		BookUser bookUser=new BookUser();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM bookuser WHERE id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				bookUser.setId(resultSet.getInt("id"));
				bookUser.setBookId(resultSet.getInt("bookid"));
				bookUser.setUserId(Integer.parseInt(resultSet.getString("userid")));
				bookUser.setBookTakenAt(resultSet.getString("booktakenat"));
				bookUser.setBookTakenFor(Integer.parseInt(resultSet.getString("booktakenfor")));
			}
		} catch (Exception ex) {
			LOGGER.info("Error getting book by id "+ex.getMessage());
		}finally {
			connection.close();
		}
		return bookUser;
	}
	@Override
	public BookUser getByUserIdAndBook(int userId,int bookId) throws SQLException {
		BookUser bookUser=null;
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM bookuser WHERE bookId=? and userId=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,bookId);
			preparedStatement.setInt(2,userId);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				bookUser=new BookUser();
				bookUser.setId(resultSet.getInt("id"));
				bookUser.setBookId(resultSet.getInt("bookid"));
				bookUser.setUserId(Integer.parseInt(resultSet.getString("userid")));
				bookUser.setBookTakenAt(resultSet.getString("booktakenat"));
				bookUser.setBookTakenFor(Integer.parseInt(resultSet.getString("booktakenfor")));
			}
		} catch (Exception ex) {
			LOGGER.info("Error getting order by id and userId"+ex.getMessage());
		}finally {
			connection.close();
		}
		return bookUser;
	}
	@Override
	public boolean update(BookUser bookUser) throws SQLException {
		boolean exists=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="UPDATE bookuser SET bookid=?, userid=?, bookstatus=?, booktakenat=?, booktakenfor=? where id="+bookUser.getId();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, bookUser.getBookId());
			preparedStatement.setInt(2, bookUser.getUserId());
			preparedStatement.setString(3, bookUser.getBookStatus());
			preparedStatement.setString(4, bookUser.getBookTakenAt());
			preparedStatement.setInt(5, bookUser.getBookTakenFor());
			preparedStatement.executeUpdate();
			exists=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:BookUser UPDATE "+ex.getMessage());
		}finally {
			connection.close();
		}
		return exists;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean exists=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="DELETE bookuser WHERE id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeQuery();
			exists=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:BookUser delete :"+ex.getMessage());
		}finally {
			connection.close();
		}
		return exists;
	}
	
	private String formatter(Date date) {
		
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}

}

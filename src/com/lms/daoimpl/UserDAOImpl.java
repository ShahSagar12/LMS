package com.lms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lms.dao.UserDAO;
import com.lms.dbutils.MySqlConnector;
import com.lms.entity.User;
import com.lms.model.BookOwned;
import com.lms.model.dtos.BookRequestDtos;

public class UserDAOImpl implements UserDAO {
	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());

	@Override
	public boolean save(User user) throws SQLException {
		boolean saved=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="INSERT INTO user(firstname,lastname,email,password,roleid)VALUES(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUserPassword());
			preparedStatement.setInt(5, user.getRoleid());
			preparedStatement.executeUpdate();
			saved=true;
		}catch(Exception ex) {
			LOGGER.info("Error:"+ex.getMessage());
		}finally {
			connection.close();
		}
		return saved;
	}

	@Override
	public List<User> findAll() throws SQLException {
		List<User> list=new ArrayList<>();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM user";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				User user=new User();
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setUserPassword(resultSet.getString("password"));
				user.setRoleid(resultSet.getInt("roleid"));
				list.add(user);
			}
		} catch (Exception ex) {
			LOGGER.info("ERROR: finding all user"+ex.getMessage());
		}finally {
			connection.close();
		}

		return list;
	}

	@Override
	public User get(int id) throws SQLException {
		User user=new User();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM user WHERE id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setUserPassword(resultSet.getString("password"));
				user.setRoleid(resultSet.getInt("roleid"));
			}
		} catch (Exception ex) {
			LOGGER.info("ERROR: get user by id "+ex.getMessage());
		}
		finally {
			connection.close();
		}
		return user;
	}
	
	@Override
	public User getByEmail(String email) throws SQLException {
		User user=new User();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM user WHERE email=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,email);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setUserPassword(resultSet.getString("password"));
				user.setRoleid(resultSet.getInt("roleid"));
			}
		} catch (Exception ex) {
			LOGGER.info("ERROR: get user by id "+ex.getMessage());
		}
		finally {
			connection.close();
		}
		return user;
	}

	@Override
	public boolean update(User user) throws SQLException {
		boolean updated=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="Update user set id=?,firstname=?,lastname=?,email=?,password=?,roleid=? where id="+user.getId();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getUserPassword());
			preparedStatement.setInt(6, user.getRoleid());
			preparedStatement.executeUpdate();
			updated=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:Updating user :"+ex.getMessage());
		}finally {
			connection.close();
		}
		return updated;
	}
	
	@Override
	public List<BookRequestDtos> getBookOwnerByOfAdmin(int adminId) throws SQLException {
		List<BookRequestDtos> list=new ArrayList<>();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT bu.bookstatus,bu.booktakenat,bu.booktakenfor,bu.id,bk.booktitle,bk.bookauthor,u.firstname,u.lastname FROM bookuser bu INNER JOIN book bk INNER JOIN user u on bu.userid=u.id and bu.bookid=bk.bookid";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				BookRequestDtos bookRequestDtos=new BookRequestDtos();
				bookRequestDtos.setBookUserId(resultSet.getInt("id"));
				bookRequestDtos.setBookTitle(resultSet.getString("booktitle"));
				bookRequestDtos.setBookAuthor(resultSet.getString("bookauthor"));
				bookRequestDtos.setBookTakenAt(resultSet.getString("booktakenat"));
				bookRequestDtos.setBookTakenFor(Integer.parseInt(resultSet.getString("booktakenfor")));
				bookRequestDtos.setBookStatus(resultSet.getString("bookstatus"));
				bookRequestDtos.setStudentName(resultSet.getString("firstname")+" "+resultSet.getString("lastname"));
				list.add(bookRequestDtos);
			}
		} catch (Exception ex) {
			LOGGER.info("Error Getting All book "+ex.getMessage());
		}finally {
			connection.close();
		}
		return list;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean exists=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="DELETE from user WHERE id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			exists=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:Deleting user :"+ex.getMessage());
		}finally {
			connection.close();
		}
		return exists;
	}

	

	

}

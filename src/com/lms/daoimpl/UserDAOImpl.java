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
		String sql="Update user set id=? AND firstname=? AND lastname=? AND email=? AND password=? AND roleid=?";
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
	public boolean delete(int id) throws SQLException {
		boolean exists=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="DELETE user WHERE id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeQuery();
			exists=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:Deleting user :"+ex.getMessage());
		}finally {
			connection.close();
		}
		return exists;
	}

	

	

}

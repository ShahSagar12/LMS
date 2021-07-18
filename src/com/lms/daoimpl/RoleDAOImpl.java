package com.lms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lms.dao.RoleDAO;
import com.lms.dbutils.MySqlConnector;
import com.lms.entity.Role;
import com.lms.entity.User;

public class RoleDAOImpl implements RoleDAO{

	private static final Logger LOGGER = Logger.getLogger(RoleDAOImpl.class.getName());
	@Override
	public boolean save(Role role) throws SQLException {
		boolean saved=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="INSERT INTO role(role)VALUES(?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, role.getRoleType());
			preparedStatement.executeUpdate();
			saved=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR:saving role :"+ex.getMessage());
		}finally {
			connection.close();
		}
		return saved;
	}

	@Override
	public List<Role> findAll() throws SQLException {
		List<Role> roles=new ArrayList<>();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM role";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Role role=new Role();
				role.setRoleid(resultSet.getInt("roleid"));
				role.setRoleType(resultSet.getString("role"));
				roles.add(role);
			}
		} catch (Exception ex) {
			LOGGER.info("ERROR: find all roles"+ex.getMessage());
		}finally {
			connection.close();
		}
		return roles;
	}

	@Override
	public Role get(int id) throws SQLException {
		Role role=new Role();
		Connection connection=MySqlConnector.connectToDB();
		String sql="SELECT * FROM role";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				role.setRoleid(resultSet.getInt("roleid"));
				role.setRoleType(resultSet.getString("role"));
			}
		} catch (Exception ex) {
			LOGGER.info("ERROR: find a role"+ex.getMessage());
		}finally {
			connection.close();
		}
		return role;
	}

	@Override
	public boolean update(Role role) throws SQLException {
		boolean updated=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="Update role set roleid=? AND role=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, role.getRoleid());
			preparedStatement.setString(2, role.getRoleType());
			preparedStatement.executeUpdate();
			updated=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR: update role "+ex.getMessage());
		}finally {
			connection.close();
		}
		return updated;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		boolean exists=false;
		Connection connection=MySqlConnector.connectToDB();
		String sql="DELETE role WHERE roleid=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeQuery();
			exists=true;
		}catch(Exception ex) {
			LOGGER.info("ERROR: Delete role"+ex.getMessage());
		}finally {
			connection.close();
		}
		return exists;
	}

}

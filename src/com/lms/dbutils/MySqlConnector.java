package com.lms.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import com.lms.daoimpl.RoleDAOImpl;

public class MySqlConnector {
	private static final Logger LOGGER = Logger.getLogger(MySqlConnector.class.getName());
	public static void main(String[] args) {
		connectToDB();		
	}
	public static Connection connectToDB() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?serverTimezone=UTC","root","password");
		} catch (Exception ex) {
			LOGGER.info("ERROR: CONNECTING DATABASE"+ex.getMessage());
		}
		return connection;
	}

}

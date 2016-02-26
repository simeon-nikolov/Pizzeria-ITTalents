package database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/pizzeria?autoReconnect=true&useSSL=false";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "password";
	
	private static Connection instance;

	public static Connection getConnection() {
		if (instance == null) {
			try {
				Class.forName(JDBC_DRIVER);
				instance = DriverManager.getConnection(DB_URL, USER, PASS);
				instance.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
}

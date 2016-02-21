package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import pizzeria.account.User;
import exceptions.InvalidArgumentValueException;

public class UserDb {
	private Connection conn;
	
	public UserDb(Connection conn) throws InvalidArgumentValueException {
		if (conn == null) {
			throw new InvalidArgumentValueException("Database connection is null!");
		}
		
		this.conn = conn;
	}
	
	public void addUser(User user) {
		String sqlAccountInsert = "INSERT INTO `pizzeria`.`account` (`username`, `password`, `email`, `isAdmin`) VALUES "
				+ "(?, ?, ?, ?);";
		String sqlUserInsert = "";
		
		try {
			PreparedStatement stmtAcc = conn.prepareStatement(sqlAccountInsert);
			stmtAcc.setString(1, user.getUsername());
			stmtAcc.setString(2, user.getPassword());
			stmtAcc.setString(3, user.getEmail());
			stmtAcc.setBoolean(4, false);
			ResultSet rs = stmtAcc.executeQuery();
			rs.next();
			int idAccount = rs.getInt("idAccount");
			
			conn.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void editUser(int id, User admin) {
		String sql = "UPDATE `pizzeria`.`account` SET `username`=?, `password`=?, `email`=? WHERE `idAccount`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
			stmt.setString(3, admin.getEmail());
			stmt.setInt(4, id);
			stmt.executeUpdate();
			conn.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void removeUser(int id) {
		String sql = "DELETE FROM `pizzeria`.`account` WHERE `idAccount`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			conn.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public User getUserById(int id) {
		User admin = null;
		String sql = "SELECT * FROM `pizzeria`.`account` WHERE `idAccount`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			admin = new User(
					rs.getInt("idAccount"), 
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"));
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return admin;
	}
	
	public User getUserByUsername(String username) {
		User admin = null;
		String sql = "SELECT * FROM `pizzeria`.`account` WHERE `username`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			admin = new User(
					rs.getInt("idAccount"), 
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"));
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return admin;
	}
	
	public User getUserByEmail(String email) {
		User admin = null;
		String sql = "SELECT * FROM `pizzeria`.`account` WHERE `email`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			admin = new User(
					rs.getInt("idAccount"), 
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"));
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return admin;
	}
	
	public Set<User> getAllUser() {
		Set<User> admins = new HashSet<User>();
		String sql = "SELECT * FROM `pizzeria`.`account`;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			
			while(rs.next()) {
				User admin = new User(
						rs.getInt("idAccount"), 
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("email"));
				admins.add(admin);
			}
			
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return admins;
	}
}

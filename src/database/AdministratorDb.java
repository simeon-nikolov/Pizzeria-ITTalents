package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import pizzeria.account.Administrator;
import exceptions.InvalidArgumentValueException;

public class AdministratorDb {
	private Connection conn;
	
	public AdministratorDb(Connection conn) throws InvalidArgumentValueException {
		if (conn == null) {
			throw new InvalidArgumentValueException("Database connection is null!");
		}
		
		this.conn = conn;
	}
	
	public void addAdministrator(Administrator admin) {
		String sql = "INSERT INTO `pizzeria`.`account` (`username`, `password`, `email`, `isAdmin`) VALUES "
				+ "(?, ?, ?, ?);";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
			stmt.setString(3, admin.getEmail());
			stmt.setBoolean(4, true);
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
	
	public void editAdministrator(int id, Administrator admin) {
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
	
	public void removeAdministrator(int id) {
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
	
	public Administrator getAdministratorById(int id) {
		Administrator admin = null;
		String sql = "SELECT * FROM `pizzeria`.`account` WHERE `idAccount`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			admin = new Administrator(
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
	
	public Administrator getAdministratorByUsername(String username) {
		Administrator admin = null;
		String sql = "SELECT * FROM `pizzeria`.`account` WHERE `username`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			admin = new Administrator(
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
	
	public Administrator getAdministratorByEmail(String email) {
		Administrator admin = null;
		String sql = "SELECT * FROM `pizzeria`.`account` WHERE `email`=?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			admin = new Administrator(
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
	
	public Set<Administrator> getAllAdministrator() {
		Set<Administrator> admins = new HashSet<Administrator>();
		String sql = "SELECT * FROM `pizzeria`.`account`;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			
			while(rs.next()) {
				Administrator admin = new Administrator(
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

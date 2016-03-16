package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pizzeria.account.Administrator;
import exceptions.InvalidArgumentValueException;

public class AdministratorDb extends DataAccessObject {
	private Connection connecion = super.getConnection();
	
	public void addAdministrator(Administrator admin) {
		String sql = "INSERT INTO `pizzeria`.`account` (`username`, `password`, `email`, `isAdmin`) VALUES "
				+ "(?, ?, ?, ?);";
		
		try {
			PreparedStatement stmt = this.connecion.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
			stmt.setString(3, admin.getEmail());
			stmt.setBoolean(4, true);
			stmt.executeUpdate();
			connecion.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connecion.rollback();
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
			PreparedStatement stmt = this.connecion.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
			stmt.setString(3, admin.getEmail());
			stmt.setInt(4, id);
			stmt.executeUpdate();
			connecion.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connecion.rollback();
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
			PreparedStatement stmt = this.connecion.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			connecion.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connecion.rollback();
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
			PreparedStatement stmt = this.connecion.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			connecion.commit();
			rs.next();
			admin = new Administrator(
					rs.getInt("idAccount"), 
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"));
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connecion.rollback();
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
			PreparedStatement stmt = this.connecion.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			connecion.commit();
			rs.next();
			admin = new Administrator(
					rs.getInt("idAccount"), 
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"));
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connecion.rollback();
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
			PreparedStatement stmt = this.connecion.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			connecion.commit();
			rs.next();
			admin = new Administrator(
					rs.getInt("idAccount"), 
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"));
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connecion.rollback();
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
	
	public List<Administrator> getAllAdministrators() {
		List<Administrator> admins = new ArrayList<Administrator>();
		String sql = "SELECT * FROM `pizzeria`.`account`;";
		
		try {
			PreparedStatement stmt = this.connecion.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			connecion.commit();
			
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
				connecion.rollback();
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
	
	public boolean isAdmin(String username) {
		String sql = "SELECT `is_admin` FROM `pizzeria`.`account` WHERE `username` = ?;";
		boolean isAdmin = false;
		
		if (username != null) {
			try {
				PreparedStatement ps = this.connecion.prepareStatement(sql);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				connecion.commit();
				rs.next();
				isAdmin = rs.getBoolean("is_admin");
				System.out.println("Success!");
			} catch (SQLException e) {	
				try {
					connecion.rollback();
					System.out.println("Transaction ROLLBACK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		
		return isAdmin;
	}
}

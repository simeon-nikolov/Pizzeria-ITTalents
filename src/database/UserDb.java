package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		String sqlSelectAccId = "SELECT `idAccount` FROM `pizzeria`.`account` WHERE `username` = ?;";
		String sqlUserInsert = "INSERT INTO `pizzeria`.`user` (`first_name`, `last_name`, `address`, `phone_number`, `idAccount`) VALUES "
				+ "(?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement stmtAcc = conn.prepareStatement(sqlAccountInsert);
			stmtAcc.setString(1, user.getUsername());
			stmtAcc.setString(2, user.getPassword());
			stmtAcc.setString(3, user.getEmail());
			stmtAcc.setBoolean(4, false);
			stmtAcc.executeUpdate();
			PreparedStatement stmtAccId = conn.prepareStatement(sqlSelectAccId);
			stmtAccId.setString(1, user.getUsername());
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idAccount = rs.getInt("idAccount");
			PreparedStatement stmtUser = conn.prepareStatement(sqlUserInsert);
			stmtUser.setString(1, user.getFirstName());
			stmtUser.setString(2, user.getLastName());
			stmtUser.setString(3, user.getAddress());
			stmtUser.setString(4, user.getPhoneNumber());
			stmtUser.setInt(5, idAccount);
			stmtUser.executeUpdate();
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
	
	public void editUser(int idUser, User user) {
		String sqlSelectUserId = "SELECT `idAccount` FROM `pizzeria`.`user` WHERE `idUser` = ?;";
		String sqlAccountUpdate = "UPDATE `pizzeria`.`account` SET `username`=?, `password`=?, `email`=? WHERE `idAccount`=?;";
		String sqlUserUpdate = "UPDATE `pizzeria`.`user` SET `first_name`=?, `last_name`=?, `address`=?, `phone_number`=? WHERE `idUser`=?;";
		
		try {
			PreparedStatement stmtAccId = conn.prepareStatement(sqlSelectUserId);
			stmtAccId.setInt(1, idUser);
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idAccount = rs.getInt("idAccount");
			
			PreparedStatement stmtAcc = conn.prepareStatement(sqlAccountUpdate);
			stmtAcc.setString(1, user.getUsername());
			stmtAcc.setString(2, user.getPassword());
			stmtAcc.setString(3, user.getEmail());
			stmtAcc.setInt(4, idAccount);
			stmtAcc.executeUpdate();
			
			PreparedStatement stmtUser = conn.prepareStatement(sqlUserUpdate);
			stmtUser.setString(1, user.getFirstName());
			stmtUser.setString(2, user.getLastName());
			stmtUser.setString(3, user.getAddress());
			stmtUser.setString(4, user.getPhoneNumber());
			stmtUser.setInt(5, idUser);
			stmtUser.executeUpdate();
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
	
	public void removeUser(int idUser) {
		String sqlSelectUserId = "SELECT `idAccount` FROM `pizzeria`.`user` WHERE `idUser` = ?;";
		String sqlAccountUpdate = "DELETE FROM `pizzeria`.`account` WHERE `idAccount`=?;";
		String sqlUserUpdate = "DELETE FROM `pizzeria`.`user` WHERE `idUser`=?;";
		
		try {
			PreparedStatement stmtAccId = conn.prepareStatement(sqlSelectUserId);
			stmtAccId.setInt(1, idUser);
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idAccount = rs.getInt("idAccount");
			
			PreparedStatement stmtAcc = conn.prepareStatement(sqlAccountUpdate);
			stmtAcc.setInt(1, idAccount);
			stmtAcc.executeUpdate();
			
			PreparedStatement stmtUser = conn.prepareStatement(sqlUserUpdate);
			stmtUser.setInt(1, idUser);
			stmtUser.executeUpdate();
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
}

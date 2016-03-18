package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pizzeria.account.User;
import exceptions.InvalidArgumentValueException;

public class UserDb extends DataAccessObject implements IUserDao {
	private Connection connection = super.getConnection();
	
	@Override
	public int addUser(User user) {
		String sqlAccountInsert = "INSERT INTO `pizzeria`.`account` (`username`, `password`, `email`, `isAdmin`) VALUES "
				+ "(?, ?, ?, ?);";
		String sqlSelectAccId = "SELECT `idAccount` FROM `pizzeria`.`account` WHERE `username` = ?;";
		String sqlUserInsert = "INSERT INTO `pizzeria`.`user` (`first_name`, `last_name`, `address`, `phone_number`, `idAccount`) VALUES "
				+ "(?, ?, ?, ?, ?);";
		int userId = 0;
		
		try {
			PreparedStatement stmtAcc = connection.prepareStatement(sqlAccountInsert);
			stmtAcc.setString(1, user.getUsername());
			stmtAcc.setString(2, user.getPassword());
			stmtAcc.setString(3, user.getEmail());
			stmtAcc.setBoolean(4, false);
			stmtAcc.executeUpdate();
			PreparedStatement stmtAccId = connection.prepareStatement(sqlSelectAccId);
			stmtAccId.setString(1, user.getUsername());
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idAccount = rs.getInt("idAccount");
			PreparedStatement stmtUser = connection.prepareStatement(sqlUserInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			stmtUser.setString(1, user.getFirstName());
			stmtUser.setString(2, user.getLastName());
			stmtUser.setString(3, user.getAddress());
			stmtUser.setString(4, user.getPhoneNumber());
			stmtUser.setInt(5, idAccount);
			stmtUser.executeUpdate();
			rs = stmtUser.getGeneratedKeys();
			rs.next();
			userId = rs.getInt(1);
			connection.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return userId;
	}
	
	@Override
	public void editUser(int idUser, User user) {
		String sqlSelectAccountId = "SELECT `idAccount` FROM `pizzeria`.`user` WHERE `idUser` = ?;";
		String sqlAccountUpdate = "UPDATE `pizzeria`.`account` SET `username`=?, `password`=?, `email`=? WHERE `idAccount`=?;";
		String sqlUserUpdate = "UPDATE `pizzeria`.`user` SET `first_name`=?, `last_name`=?, `address`=?, `phone_number`=? WHERE `idUser`=?;";
		
		try {
			PreparedStatement stmtAccId = connection.prepareStatement(sqlSelectAccountId);
			stmtAccId.setInt(1, idUser);
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idAccount = rs.getInt("idAccount");
			
			PreparedStatement stmtAcc = connection.prepareStatement(sqlAccountUpdate);
			stmtAcc.setString(1, user.getUsername());
			stmtAcc.setString(2, user.getPassword());
			stmtAcc.setString(3, user.getEmail());
			stmtAcc.setInt(4, idAccount);
			stmtAcc.executeUpdate();
			
			PreparedStatement stmtUser = connection.prepareStatement(sqlUserUpdate);
			stmtUser.setString(1, user.getFirstName());
			stmtUser.setString(2, user.getLastName());
			stmtUser.setString(3, user.getAddress());
			stmtUser.setString(4, user.getPhoneNumber());
			stmtUser.setInt(5, idUser);
			stmtUser.executeUpdate();
			connection.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeUser(int idUser) {
		String sqlSelectAccountId = "SELECT `idAccount` FROM `pizzeria`.`user` WHERE `idUser` = ?;";
		String sqlAccountUpdate = "DELETE FROM `pizzeria`.`account` WHERE `idAccount`=?;";
		String sqlUserUpdate = "DELETE FROM `pizzeria`.`user` WHERE `idUser`=?;";
		
		try {
			PreparedStatement stmtAccId = connection.prepareStatement(sqlSelectAccountId);
			stmtAccId.setInt(1, idUser);
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idAccount = rs.getInt("idAccount");
			
			PreparedStatement stmtAcc = connection.prepareStatement(sqlAccountUpdate);
			stmtAcc.setInt(1, idAccount);
			stmtAcc.executeUpdate();
			
			PreparedStatement stmtUser = connection.prepareStatement(sqlUserUpdate);
			stmtUser.setInt(1, idUser);
			stmtUser.executeUpdate();
			connection.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public User getUserById(int idUser) {
		User user = null;
		String sqlSelectUser = "SELECT * FROM `pizzeria`.`user` u "
				+ "JOIN `pizzeria`.`account` a ON u.idAccount = a.idAccount "
				+ "WHERE `idUser` = ?;";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sqlSelectUser);
			stmt.setInt(1, idUser);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			rs.next();
			user = new User(
					rs.getInt("idUser"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("address"),
					rs.getString("phone_number")
				);
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = null;
		String sqlSelectUser = "SELECT * FROM `pizzeria`.`user` u "
				+ "JOIN `pizzeria`.`account` a ON u.idAccount = a.idAccount "
				+ "WHERE a.`username` = ?;";
		
		if (username != null) {
			try {
				PreparedStatement stmt = connection.prepareStatement(sqlSelectUser);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				connection.commit();
				rs.next();
				user = new User(
						rs.getInt("idUser"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("address"),
						rs.getString("phone_number")
					);
				System.out.println("Success!");
			} catch (SQLException e) {	
				try {
					connection.rollback();
					System.out.println("Transaction ROLLBACK");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (InvalidArgumentValueException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		User user = null;
		String sqlSelectUser = "SELECT * FROM `pizzeria`.`user` u "
				+ "JOIN `pizzeria`.`account` a ON u.idAccount = a.idAccount "
				+ "WHERE a.`email` = ?;";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sqlSelectUser);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			rs.next();
			user = new User(
					rs.getInt("idUser"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("address"),
					rs.getString("phone_number")
				);
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sqlSelectUser = "SELECT * FROM `pizzeria`.`user` u "
				+ "JOIN `pizzeria`.`account` a ON u.idAccount = a.idAccount;";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sqlSelectUser);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			
			while(rs.next()) {
				User user = new User(
						rs.getInt("idUser"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("address"),
						rs.getString("phone_number")
					);
				users.add(user);
			}
			
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}

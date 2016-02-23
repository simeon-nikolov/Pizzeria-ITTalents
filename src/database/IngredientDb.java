package database;

import java.sql.*;

import exceptions.InvalidArgumentValueException;
import pizzeria.menu.Ingredient;

public class IngredientDb {

	private Connection conn;
	
	public IngredientDb(Connection conn) throws InvalidArgumentValueException {
		if (conn != null)
			this.conn = conn;
		else
			throw new InvalidArgumentValueException("Database connection is null");
	}
	
	public void addIngredient(Ingredient ing) {
		String sql = "INSERT INTO pizzeria.ingredient (name) VALUES "
				+ "(?);";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ing.getName());
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
	
	public void editIngredient(int id, Ingredient ing) {
		String sql = "UPDATE pizzeria.ingredient SET name = ?, WHERE idIngredient = ?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ing.getName());
			stmt.setInt(2, id);
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
	
	public void removeIngredient(int id) {
		String sql = "DELETE FROM pizzeria.ingredient WHERE idIngredient = ?;";
		
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
	
	public Ingredient getIngredientByname(String name) {
		Ingredient ingredient = null;
		String sql = "SELECT * FROM pizzeria.ingredient WHERE name = ?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			ingredient = new Ingredient(
					rs.getInt("idIngredient"),
					rs.getString("name"));
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
		return ingredient;
	}
}

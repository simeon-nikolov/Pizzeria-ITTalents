package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pizzeria.menu.Ingredient;

public class IngredientDb extends DataAccessObject {

	private Connection connection = super.getConnection();
	
	public void addIngredient(Ingredient ing) {
		String sql = "INSERT INTO pizzeria.ingredient (name) VALUES "
				+ "(?);";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, ing.getName());
			stmt.executeUpdate();
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
	
	public void editIngredient(int id, Ingredient ing) {
		String sql = "UPDATE pizzeria.ingredient SET name = ?, WHERE idIngredient = ?;";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, ing.getName());
			stmt.setInt(2, id);
			stmt.executeUpdate();
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
	
	public void removeIngredient(int id) {
		String sql = "DELETE FROM pizzeria.ingredient WHERE idIngredient = ?;";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
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
	
	public Ingredient getIngredientByname(String name) {
		Ingredient ingredient = null;
		String sql = "SELECT * FROM pizzeria.ingredient WHERE name = ?;";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			rs.next();
			ingredient = new Ingredient(
					rs.getInt("idIngredient"),
					rs.getString("name"));
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
		return ingredient;
	}
}

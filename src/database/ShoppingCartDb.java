package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pizzeria.menu.IProduct;
import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import exceptions.InvalidArgumentValueException;

public class ShoppingCartDb extends DataAccessObject {
	private Connection connection = super.getConnection();
	
	public void addProductToShoppingCart(int userId, int productId, int quantity) {
		String sqlInserToShoppingCart = "INSERT INTO `pizzeria`.`Products_In_Carts` (`Product_idProduct`, `User_idUser`, `quantity`) VALUES "
				+ "(?, ?, ?);";

		try {
			PreparedStatement stmtInsert = connection.prepareStatement(sqlInserToShoppingCart);
			stmtInsert.setInt(1, productId);
			stmtInsert.setInt(2, userId);
			stmtInsert.setInt(3, quantity);
			stmtInsert.executeUpdate();
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

	public void removeProductFromShoppingCart(int userId, int productId) {
		String sqlDeleteInShoppingCart = "DELETE FROM `pizzeria`.`Products_In_Carts` WHERE `Product_idProduct`=? AND `User_idUser`=?;";

		try {
			PreparedStatement stmtDelete = connection.prepareStatement(sqlDeleteInShoppingCart);
			stmtDelete.setInt(1, productId);
			stmtDelete.setInt(2, userId);
			stmtDelete.executeUpdate();
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
	
	public void emptyShoppingCart(int userId) {
		String sqlEmptyShoppingCart = "DELETE FROM `pizzeria`.`Products_In_Carts` WHERE `User_idUser`=?;";

		try {
			PreparedStatement stmtDelete = connection.prepareStatement(sqlEmptyShoppingCart);
			stmtDelete.setInt(1, userId);
			stmtDelete.executeUpdate();
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
	
	public List<IProduct> getProducts(int userId) {
		List<IProduct> products = new ArrayList<IProduct>();
		
		try {
			List<Pizza> pizzas = this.getPizzasByCartId(userId);
			
			for (Pizza pizza : pizzas) {
				products.add(pizza);
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
		
		return products;
	}
	
	private List<Pizza> getPizzasByCartId(int id) throws SQLException, InvalidArgumentValueException {
		String sqlSelectPizzas = "SELECT * FROM `pizzeria`.`Product` pr "
				+ "JOIN `pizzeria`.`Products_In_Carts` c ON (pr.`idProduct` = c.`Product_idProduct`) "
				+ "JOIN `pizzeria`.`Food` f ON (f.`Product_idProduct` = pr.`idProduct`) "
				+ "JOIN `pizzeria`.`Pizza` pi ON (pi.`Food_idFood` = f.`idFood`) "
				+ "WHERE c.`User_idUser` = ?;";
		PreparedStatement stmtSelectPizzas = connection.prepareStatement(sqlSelectPizzas);
		stmtSelectPizzas.setInt(1, id);
		ResultSet rs = stmtSelectPizzas.executeQuery();
		List<Pizza> pizzas = new ArrayList<Pizza>(); 
		
		while(rs.next()) {
			Pizza pizza = new Pizza(
					rs.getInt("idProduct"),
					rs.getString("name"),
					rs.getDouble("price"),
					rs.getString("image"),
					rs.getInt("grammage"),
					rs.getInt("size")
				);
			
			List<Ingredient> ingredients = new PizzaDb().getAllPizzaIngredients(pizza);
			
			for (Ingredient ingredient : ingredients) {
				pizza.addIngredients(ingredient);
			}
			
			pizzas.add(pizza);
		}
		
		return pizzas;
	}
}

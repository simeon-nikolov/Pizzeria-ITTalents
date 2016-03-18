package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pizzeria.menu.IProduct;
import pizzeria.menu.Pizza;
import exceptions.InvalidArgumentValueException;

public class ShoppingCartDb extends DataAccessObject implements IShoppingCartDao {
	private Connection connection = super.getConnection();
	
	@Override
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

	@Override
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
	
	@Override
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
	
	@Override
	public List<IProduct> getProducts(int userId) {
		List<IProduct> products = new ArrayList<IProduct>();
		
		try {
			List<Pizza> pizzas = new PizzaDb().getPizzasByCartId(userId);
			
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
}

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pizzeria.menu.IProduct;

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
		String sqlDeleteInShoppingCart = "DELETE FROM `pizzeria`.`Products_In_Carts` WHERE `pizzeria`.`Product_idProduct`=? AND `pizzeria`.`User_idUser`=?;";

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
		String sqlEmptyShoppingCart = "DELETE FROM `pizzeria`.`Products_In_Carts` WHERE `pizzeria`.`User_idUser`=?;";

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
		String sqlSelectProducts = "SELECT * FROM `pizzeria`.`Product` p "
				+ "JOIN `pizzeria`.`Products_In_Carts` s ON (p.`idProduct` = s.`idProduct`) "
				+ "WHERE s.`User_idUser` = ?;";
		List<IProduct> products = new ArrayList<IProduct>();
		
		try {
			PreparedStatement  stmtSelectProducts = connection.prepareStatement(sqlSelectProducts);
			stmtSelectProducts.setInt(1, userId);
			ResultSet rs = stmtSelectProducts.executeQuery();
			
			while (rs.next()) {
				// TO DO - get product by product type
				// add product in List products
			}
			
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
		
		return products;
	}
}

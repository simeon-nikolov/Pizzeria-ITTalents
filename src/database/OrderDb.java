package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pizzeria.Shop;
import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.IProduct;
import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;

import com.mysql.jdbc.Statement;

import exceptions.InvalidArgumentValueException;

public class OrderDb extends DataAccessObject {
	private Connection connection = super.getConnection();

	public int addOrder(Order order) {
		String sqlInserOrder = "INSERT INTO `pizzeria`.`Order` (`sum`, `is_ready`, `is_received`, `User_idUser`, `Shop_idShop`) VALUES "
				+ "(?, ?, ?, ?, ?);";
		String sqlInserProductId = "INSERT INTO `pizzeria`.`Products_In_Orders` (`idProduct`, `quantity`, `Order_idOrder`) VALUES "
				+ "(?, ?, ?);";
		int orderId = 0;

		try {
			PreparedStatement stmtInsertOrder = connection.prepareStatement(sqlInserOrder, Statement.RETURN_GENERATED_KEYS);
			stmtInsertOrder.setDouble(1, order.getSum());
			stmtInsertOrder.setBoolean(2, false);
			stmtInsertOrder.setBoolean(3, false);
			stmtInsertOrder.setInt(4, order.getClient().getId());
			stmtInsertOrder.setInt(5, order.getShop().getId());
			stmtInsertOrder.executeUpdate();

			try (ResultSet generatedKeys = stmtInsertOrder.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					orderId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating order failed, no ID obtained.");
				}
			}

			PreparedStatement stmtInsertProductId = connection.prepareStatement(sqlInserProductId);

			for (IProduct product : order.getProducts()) {
				stmtInsertProductId.setInt(1, product.getId());
				stmtInsertProductId.setInt(2, 1);
				stmtInsertProductId.setInt(3, orderId);
				stmtInsertProductId.executeUpdate();
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
		
		return orderId;
	}

	public void editOrder(int idOrder, Order order) {
		String sqlUpdateOrder = "UPDATE `pizzeria`.`Order` SET `sum`=?, `is_ready`=?, `is_received`=?, `User_idUser`=?;";
		String sqlInserProductId = "UPDATE `pizzeria`.`Products_In_Orders` SET `idProduct`=?, `quantity`=?, `Order_idOrder`=?;";

		try {
			PreparedStatement stmtInsertOrder = connection.prepareStatement(sqlUpdateOrder);
			stmtInsertOrder.setDouble(1, order.getSum());
			stmtInsertOrder.setBoolean(2, order.isReady());
			stmtInsertOrder.setBoolean(3, order.isReceived());
			stmtInsertOrder.setInt(4, order.getClient().getId());
			stmtInsertOrder.executeUpdate();
			PreparedStatement stmtInsertProductId = connection.prepareStatement(sqlInserProductId);

			for (IProduct product : order.getProducts()) {
				stmtInsertProductId.setInt(1, product.getId());
				stmtInsertProductId.setInt(2, 1);
				stmtInsertProductId.setInt(3, idOrder);
				stmtInsertProductId.executeUpdate();
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
	}

	public void removeOrder(int idOrder) {
		String sqlDeleteOrder = "DELETE FROM `pizzeria`.`Order` WHERE `idOrder`=?;";
		String sqlDeleteProductsFromOrder = "DELETE FROM `pizzeria`.`Products_In_Orders` WHERE `Order_idOrder`=?;";

		try {
			PreparedStatement stmtDeleteProductsFromOrder = connection.prepareStatement(sqlDeleteProductsFromOrder);
			stmtDeleteProductsFromOrder.setInt(1, idOrder);
			stmtDeleteProductsFromOrder.executeUpdate();
			PreparedStatement stmtDeleteOrder = connection.prepareStatement(sqlDeleteOrder);
			stmtDeleteOrder.setInt(1, idOrder);
			stmtDeleteOrder.executeUpdate();
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

	public Order getOrderById(int idOrder) {
		String sqlSelectOrder = "SELECT * FROM `pizzeria`.`Order` WHERE `idOrder`=?;";
		String sqlSelectShop = "SELECT * FROM `pizzeria`.`Shop` WHERE `idShop` = ?";
		
		Order order = null;
		
		try {
			PreparedStatement stmtSelectOrder = connection.prepareStatement(sqlSelectOrder);
			stmtSelectOrder.setInt(1, idOrder);
			ResultSet rs = stmtSelectOrder.executeQuery();
			rs.next();
			order = new Order(); 
			order.setReady(rs.getBoolean("is_ready"));
			order.setReceived(rs.getBoolean("is_received"));
			int userId = rs.getInt("User_idUser");
			User client = new UserDb().getUserById(userId);
			order.setClient(client);
			PreparedStatement stmtSelectShop = connection.prepareStatement(sqlSelectShop);
			stmtSelectShop.setInt(1, rs.getInt("Shop_idShop"));
			rs = stmtSelectShop.executeQuery();
			rs.next();
			Shop shop = new Shop(
					rs.getInt("idShop"),
					rs.getString("name"),
					rs.getString("address")
				);
			order.setShop(shop);
			List<Pizza> pizzas = getPizzasByOrderId(idOrder);
			
			for (Pizza pizza : pizzas) {
				order.addProduct(pizza);
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
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return order;
	}

	private List<Pizza> getPizzasByOrderId(int idOrder) throws SQLException, InvalidArgumentValueException {
		String sqlSelectPizzas = "SELECT * FROM `pizzeria`.`Product` pr "
				+ "JOIN `pizzeria`.`Products_In_Orders` po ON (pr.`idProduct` = po.`idProduct`) "
				+ "JOIN `pizzeria`.`Food` f ON (f.`Product_idProduct` = pr.`idProduct`) "
				+ "JOIN `pizzeria`.`Pizza` pi ON (pi.`Food_idFood` = f.`idFood`) "
				+ "WHERE po.`Order_idOrder` = ?;";
		PreparedStatement stmtSelectPizzas = connection.prepareStatement(sqlSelectPizzas);
		stmtSelectPizzas.setInt(1, idOrder);
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

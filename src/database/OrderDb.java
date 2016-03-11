package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pizzeria.Shop;
import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.IProduct;
import pizzeria.menu.Pizza;

import com.mysql.jdbc.Statement;

import exceptions.InvalidArgumentValueException;

public class OrderDb {
	private static final String DB_CONNECTION_ERROR_MESSAGE = "Database connection is null!";

	private Connection conn;

	public OrderDb(Connection conn) throws InvalidArgumentValueException {
		if (conn == null) {
			throw new InvalidArgumentValueException(DB_CONNECTION_ERROR_MESSAGE);
		}

		this.conn = conn;
	}

	public void addOrder(Order order) {
		String sqlInserOrder = "INSERT INTO `pizzeria`.`Order` (`sum`, `is_ready`, `is_received`, `User_idUser`) VALUES "
				+ "(?, ?, ?, ?);";
		String sqlInserProductId = "INSERT INTO `pizzeria`.`Products_In_Orders` (`idProduct`, `quantity`, `Order_idOrder`) VALUES "
				+ "(?, ?, ?);";

		try {
			PreparedStatement stmtInsertOrder = conn.prepareStatement(sqlInserOrder, Statement.RETURN_GENERATED_KEYS);
			stmtInsertOrder.setDouble(1, order.getSum());
			stmtInsertOrder.setBoolean(2, false);
			stmtInsertOrder.setBoolean(3, false);
			stmtInsertOrder.setInt(4, order.getClient().getId());
			stmtInsertOrder.executeUpdate();

			int orderId = 0;
			try (ResultSet generatedKeys = stmtInsertOrder.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					orderId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating order failed, no ID obtained.");
				}
			}

			PreparedStatement stmtInsertProductId = conn.prepareStatement(sqlInserProductId);

			for (IProduct product : order.getProducts()) {
				stmtInsertProductId.setInt(1, product.getId());
				stmtInsertProductId.setInt(2, product.getQuantity());
				stmtInsertProductId.setInt(3, orderId);
			}

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

	public void editOrder(int idOrder, Order order) {
		String sqlUpdateOrder = "UPDATE `pizzeria`.`Order` SET `sum`=?, `is_ready`=?, `is_received`=?, `User_idUser`=?;";
		String sqlInserProductId = "UPDATE `pizzeria`.`Products_In_Orders` SET `idProduct`=?, `quantity`=?, `Order_idOrder`=?;";

		try {
			PreparedStatement stmtInsertOrder = conn.prepareStatement(sqlUpdateOrder);
			stmtInsertOrder.setDouble(1, order.getSum());
			stmtInsertOrder.setBoolean(2, order.isReady());
			stmtInsertOrder.setBoolean(3, order.isReceived());
			stmtInsertOrder.setInt(4, order.getClient().getId());
			stmtInsertOrder.executeUpdate();
			PreparedStatement stmtInsertProductId = conn.prepareStatement(sqlInserProductId);

			for (IProduct product : order.getProducts()) {
				stmtInsertProductId.setInt(1, product.getId());
				stmtInsertProductId.setInt(2, product.getQuantity());
				stmtInsertProductId.setInt(3, idOrder);
				stmtInsertProductId.executeUpdate();
			}

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

	public void removeOrder(int idOrder) {
		String sqlDeleteOrder = "DELETE FROM `pizzeria`.`Order` WHERE `idOrder`=?;";
		String sqlDeleteProductsFromOrder = "DELETE FROM `pizzeria`.`Products_In_Orders` WHERE `Order_idOrder`=?;";

		try {
			PreparedStatement stmtDeleteProductsFromOrder = conn.prepareStatement(sqlDeleteProductsFromOrder);
			stmtDeleteProductsFromOrder.setInt(1, idOrder);
			stmtDeleteProductsFromOrder.executeUpdate();
			PreparedStatement stmtDeleteOrder = conn.prepareStatement(sqlDeleteOrder);
			stmtDeleteOrder.setInt(1, idOrder);
			stmtDeleteOrder.executeUpdate();
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

	public Order getOrderById(int idOrder) {
		String sqlSelectOrder = "SELECT * FROM `pizzeria`.`Order` WHERE `idOrder`=?;";
		String sqlSelectShop = "SELECT * FROM `pizzeria`.`Shop` WHERE `pizzeria`.`Order_idOrder` = ?";
		
		Order order = null;
		
		try {
			PreparedStatement stmtSelectOrder = conn.prepareStatement(sqlSelectOrder);
			stmtSelectOrder.setInt(1, idOrder);
			ResultSet rs = stmtSelectOrder.executeQuery();
			rs.next();
			order = new Order(); 
			order.setReady(rs.getBoolean("is_ready"));
			order.setReceived(rs.getBoolean("is_received"));
			int userId = rs.getInt("User_idUser");
			User client = new UserDb(conn).getUserById(userId);
			order.setClient(client);
			PreparedStatement stmtSelectShop = conn.prepareStatement(sqlSelectShop);
			stmtSelectShop.setInt(1, idOrder);
			rs = stmtSelectShop.executeQuery();
			rs.next();
			Shop shop = new Shop(
					rs.getInt("idShop"),
					rs.getString("name"),
					rs.getString("address")
				);
			order.setShop(shop);
			
			// get pizzas
			String sqlSelectPizzas = "SELECT * FROM `pizzeria`.`Product` pr "
					+ "JOIN `pizzeria`.`Products_In_Order` po ON (pr.`idProduct` = po.`idProduct`) "
					+ "JOIN `pizzeria`.`Food` f ON (f.`Product_idProduct` = pr.`idProduct`) "
					+ "JOIN `pizeria`.`Pizza` pi ON (pi.`Food_idFood` = f.`idFood`) "
					+ "WHERE po.`Order_idOrder` = ?;";
			PreparedStatement stmtSelectPizzas = conn.prepareStatement(sqlSelectPizzas);
			stmtSelectPizzas.setInt(1, idOrder);
			rs = stmtSelectPizzas.executeQuery();
			
			while(rs.next()) {
				Pizza pizza = new Pizza(
						rs.getInt("idProduct"),
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getShort("quantity"),
						rs.getInt("grammage"),
						rs.getInt("size")
					);
				order.addProduct(pizza);
			}

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
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return order;
	}
}

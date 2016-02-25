package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pizzeria.account.Order;
import pizzeria.menu.IProduct;

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
		String sqlSelectProducts = "SELECT * FROM `pizzeria`.`Product` pr "
				+ "JOIN `pizzeria`.`Products_In_Order` po ON (pr.`idProduct` = po.`idProduct`) "
				+ "WHERE po.`Order_idOrder` = ?;";
		
		Order order = null;
		
		try {
			PreparedStatement stmtSelectOrder = conn.prepareStatement(sqlSelectOrder);
			stmtSelectOrder.setInt(1, idOrder);
			ResultSet rs = stmtSelectOrder.executeQuery();
			rs.next();
			order = new Order(); 
			order.setReady(rs.getBoolean("is_ready"));
			order.setReceived(rs.getBoolean("is_received"));
			// TO DO
			PreparedStatement stmtSelectProducts = conn.prepareStatement(sqlSelectProducts);


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
		
		return order;
	}
}

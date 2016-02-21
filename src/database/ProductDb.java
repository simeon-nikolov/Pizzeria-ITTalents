package database;

import java.sql.*;

import pizzeria.menu.Food;
import pizzeria.menu.IProduct;
import pizzeria.menu.Pizza;
import exceptions.InvalidArgumentValueException;

public class ProductDb {
	Connection conn;

	public ProductDb(Connection conn) throws InvalidArgumentValueException {
		if (conn != null)
			this.conn = conn;
		else
			throw new InvalidArgumentValueException("Database connection is null");
	}

	public void addProduct(IProduct product) {
		String sql = "INSERT INTO `pizzeria`.`product` ( `name`, `price`, `quantity`) VALUES " + "(?, ?, ?);";
		String sqlSelect = "SELECT idProduct FROM pizzeria.product WHERE name = ?; ";
		String st = "INSERT INTO `pizzeria`.`food` (`grammage`, `Product_idProduct`) VALUES " + "(?,?);";
		String stSelect = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String s = "INSET INTO pizzeria.pizza (size,Food_idFood) VALUES  " + " (?,?)";
		try {
			PreparedStatement stt = conn.prepareStatement(sql);
			stt.setString(1, product.getName());
			stt.setDouble(2, product.getPrice());
			stt.setInt(3, product.getQuantity());
			stt.executeUpdate();
			PreparedStatement sttSel = conn.prepareStatement(sqlSelect);
			sttSel.setString(1, product.getName());
			ResultSet rs = sttSel.executeQuery();
			rs.next();
			int idProduct = rs.getInt("idProduct");
			PreparedStatement stt2 = conn.prepareStatement(st);
			stt2.setInt(1, product.getGrammage());
			stt2.setInt(2, idProduct);
			stt2.executeUpdate();
			PreparedStatement stSel = conn.prepareStatement(stSelect);
			stSel.setInt(1, idProduct);
			ResultSet rs2 = stSel.executeQuery();
			rs2.next();
			int idFood = rs2.getInt("idFood");
			PreparedStatement stt3 = conn.prepareStatement(s);
			stt3.setInt(1, ((Pizza) product).getSize());
			stt3.setInt(2, idFood);
			stt3.executeUpdate();
			conn.commit();
			System.out.println("Success!");
		} catch (Exception e) {
			try {
				conn.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}

package database;

import java.sql.*;

import exceptions.InvalidArgumentValueException;
import pizzeria.menu.Food;
import pizzeria.menu.IProduct;
import pizzeria.menu.Pizza;

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
		String st = "INSERT INTO `pizzeria`.`food(`grammage`, `Product_idProduct`) VALUES (?,?);";
		String s = "INSET INTO pizzeria.pizza(size,Food_idFood) VALUES(?,?)";
		try {
			PreparedStatement stt = conn.prepareStatement(sql);
			stt.setString(1, product.getName());
			stt.setDouble(2, product.getPrice());
			stt.setInt(3, product.getQuantity());
			stt.executeUpdate();
			PreparedStatement stt2 = conn.prepareStatement(st);
			stt2.setInt(1, ((Food) product).getGrammage());
			stt2.setInt(2, product.getId());
			stt2.executeQuery();
			PreparedStatement stt3 = conn.prepareStatement(s);
			stt3.setInt(1, ((Pizza) product).getSize());
			stt3.setInt(2, ((Food)product).);
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

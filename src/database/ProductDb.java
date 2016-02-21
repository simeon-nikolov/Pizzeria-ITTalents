package database;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import pizzeria.account.Administrator;
import pizzeria.account.User;
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

	public void addProduct(Pizza pizza) {
		String sql = "INSERT INTO `pizzeria`.`product` ( `name`, `price`, `quantity`) VALUES " + "(?, ?, ?);";
		String sqlSelect = "SELECT idProduct FROM pizzeria.product WHERE name = ?; ";
		String st = "INSERT INTO `pizzeria`.`food` (`grammage`, `Product_idProduct`) VALUES " + "(?,?);";
		String stSelect = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String s = "INSERT INTO pizzeria.pizza (size,Food_idFood) VALUES  " + " (?,?)";
		try {
			PreparedStatement stt = conn.prepareStatement(sql);
			stt.setString(1, pizza.getName());
			stt.setDouble(2, pizza.getPrice());
			stt.setInt(3, pizza.getQuantity());
			stt.executeUpdate();
			PreparedStatement sttSel = conn.prepareStatement(sqlSelect);
			sttSel.setString(1, pizza.getName());
			ResultSet rs = sttSel.executeQuery();
			rs.next();
			int idProduct = rs.getInt("idProduct");
			PreparedStatement stt2 = conn.prepareStatement(st);
			stt2.setInt(1, pizza.getGrammage());
			stt2.setInt(2, idProduct);
			stt2.executeUpdate();
			PreparedStatement stSel = conn.prepareStatement(stSelect);
			stSel.setInt(1, idProduct);
			ResultSet rs2 = stSel.executeQuery();
			rs2.next();
			int idFood = rs2.getInt("idFood");
			PreparedStatement stt3 = conn.prepareStatement(s);
			stt3.setInt(1, pizza.getSize());
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
	
	public void editPizza(int idProduct, Pizza pizza) {
		String sqlProductUpdate = "UPDATE pizzeria.product SET name=?, price=?, quantity=? WHERE idProduct=?;";
		String sqlSelectFoodId = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String sqlFoodUpdate = "UPDATE pizzeria.food SET grammage = ?  WHERE idFood = ? ;";
		String sqlPizzaUpdate = "UPDATE pizzeria.pizza SET size = ? WHERE Food_idFood = ?;";
		
		try {
			
			PreparedStatement stmtPro = conn.prepareStatement(sqlProductUpdate);
			stmtPro.setString(1, pizza.getName());
			stmtPro.setDouble(2, pizza.getPrice());
			stmtPro.setInt(3, pizza.getQuantity());
			stmtPro.setInt(4, idProduct);
			stmtPro.executeUpdate();
			
			PreparedStatement stmtProId = conn.prepareStatement(sqlSelectFoodId);
			stmtProId.setInt(1, idProduct);
			ResultSet rs = stmtProId.executeQuery();
			rs.next();
			int idFood = rs.getInt("idFood");
			PreparedStatement stmtFood = conn.prepareStatement(sqlFoodUpdate);
			stmtFood.setInt(1, pizza.getGrammage());
			stmtFood.setInt(2, idFood);
			stmtFood.executeUpdate();
			
			PreparedStatement stmtPizza = conn.prepareStatement(sqlPizzaUpdate);
			stmtPizza.setInt(1, pizza.getSize());
			stmtPizza.setInt(2, idFood);
			stmtPizza.executeUpdate();
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
	
}

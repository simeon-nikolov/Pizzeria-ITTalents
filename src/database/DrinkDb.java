package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidArgumentValueException;
import pizzeria.menu.Drink;
import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;

public class DrinkDb extends DataAccessObject {
	private Connection connection = super.getConnection();

	public void addDrink(Drink drink) {
		String sqlInsertInProduct = "INSERT INTO pizzeria.product ( name, price, quantity) VALUES (?, ?, ?);";
		String sqlSelectProductId = "SELECT idProduct FROM pizzeria.product WHERE name = ?; ";
		String insertDrink = "INSERT INTO pizzeria.drink (litres, Product_idProduct) VALUES (?,?);";
		try {
			PreparedStatement stmtProduct = connection.prepareStatement(sqlInsertInProduct);
			stmtProduct.setString(1, drink.getName());
			stmtProduct.setDouble(2, drink.getPrice());
			stmtProduct.setInt(3, drink.getQuantity());
			stmtProduct.executeUpdate();

			PreparedStatement stmtSelect = connection.prepareStatement(sqlSelectProductId);
			stmtSelect.setString(1, drink.getName());
			ResultSet rs = stmtSelect.executeQuery();
			rs.next();
			int idProduct = rs.getInt("idProduct");

			PreparedStatement stmtInsertDrink = connection.prepareStatement(insertDrink);
			stmtInsertDrink.setDouble(1, drink.getSize());
			stmtInsertDrink.setInt(2, idProduct);

			connection.commit();
			System.out.println("Success!");
		} catch (SQLException e) {
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void editDrink(int id, Drink drink) {
		String sqlProductUpdate = "UPDATE pizzeria.product SET name=?, price=?, quantity=? WHERE idProduct=?;";
		String sqlDrinkUpdate = "UPDATE pizzeria.drink SET litres = ?  WHERE Product_idProduct = ? ;";
		try {
			PreparedStatement stmtProduct = connection.prepareStatement(sqlProductUpdate);
			stmtProduct.setString(1, drink.getName());
			stmtProduct.setDouble(2, drink.getPrice());
			stmtProduct.setInt(3, drink.getQuantity());
			stmtProduct.setInt(4, id);
			stmtProduct.executeUpdate();

			PreparedStatement stmtDrunk = connection.prepareStatement(sqlDrinkUpdate);
			stmtDrunk.setDouble(1, drink.getSize());
			stmtDrunk.setInt(2, id);
			stmtDrunk.executeUpdate();

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

	public void removeDrink(int id) {
		String sqlProductUpdate = "DELETE FROM pizzeria.product WHERE idProduct = ?;";
		String sqlDrinkUpdate = "DELETE FROM pizzeria.drink WHERE Product_idProduct = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sqlProductUpdate);
			stmt.setInt(1, id);
			stmt.executeUpdate();

			PreparedStatement stmtDrink = connection.prepareStatement(sqlDrinkUpdate);
			stmtDrink.setInt(1, id);
			stmtDrink.executeUpdate();

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

	public Drink getDrinkById(int id) {
		Drink drink = null;
		String sql = "select p.idProduct, p.name , p.price, p.quantity, d.litres from pizzeria.product p "
				+ "join pizzeria.drink d on p.idProduct = d.Product_idProduct " + "where p.idProduct =  ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			rs.next();
			drink = new Drink(rs.getInt("idProduct"), rs.getDouble("price"), (short) rs.getInt("quantity"),
					rs.getString("name"), rs.getInt("litres"));
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
		return drink;
	}

	public List<Drink> getAllDrinks(){
		List<Drink> drinks = new ArrayList<Drink>();
		String sql = "select p.idProduct, p.name , p.price, p.quantity, d.litres,  from pizzeria.product p "
				+ "join pizzeria.drink d on p.idProduct = f.Product_idProduct;";
		try {
			PreparedStatement ptmt = connection.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				Drink d = new Drink(rs.getInt("idProduct"), rs.getDouble("price"), (short) rs.getInt("quantity"), rs.getString("name"), rs.getInt("litres"));
				drinks.add(d);
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
		return drinks;
	}
	
}

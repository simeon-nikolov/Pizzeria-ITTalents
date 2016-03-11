package database;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import exceptions.InvalidArgumentValueException;

public class ProductDb {
	private Connection conn;

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
		String sqlFoodIngredient = " INSERT INTO pizzeria.food_has_ingredient (Ingredient_idIngredient,Food_idFood) "
				+ "VALUES " + "(?,?);";
		String sqlIngredientSelect = "SELECT idIngredient FROM pizzeria.ingredient WHERE name = ?;";
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

			for (Ingredient ing : pizza.getIngredients()) {
				PreparedStatement stIng = conn.prepareStatement(sqlIngredientSelect);
				stIng.setString(1, ing.getName());
				ResultSet rsIng = stIng.executeQuery();
				rsIng.next();
				int idIngredient = rsIng.getInt("IdIngredient");
				PreparedStatement stmt = conn.prepareStatement(sqlFoodIngredient);
				stmt.setInt(1, idIngredient);
				stmt.setInt(2, idFood);
				stmt.executeUpdate();
			}

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
		String sqlIngUpdate = "UPDATE pizzeria.food_has_ingredient SET Food_idFood = ? WHERE Food_idFood = ?;";

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

	public void removePizza(int idPizza) {
		String sqlProductUpdate = "DELETE FROM pizzeria.product WHERE idProduct = ?;";
		String sqlSelectFoodId = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String sqlFoodUpdate = "DELETE FROM pizzeria.food WHERE idFood = ?;";
		String sqlPizzaUpdate = "DELETE FROM pizzeria.pizza WHERE Food_idFood = ?;";
		String sqlIngredientsUpdate = "DELETE FROM pizzeria.food_has_ingredient WHERE Food_idFood = ?;";

		try {
			PreparedStatement stmtPro = conn.prepareStatement(sqlProductUpdate);
			stmtPro.setInt(1, idPizza);
			stmtPro.executeUpdate();
			PreparedStatement stmtAccId = conn.prepareStatement(sqlSelectFoodId);
			stmtAccId.setInt(1, idPizza);
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idFood = rs.getInt("idFood");

			PreparedStatement stmtPizza = conn.prepareStatement(sqlPizzaUpdate);
			stmtPizza.setInt(1, idFood);
			stmtPizza.executeUpdate();

			PreparedStatement stmtIng = conn.prepareStatement(sqlIngredientsUpdate);
			stmtIng.setInt(1, idFood);
			stmtIng.executeUpdate();

			PreparedStatement stmtFood = conn.prepareStatement(sqlFoodUpdate);
			stmtFood.setInt(1, idFood);
			stmtFood.executeUpdate();

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

	public Pizza getPizzaById(int id) {
		Pizza pizza = null;
		String sql = "select p.idProduct, p.name , p.price, p.quantity, f.grammage, pi.size from pizzeria.product p "
				+ "join pizzeria.food f on p.idProduct = f.Product_idProduct join pizzeria.pizza pi on f.idFood = pi.Food_idFood "
				+ "where p.idProduct =  ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			rs.next();
			pizza = new Pizza(rs.getInt("idProduct"), rs.getString("name"), rs.getDouble("price"),
					(short) rs.getInt("quantity"), rs.getInt("grammage"), rs.getInt("size"));
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
		return pizza;
	}

	public Set<Pizza> getAllPizza() {
		Set<Pizza> pizzas = new HashSet<Pizza>();
		String sql = "select p.idProduct, p.name , p.price, p.quantity, f.grammage, pi.size from pizzeria.product p "
				+ "join pizzeria.food f on p.idProduct = f.Product_idProduct join pizzeria.pizza pi on f.idFood = pi.Food_idFood;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			conn.commit();

			while (rs.next()) {
				Pizza pizza = new Pizza(rs.getInt("idProduct"), rs.getString("name"), rs.getDouble("price"),
						(short) rs.getInt("quantity"), rs.getInt("grammage"), rs.getInt("size"));
				pizzas.add(pizza);
			}

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
		return pizzas;
	}

	public Set<Ingredient> getAllPizzaIngredients(Pizza pizza) {
		Set<Ingredient> ing = new HashSet<Ingredient>();
		String sqlSelect = "SELECT idProduct FROM pizzeria.product WHERE name = ?; ";
		String sqlSelectFoodId = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String sql = "select i.name from pizzeria.ingredient i join pizzeria.food_has_ingredient fi "
				+ "on i.idIngredient = fi.Ingredient_idIngredient where Food_idFood = ?;";
		try {
			PreparedStatement sttSel = conn.prepareStatement(sqlSelect);
			sttSel.setString(1, pizza.getName());
			ResultSet rs = sttSel.executeQuery();
			rs.next();
			int idProduct = rs.getInt("idProduct");
			PreparedStatement stfo = conn.prepareStatement(sqlSelectFoodId);
			stfo.setInt(1, idProduct);
			ResultSet rsF = stfo.executeQuery();
			rsF.next();
			int idFood = rsF.getInt("idFood");
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idFood);
			ResultSet rsI = stmt.executeQuery();
			conn.commit();
			while (rsI.next()) {
				Ingredient ingredient = new Ingredient(rsI.getString("name"));
				ing.add(ingredient);
			}
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
		return ing;
	}
}

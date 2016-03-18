package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import exceptions.InvalidArgumentValueException;

public class PizzaDb extends DataAccessObject implements IPizzaDao {
	private Connection connection = super.getConnection();

	@Override
	public int addPizza(Pizza pizza) {
		String sql = "INSERT INTO `pizzeria`.`product` ( `name`, `price`, `image`) VALUES " + "(?, ?, ?);";
		String sqlSelect = "SELECT idProduct FROM pizzeria.product WHERE name = ?; ";
		String st = "INSERT INTO `pizzeria`.`food` (`grammage`, `Product_idProduct`) VALUES " + "(?,?);";
		String stSelect = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String s = "INSERT INTO pizzeria.pizza (size,Food_idFood) VALUES  " + " (?,?)";
		String sqlFoodIngredient = " INSERT INTO pizzeria.food_has_ingredient (Ingredient_idIngredient,Food_idFood) "
				+ "VALUES " + "(?,?);";
		String sqlIngredientSelect = "SELECT idIngredient FROM pizzeria.ingredient WHERE name = ?;";
		int pizzaId = 0;
		
		try {
			PreparedStatement stt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stt.setString(1, pizza.getName());
			stt.setDouble(2, pizza.getPrice());
			stt.setString(3, pizza.getImage());
			stt.executeUpdate();
			ResultSet rs = stt.getGeneratedKeys();
			rs.next();
			pizzaId = rs.getInt(1);
			PreparedStatement sttSel = connection.prepareStatement(sqlSelect);
			sttSel.setString(1, pizza.getName());
			rs = sttSel.executeQuery();
			rs.next();
			int idProduct = rs.getInt("idProduct");
			PreparedStatement stt2 = connection.prepareStatement(st);
			stt2.setInt(1, pizza.getGrammage());
			stt2.setInt(2, idProduct);
			stt2.executeUpdate();
			PreparedStatement stSel = connection.prepareStatement(stSelect);
			stSel.setInt(1, idProduct);
			ResultSet rs2 = stSel.executeQuery();
			rs2.next();
			int idFood = rs2.getInt("idFood");
			PreparedStatement stt3 = connection.prepareStatement(s);
			stt3.setInt(1, pizza.getSize());
			stt3.setInt(2, idFood);
			stt3.executeUpdate();

			for (Ingredient ing : pizza.getIngredients()) {
				PreparedStatement stIng = connection.prepareStatement(sqlIngredientSelect);
				stIng.setString(1, ing.getName());
				ResultSet rsIng = stIng.executeQuery();
				rsIng.next();
				int idIngredient = rsIng.getInt("IdIngredient");
				PreparedStatement stmt = connection.prepareStatement(sqlFoodIngredient);
				stmt.setInt(1, idIngredient);
				stmt.setInt(2, idFood);
				stmt.executeUpdate();
			}

			connection.commit();
			System.out.println("Success!");
		} catch (Exception e) {
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return pizzaId;
	}

	@Override
	public void editPizza(int idProduct, Pizza pizza) {
		String sqlProductUpdate = "UPDATE pizzeria.product SET name=?, price=?, image=? WHERE idProduct=?;";
		String sqlSelectFoodId = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String sqlFoodUpdate = "UPDATE pizzeria.food SET grammage = ?  WHERE idFood = ? ;";
		String sqlPizzaUpdate = "UPDATE pizzeria.pizza SET size = ? WHERE Food_idFood = ?;";
		String sqlIngUpdate = "UPDATE pizzeria.food_has_ingredient SET Food_idFood = ? WHERE Food_idFood = ?;";

		try {

			PreparedStatement stmtPro = connection.prepareStatement(sqlProductUpdate);
			stmtPro.setString(1, pizza.getName());
			stmtPro.setDouble(2, pizza.getPrice());
			stmtPro.setString(3, pizza.getImage());
			stmtPro.setInt(4, idProduct);
			stmtPro.executeUpdate();

			PreparedStatement stmtProId = connection.prepareStatement(sqlSelectFoodId);
			stmtProId.setInt(1, idProduct);
			ResultSet rs = stmtProId.executeQuery();
			rs.next();
			int idFood = rs.getInt("idFood");
			PreparedStatement stmtFood = connection.prepareStatement(sqlFoodUpdate);
			stmtFood.setInt(1, pizza.getGrammage());
			stmtFood.setInt(2, idFood);
			stmtFood.executeUpdate();

			PreparedStatement stmtPizza = connection.prepareStatement(sqlPizzaUpdate);
			stmtPizza.setInt(1, pizza.getSize());
			stmtPizza.setInt(2, idFood);
			stmtPizza.executeUpdate();

			PreparedStatement stmtIngredient = connection.prepareStatement(sqlIngUpdate);
			stmtIngredient.setInt(1, pizza.getId());
			stmtIngredient.setInt(2, idFood);
			stmtIngredient.executeUpdate();
			
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
	public void removePizza(int idPizza) {
		String sqlProductUpdate = "DELETE FROM pizzeria.product WHERE idProduct = ?;";
		String sqlSelectFoodId = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String sqlFoodUpdate = "DELETE FROM pizzeria.food WHERE idFood = ?;";
		String sqlPizzaUpdate = "DELETE FROM pizzeria.pizza WHERE Food_idFood = ?;";
		String sqlIngredientsUpdate = "DELETE FROM pizzeria.food_has_ingredient WHERE Food_idFood = ?;";

		try {
			PreparedStatement stmtPro = connection.prepareStatement(sqlProductUpdate);
			stmtPro.setInt(1, idPizza);
			stmtPro.executeUpdate();
			PreparedStatement stmtAccId = connection.prepareStatement(sqlSelectFoodId);
			stmtAccId.setInt(1, idPizza);
			ResultSet rs = stmtAccId.executeQuery();
			rs.next();
			int idFood = rs.getInt("idFood");

			PreparedStatement stmtPizza = connection.prepareStatement(sqlPizzaUpdate);
			stmtPizza.setInt(1, idFood);
			stmtPizza.executeUpdate();

			PreparedStatement stmtIng = connection.prepareStatement(sqlIngredientsUpdate);
			stmtIng.setInt(1, idFood);
			stmtIng.executeUpdate();

			PreparedStatement stmtFood = connection.prepareStatement(sqlFoodUpdate);
			stmtFood.setInt(1, idFood);
			stmtFood.executeUpdate();

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
	public Pizza getPizzaById(int id) {
		Pizza pizza = null;
		String sql = "select p.idProduct, p.name , p.price, p.image, f.grammage, pi.size from pizzeria.product p "
				+ "join pizzeria.food f on p.idProduct = f.Product_idProduct join pizzeria.pizza pi on f.idFood = pi.Food_idFood "
				+ "where p.idProduct =  ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			rs.next();
			pizza = new Pizza(rs.getInt("idProduct"), rs.getString("name"), rs.getDouble("price"),
					rs.getString("image"), rs.getInt("grammage"), rs.getInt("size"));
			List<Ingredient> ingredients = this.getAllPizzaIngredients(pizza);
			for (Ingredient ingredient : ingredients) {
				pizza.addIngredient(ingredient);
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
		return pizza;
	}

	@Override
	public List<Pizza> getAllPizza() {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		String sql = "select p.idProduct, p.name , p.price, p.image, f.grammage, pi.size from pizzeria.product p "
				+ "join pizzeria.food f on p.idProduct = f.Product_idProduct join pizzeria.pizza pi on f.idFood = pi.Food_idFood;";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			connection.commit();

			while (rs.next()) {
				Pizza pizza = new Pizza(rs.getInt("idProduct"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("image"), rs.getInt("grammage"), rs.getInt("size"));
				List<Ingredient> ingredients = this.getAllPizzaIngredients(pizza);
				for (Ingredient ingredient : ingredients) {
					pizza.addIngredient(ingredient);
				}
				pizzas.add(pizza);
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
		return pizzas;
	}

	@Override
	public List<Ingredient> getAllPizzaIngredients(Pizza pizza) {
		List<Ingredient> ing = new ArrayList<Ingredient>();
		String sqlSelect = "SELECT idProduct FROM pizzeria.product WHERE name = ?; ";
		String sqlSelectFoodId = "SELECT idFood FROM pizzeria.food WHERE Product_idProduct = ?;";
		String sql = "select i.name from pizzeria.ingredient i join pizzeria.food_has_ingredient fi "
				+ "on i.idIngredient = fi.Ingredient_idIngredient where Food_idFood = ?;";
		try {
			PreparedStatement sttSel = connection.prepareStatement(sqlSelect);
			sttSel.setString(1, pizza.getName());
			ResultSet rs = sttSel.executeQuery();
			rs.next();
			int idProduct = rs.getInt("idProduct");
			PreparedStatement stfo = connection.prepareStatement(sqlSelectFoodId);
			stfo.setInt(1, idProduct);
			ResultSet rsF = stfo.executeQuery();
			rsF.next();
			int idFood = rsF.getInt("idFood");
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idFood);
			ResultSet rsI = stmt.executeQuery();
			connection.commit();
			while (rsI.next()) {
				Ingredient ingredient = new Ingredient(rsI.getString("name"));
				ing.add(ingredient);
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
		}
		return ing;
	}
	
	@Override
	public List<Pizza> getPizzasByOrderId(int idOrder) throws SQLException, InvalidArgumentValueException {
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
				pizza.addIngredient(ingredient);
			}
			
			pizzas.add(pizza);
		}
		
		return pizzas;
	}
	
	public List<Pizza> getPizzasByCartId(int id) throws SQLException, InvalidArgumentValueException {
		String sqlSelectPizzas = "SELECT * FROM `pizzeria`.`Product` pr "
				+ "JOIN `pizzeria`.`Products_In_Carts` c ON (pr.`idProduct` = c.`Product_idProduct`) "
				+ "JOIN `pizzeria`.`Food` f ON (f.`Product_idProduct` = pr.`idProduct`) "
				+ "JOIN `pizzeria`.`Pizza` pi ON (pi.`Food_idFood` = f.`idFood`) "
				+ "WHERE c.`User_idUser` = ?;";
		PreparedStatement stmtSelectPizzas = connection.prepareStatement(sqlSelectPizzas);
		stmtSelectPizzas.setInt(1, id);
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
				pizza.addIngredient(ingredient);
			}
			
			pizzas.add(pizza);
		}
		
		return pizzas;
	}
}

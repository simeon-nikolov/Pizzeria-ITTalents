package database;

import java.sql.SQLException;
import java.util.List;

import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import exceptions.InvalidArgumentValueException;

public interface IPizzaDao {

	int addPizza(Pizza pizza);

	void editPizza(int idProduct, Pizza pizza);

	void removePizza(int idPizza);

	Pizza getPizzaById(int id);

	List<Pizza> getAllPizza();

	List<Ingredient> getAllPizzaIngredients(Pizza pizza);
	
	List<Pizza> getPizzasByOrderId(int idOrder) throws SQLException, InvalidArgumentValueException;
	
	List<Pizza> getPizzasByCartId(int id) throws SQLException, InvalidArgumentValueException;

}
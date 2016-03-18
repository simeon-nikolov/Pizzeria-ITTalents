package database;

import java.util.List;

import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;

public interface IPizzaDao {

	int addPizza(Pizza pizza);

	void editPizza(int idProduct, Pizza pizza);

	void removePizza(int idPizza);

	Pizza getPizzaById(int id);

	List<Pizza> getAllPizza();

	List<Ingredient> getAllPizzaIngredients(Pizza pizza);

}
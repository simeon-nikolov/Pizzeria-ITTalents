package database;

import java.util.List;

import pizzeria.menu.Ingredient;

public interface IIngredientDao {

	void addIngredient(Ingredient ing);

	void editIngredient(int id, Ingredient ing);

	void removeIngredient(int id);

	Ingredient getIngredientByname(String name);

	List<Ingredient> getAllIngredients();

}
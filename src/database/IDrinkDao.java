package database;

import java.util.List;

import pizzeria.menu.Drink;

public interface IDrinkDao {

	void addDrink(Drink drink);

	void editDrink(int id, Drink drink);

	void removeDrink(int id);

	Drink getDrinkById(int id);

	List<Drink> getAllDrinks();

}
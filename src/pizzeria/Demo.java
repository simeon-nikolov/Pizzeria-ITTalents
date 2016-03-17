package pizzeria;

import java.util.List;

import pizzeria.account.Administrator;
import pizzeria.account.User;
import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import database.AdministratorDb;
import database.IngredientDb;
import database.PizzaDb;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

public class Demo {

	public static void main(String[] args) {
		Pizzeria dominos = new Pizzeria();
		try {
			PizzaDb pizzaDao = new PizzaDb();
			// Pizza kalcone = new Pizza();
			// kalcone.setName("Kalcone");
			// kalcone.setPrice(8.99);
			// kalcone.setQuantity(2);
			// kalcone.setGrammage(550);
			// kalcone.setSize(30);
			// String[] ingredients = {"Mocarela","Bekon","Luk","Carevica"};
			// for (String string : ingredients) {
			// kalcone.addIngredients(new Ingredient(string));
			// }
			// Pizza margarita = new Pizza(0, "Margarita", 5.99,
			// "resources/images/pizza/Ultimate_Cheese_Lovers_Pizza.png",
			// 450, 25);

			// PizzaDb pizzaDao = new PizzaDb();
			// Pizza margarita = new Pizza();
			// margarita.setName("Margarita");
			// margarita.setPrice(5.99);
			// margarita.setQuantity(1);
			// margarita.setGrammage(450);
			// margarita.setSize(25);
			// String[] ingredients2 = { "Mocarela", "Bekon", "Luk", "Carevica"
			// };
			// for (String string : ingredients2) {
			// margarita.addIngredients(new Ingredient(string));
			// }
			// pizzaDao.addPizza(margarita);
			// pizzaDao.editPizza(5, margarita);
			// Set<Pizza> pizzas = pizzaDao.getAllPizza();
			// for (Pizza pizza : pizzas) {
			// System.out.println(pizza);
			// }
			// Pizza a = pizzaDao.getPizzaById(1);
			// System.out.println(a);
			//
			// IngredientDb ingDao = new IngredientDb();
			// ingDao.addIngredient(new Ingredient("Parmezan"));
			// ingDao.addIngredient(new Ingredient("Brokoli"));
			// ingDao.addIngredient(new Ingredient("Mocarela"));
			// ingDao.addIngredient(new Ingredient("Maslini"));
			// ingDao.addIngredient(new Ingredient("Bekon"));
			// ingDao.addIngredient(new Ingredient("Gybi"));
			// ingDao.addIngredient(new Ingredient("Topeno sirene"));
			// ingDao.addIngredient(new Ingredient("Carevica"));
			// ingDao.addIngredient(new Ingredient("Luk"));
			// ingDao.addIngredient(new Ingredient("Shunka"));
			// ingDao.addIngredient(new Ingredient("Peperoni"));
			// String[] ingredients = { "Parmezan", "Gybi", "Peperoni",
			// "Maslini" };
			// Pizza peperoni = new Pizza(0, "Peperoni", 8.50,
			// "resources/images/pizza/Pepperoni_Lovers_Pizza.png", 600, 30);
			// for (String string : ingredients) {
			// peperoni.addIngredients(new Ingredient(string));
			// }
			Pizza kalcone = new Pizza(0, "Kalcone", 8.99, "resources/images/pizza/Classic_Supreme_Pizza.png", 550, 30);
			// pizzaDao.addPizza(peperoni);
			// kalcone.setName("Kalcone");
			// kalcone.setPrice(8.99);
			// kalcone.setQuantity(2);
			// kalcone.setGrammage(550);
			// kalcone.setSize(30);
//			String[] ingredients2 = { "Mocarela", "Bekon", "Luk", "Carevica" };
//			for (String string : ingredients2) {
//				kalcone.addIngredients(new Ingredient(string));
//			}
//			pizzaDao.addPizza(kalcone);
			// pizzaDao.addProduct(peperoni);
			// List<Ingredient> in = pizzaDao.getAllPizzaIngredients(peperoni);
			// System.out.println("Systavki na : " + peperoni.getName());
			// for (Ingredient ingredient : in) {
			// System.out.print(ingredient.getName() + " ");
			// }
			// System.out.println();
			// List<Pizza> pizzas = pizzaDao.getAllPizza();
			// for (Pizza pizza : pizzas) {
			// System.out.println(pizza.getName());
			// System.out.println(pizza.getPrice());
			// }
			// pizzaDao.removePizza(5); // Iztri margaritata
		} catch (InvalidArgumentValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

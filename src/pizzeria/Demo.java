package pizzeria;

import java.sql.SQLException;
import java.util.Set;

import pizzeria.account.Administrator;
import pizzeria.account.User;
import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import database.AdministratorDb;
import database.DatabaseConnection;
import database.IngredientDb;
import database.ProductDb;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

public class Demo {

	public static void main(String[] args) {
		Pizzeria dominos = new Pizzeria();

		try {
			DatabaseConnection dbConn = new DatabaseConnection();
			AdministratorDb adminDao = new AdministratorDb(dbConn.getConnection());
			Administrator admin = new Administrator();
			admin.setPassword("123123");
			admin.setUsername("admincho");
			admin.setEmail("admin2@test.com");
			// adminDao.addAdministrator(admin);
			// adminDao.editAdministrator(8, admin);
			// adminDao.removeAdministrator(7);
			// Set<Administrator> admins = adminDao.getAllAdministrator();
			//
			// for (Administrator administrator : admins) {
			// System.out.println(administrator.getUsername());
			// }

			// User user = new User();
			// user.setFirstName("Hsalfkdj");
			// user.setLastName("asldjalsjkd");
			// user.setUsername("user123");
			// user.setPassword("321321");
			// user.setEmail("user@asld.asd");
			// user.setPhoneNumber("11231123");
			// user.setAddress("fhs kkj fsdkjhf sdh kjdhf ksdjfkjd fkjfk");
			// UserDb userDao = new UserDb(dbConn.getConnection());
			// userDao.addUser(user);

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
			ProductDb pizzaDao = new ProductDb(dbConn.getConnection());
			// Pizza margarita = new Pizza();
			// margarita.setName("Margarita");
			// margarita.setPrice(5.99);
			// margarita.setQuantity(1);
			// margarita.setGrammage(450);
			// margarita.setSize(25);
			// pizzaDao.addProduct(margarita);
			// pizzaDao.editPizza(3, margarita);
			// Set<Pizza> pizzas = pizzaDao.getAllPizza();
			// for (Pizza pizza : pizzas) {
			// System.out.println(pizza);
			// }
			// Pizza a = pizzaDao.getPizzaById(1);
			// System.out.println(a);

			IngredientDb ingDao = new IngredientDb(dbConn.getConnection());
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
			String[] ingredients = { "Parmezan", "Gybi", "Peperoni", "Maslini" };
			Pizza peperoni = new Pizza("Peperoni", 8.50, (short) 1, 600, 30);
			for (String string : ingredients) {
				peperoni.addIngredients(new Ingredient(string));
			}
			Pizza kalcone = new Pizza("Kalcone", 8.99, (short) 2, 550, 30);
			// kalcone.setName("Kalcone");
			// kalcone.setPrice(8.99);
			// kalcone.setQuantity(2);
			// kalcone.setGrammage(550);
			// kalcone.setSize(30);
			String[] ingredients2 = { "Mocarela", "Bekon", "Luk", "Carevica" };
			for (String string : ingredients2) {
				kalcone.addIngredients(new Ingredient(string));
			}
			// pizzaDao.addProduct(kalcone);
			// pizzaDao.addProduct(peperoni);
			Set<Ingredient> in = pizzaDao.getAllPizzaIngredients(peperoni);
			for (Ingredient ingredient : in) {
				System.out.print(ingredient.getName() + " ");
			}
			dbConn.getConnection().close();
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

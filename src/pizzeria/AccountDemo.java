package pizzeria;

import pizzeria.account.Order;
import pizzeria.account.User;
import database.PizzaDb;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

public class AccountDemo {

	public static void main(String[] args) {
		Pizzeria dominos = new Pizzeria();

		try {
			User user = addUser();
			addSomePizzasToDb();
			
			Order order = new Order();
			order.setClient(user);
			
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addSomePizzasToDb() {
		PizzaDb pizzaDao = new PizzaDb();
		
		for (int pizzaIndex = 1; pizzaIndex <= 5; pizzaIndex++) {
			
		}
	}

	private static User addUser() throws InvalidArgumentValueException {
		User user = new User(0, "user123", "user123", "user123@asd.asd", "liolio", "sausage", "somewhere", "123123123");
		UserDb userDao = new UserDb();
		userDao.addUser(user);
		return user;
	}

}

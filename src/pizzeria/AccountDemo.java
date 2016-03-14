package pizzeria;

import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.Pizza;
import database.OrderDb;
import database.PizzaDb;
import database.ShopDb;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

public class AccountDemo {

	public static void main(String[] args) {
		Pizzeria dominos = new Pizzeria();

		try {
			User user = addUser();
			Order order = new Order();
			order.setClient(user);
			Shop shop = addShop();
			order.setShop(shop);
			Pizza kalcone = addPizza();
			order.addProduct(kalcone);
			OrderDb orderDao = new OrderDb();
			int orderId = orderDao.addOrder(order);
			order.setId(orderId);
			Order order2 = orderDao.getOrderById(1);
			System.out.println(order2.getClient().getEmail() + " " + order2.getProducts().get(0).getName());
			orderDao.removeOrder(order2.getId());
			orderDao.removeOrder(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static Pizza addPizza() throws InvalidArgumentValueException {
		Pizza kalcone = new Pizza(0, "Kalcone", 6.20, (short)10, 1000, 3);
		PizzaDb pizzaDao = new PizzaDb();
		int pizzaId = pizzaDao.addPizza(kalcone);
		kalcone.setId(pizzaId);
		return kalcone;
	}

	private static Shop addShop() throws InvalidArgumentValueException {
		Shop shop = new Shop(1, "София - Борово", "ул. Ген. Стефан Тошев 8");
		ShopDb shopDao = new ShopDb();
		int shopId = shopDao.addShop(shop);
		shop.setId(shopId);
		return shop;
	}

	private static User addUser() throws InvalidArgumentValueException {
		User user = new User(0, "user123", "user123", "user123@asd.asd", "liolio", "sausage", "somewhere", "123123123");
		UserDb userDao = new UserDb();
		int userId = userDao.addUser(user);
		user.setId(userId);
		return user;
	}

}

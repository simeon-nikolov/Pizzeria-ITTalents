package pizzeria;

import java.util.List;

import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.IProduct;
import pizzeria.menu.Menu;
import pizzeria.menu.Pizza;
import database.OrderDb;
import database.PizzaDb;
import database.ShopDb;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

public class Pizzeria {
	private Menu menu;
	private ShopDb shopDao = new ShopDb();
	private OrderDb orderDao = new OrderDb();
	private UserDb userDao = new UserDb();
	
	public Pizzeria() {
		this.menu = Menu.getInstance();
	}
	
	public void register(User user) {
		if (user != null) {
			userDao.addUser(user);
		}
	}
	
	public boolean login(String username, String password) 
			throws InvalidArgumentValueException {
		boolean loginSuccessful = false;
		User user = this.userDao.getUserByUsername(username);
		
		if (user != null) {
			loginSuccessful = user.getPassword().equals(password);
		}
		
		return loginSuccessful;
	}
	
	public void addShop(Shop shop) {
		if (shop != null) {
			this.shopDao.addShop(shop);
		}
	}
	
	public void removeShop(Shop shop) {
		if (shop != null) {
			this.shopDao.removeShop(shop);
		}
	}
	
	public void makeOrder(User user, Shop shop) {
		if (user != null && shop != null) {
			Order order = user.makeOrder(shop);
			this.orderDao.addOrder(order);
		}
	}
	
	public List<? extends IProduct> showMenu() {
		List<Pizza> products = new PizzaDb().getAllPizza();
		return products;
	}
	
}

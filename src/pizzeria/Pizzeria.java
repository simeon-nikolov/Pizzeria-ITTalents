package pizzeria;

import java.util.List;

import pizzeria.account.Account;
import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.IProduct;
import pizzeria.menu.Menu;
import pizzeria.menu.Pizza;
import database.AdministratorDb;
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
	private AdministratorDb adminDao = new AdministratorDb();
	
	public Pizzeria() {
		this.menu = Menu.getInstance();
	}
	
	public void register(Account acc) {
		if (acc != null) {
			acc.register();
		}
	}
	
	public boolean login(String username, String password) 
			throws InvalidArgumentValueException {
		boolean loginSuccessful = false;
		Account acc = this.getAccountByUsername(username);
		
		if (acc != null) {
			loginSuccessful = acc.login(password);
		}
		
		return loginSuccessful;
	}
	
	public Account getAccountByUsername(String username) {
		Account acc = null;
		
		if (this.adminDao.isAdmin(username)) {
			acc = this.adminDao.getAdministratorByUsername(username);
		} else {
			acc = this.userDao.getUserByUsername(username);	
		}
		
		return acc;
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

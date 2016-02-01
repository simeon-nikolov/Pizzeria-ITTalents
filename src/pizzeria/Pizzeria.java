package pizzeria;

import java.util.ArrayList;

import pizzeria.account.Account;
import pizzeria.account.Administrator;
import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.Menu;
import exceptions.InvalidArgumentValueException;

public class Pizzeria {
	private Menu menu;
	private ArrayList<Account> accounts;
	private ArrayList<Order> allOrders;
	private ArrayList<Shop> shops;
	
	public Pizzeria() {
		this.menu = Menu.getInstance();
		this.accounts = new ArrayList<Account>();
		this.allOrders = new ArrayList<Order>();
		this.shops = new ArrayList<Shop>();
		Administrator admin = null;
		
		try {
			admin = new Administrator("admin", "password", "");
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		this.accounts.add(admin);
	}
	
	public void register(User user) {
		if (user != null) {
			this.accounts.add(user);
		}
	}
	
	public Account login(String username, String password) throws InvalidArgumentValueException {
		Account account = null;
		
		if (username != null) {
			for (Account acc : this.accounts) {
				if (acc.getUsername().equals(username)) {
					account = acc;
					account.login(password);
					break;
				}
			}
		}
		
		return account;
	}
	
	public void addShop(Shop shop) {
		if (shop != null) {
			this.shops.add(shop);
		}
	}
	
	public void removeShop(Shop shop) {
		if (shop != null) {
			this.shops.remove(shop);
		}
	}
	
	public void makeOrder(User user) {
		if (user != null) {
			Order order = user.makeOrder();
			this.allOrders.add(order);
		}
	}
	
	public void showMenu() {
		System.out.println(this.menu.toString());
	}
	
}

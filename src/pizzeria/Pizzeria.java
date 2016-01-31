package pizzeria;

import java.util.ArrayList;

import pizzeria.account.Account;
import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.Menu;

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
	}
	
	public void register(User user) {
		if (user != null) {
			this.accounts.add(user);
		}
	}
	
}

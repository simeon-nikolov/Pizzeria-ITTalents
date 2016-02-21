package pizzeria;

import pizzeria.account.Account;
import pizzeria.account.Order;
import pizzeria.account.User;
import pizzeria.menu.Menu;
import exceptions.InvalidArgumentValueException;

public class Pizzeria {
	private Menu menu;
	
	public Pizzeria() {
		this.menu = Menu.getInstance();

	}
	
	public void register(User user) {
		if (user != null) {
			
		}
	}
	
	public Account login(String username, String password) 
			throws InvalidArgumentValueException {
		Account account = null;
		
		if (username != null) {
			
		}
		
		return account;
	}
	
	public void addShop(Shop shop) {
		if (shop != null) {
			
		}
	}
	
	public void removeShop(Shop shop) {
		if (shop != null) {
			
		}
	}
	
	public void makeOrder(User user, Shop shop) {
		if (user != null && shop != null) {
			Order order = user.makeOrder(shop);
			
		}
	}
	
	public void showMenu() {
		System.out.println(this.menu.toString());
	}
	
}

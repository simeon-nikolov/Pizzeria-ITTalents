package pizzeria;

import pizzeria.account.Administrator;
import pizzeria.account.User;
import exceptions.InvalidArgumentValueException;

public class Demo {

	public static void main(String[] args) {
		Pizzeria dominos = new Pizzeria();
		
		try {
			Administrator admin = (Administrator) dominos.login("admin", "admin");
			User user = new User("testUser", "123456", "user@test.com", "pesho", "peshov", "nqakyv adres", "0876123456");
			dominos.register(user);
			
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		}
	}

}

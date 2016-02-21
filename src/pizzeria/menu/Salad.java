package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Salad extends Food {

	public Salad(int id, double price, short quantity, String name, int grammage) throws InvalidArgumentValueException {
		super(id, price, quantity, name, grammage);
	}

}

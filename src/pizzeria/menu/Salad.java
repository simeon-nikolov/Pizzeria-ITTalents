package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Salad extends Food {

	public Salad(double price, short quantity, String name, int grammage) throws InvalidArgumentValueException {
		super(price, quantity, name, grammage);
	}

}

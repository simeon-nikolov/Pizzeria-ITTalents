package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Pasta extends Food {

	public Pasta(int id,double price, short quantity, String name, int grammage) throws InvalidArgumentValueException {
		super(id, price, quantity, name, grammage);
	}

	

	
}

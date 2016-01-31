package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Pasta extends Food {

	public Pasta(double price, short quantity, String name, int grammage) throws InvalidArgumentValueException {
		super(price, quantity, name, grammage);
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}

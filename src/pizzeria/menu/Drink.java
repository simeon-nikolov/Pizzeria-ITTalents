package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Drink extends Product {
	private static final String SIZE_MESSAGE_ERROR = "Size is not correct";
	private static final int MIN_SIZE_DRINK = 0;
	private int size;

	public Drink(double price, short quantity, String name, int size) throws InvalidArgumentValueException {
		super(price, quantity, name);
		this.setSize(size);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) throws InvalidArgumentValueException {
		this.validateSize(size);
		this.size = size;
	}

	private void validateSize(int size) throws InvalidArgumentValueException {
		if (size < MIN_SIZE_DRINK) {
			throw new InvalidArgumentValueException(SIZE_MESSAGE_ERROR);
		}
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}

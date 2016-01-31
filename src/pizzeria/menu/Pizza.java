package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Pizza extends Food {
	private static final String SIZE_MESSAGE_ERROR = "Size is not correct";
	private static final int MIN_SIZE_PIZZA = 0;
	private int size;

	public Pizza(double price, short quantity, String name, int grammage, int size) throws InvalidArgumentValueException {
		super(price, quantity, name, grammage);
		setSize(size);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) throws InvalidArgumentValueException {
		this.validateSize(size);
		this.size = size;
	}

	private void validateSize(int size) throws InvalidArgumentValueException {
		if (size < MIN_SIZE_PIZZA) {
			throw new InvalidArgumentValueException(SIZE_MESSAGE_ERROR);
		}
	}
	
//	public double priceForPizza (Pizza p){
//		return p.getSize()*0.2 + p.getPrice() + p.
//	}

}

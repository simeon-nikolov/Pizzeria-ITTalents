package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Pizza extends Food {
	private static final String SIZE_MESSAGE_ERROR = "Size is not correct";
	private static final int MIN_SIZE_PIZZA = 0;
	private int size;

	public Pizza() {
		
	}

	public Pizza(int id, String name, double price, short quantity, int grammage, int size)
			throws InvalidArgumentValueException {
		super(id, price, quantity, name, grammage);
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

	@Override
	public String toString() {
		return super.toString() + " size " + size;
	}

	// public double priceForPizza(Pizza p) {
	// int sum = 0;
	// for (Ingredient c : p.getIngredients()) {
	// sum++;
	// }
	// return p.getQuantity() * (p.getGrammage() * 10 + sum * 0.20);
	// }

}

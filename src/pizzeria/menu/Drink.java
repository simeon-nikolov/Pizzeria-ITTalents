package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Drink extends Product {
	private static final String SIZE_MESSAGE_ERROR = "Size is not correct";
	private static final int MIN_SIZE_DRINK = 0;
	private double size;

	public Drink(int id,double price, String image, String name, double litres) throws InvalidArgumentValueException {
		super(id,price, image, name);
		this.setSize(litres);
	}

	public double getSize() {
		return size;
	}

	public void setSize(double litres) throws InvalidArgumentValueException {
		this.validateSize(litres);
		this.size = litres;
	}

	private void validateSize(double size) throws InvalidArgumentValueException {
		if (size < MIN_SIZE_DRINK) {
			throw new InvalidArgumentValueException(SIZE_MESSAGE_ERROR);
		}
	}

	@Override
	public int getId() {
		return 0;
	}



}

package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public abstract class Product implements IProduct {
	private static final String PRICE_MESSAGE_ERROR = "Price is not correct";
	private static final String GRAMMAGE_IS_MESSAGE_ERROR = "Grammage is not correct";
	private static final String NAME_IS_EMPTY_ERROR_MESSAGE = "Name is empty!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	private static final int MIN_PRICE = 0;
	private static final int MAX_QUANTITY = 100;
	private static final int MIN_QUANTITY = 0;
	private double price;
	private short quantity;
	private String name;

	public Product(double price, short quantity, String name) throws InvalidArgumentValueException {
		setName(name);
		setPrice(price);
		setQuantity(quantity);
	}

	@Override
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws InvalidArgumentValueException {
		this.validatePrice(price);
		this.price = price;
	}

	private void validatePrice(double price) throws InvalidArgumentValueException {
		if (price < MIN_PRICE) {
			throw new InvalidArgumentValueException(PRICE_MESSAGE_ERROR);
		}
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) throws InvalidArgumentValueException {
		this.validateQuantity(quantity);
		this.quantity = quantity;
	}

	private void validateQuantity(short quantity) throws InvalidArgumentValueException {
		if (quantity < MIN_QUANTITY && quantity > MAX_QUANTITY) {
			throw new InvalidArgumentValueException(GRAMMAGE_IS_MESSAGE_ERROR);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidArgumentValueException {
		this.validateName(name);
		this.name = name;
	}

	private void validateName(String name) throws InvalidArgumentValueException {
		if (name == null) {
			throw new InvalidArgumentValueException(NAME_IS_NULL_ERROR_MESSAGE);
		}

		if (name.equals("")) {
			throw new InvalidArgumentValueException(NAME_IS_EMPTY_ERROR_MESSAGE);
		}
	}
}

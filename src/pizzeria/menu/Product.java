package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public abstract class Product implements IProduct {

	private static final String PRICE_MESSAGE_ERROR = "Price is not correct";
	private static final String NAME_IS_EMPTY_ERROR_MESSAGE = "Name is empty!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	private static final int MIN_PRICE = 0;
	private int id;
	private double price;
	private String image;
	private String name;

	public Product() {

	}

	public Product(int id, double price, String image, String name) throws InvalidArgumentValueException {
		this.id = id;
		setName(name);
		setPrice(price);
		setImage(image);
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double price) throws InvalidArgumentValueException {
		this.validatePrice(price);
		this.price = price;
	}

	private void validatePrice(double price) throws InvalidArgumentValueException {
		if (price < MIN_PRICE) {
			throw new InvalidArgumentValueException(PRICE_MESSAGE_ERROR);
		}
	}

	

	public String getImage() {
		return image;
	}
	@Override
	public void setImage(String image) throws InvalidArgumentValueException {
		validateName(image);
		this.image = image;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", image=" + image + ", name=" + name + "]";
	}
}

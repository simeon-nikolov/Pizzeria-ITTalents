package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public class Drink extends Product {
	private static final String SIZE_MESSAGE_ERROR = "Size is not correct";
	private static final int MIN_SIZE_DRINK = 0;
	private int size;

	public Drink(int id,double price, short quantity, String name, int size) throws InvalidArgumentValueException {
		super(id,price, quantity, name);
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
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setGrammage(int grammage) throws InvalidArgumentValueException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGrammage() {
		// TODO Auto-generated method stub
		return 0;
	}

}

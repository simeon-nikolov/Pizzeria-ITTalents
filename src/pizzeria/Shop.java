package pizzeria;

import exceptions.InvalidArgumentValueException;

public class Shop {
	private static final String ADDRESS_IS_NULL_ERROR_MESSAGE = "Address is null!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	
	private int id;
	private String name;
	private String address;
	
	public Shop(int id, String name, String address) throws InvalidArgumentValueException {
		this.id = id;
		this.setName(name);
		this.setAddress(address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidArgumentValueException {
		if (name == null) {
			throw new InvalidArgumentValueException(NAME_IS_NULL_ERROR_MESSAGE);
		}
		
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws InvalidArgumentValueException {
		if (address == null) {
			throw new InvalidArgumentValueException(ADDRESS_IS_NULL_ERROR_MESSAGE);
		}
		
		this.address = address;
	}
}

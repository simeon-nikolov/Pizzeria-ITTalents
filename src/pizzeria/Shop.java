package pizzeria;

import exceptions.InvalidArgumentValueException;

public class Shop {
	private static final String ADDRESS_IS_NULL_ERROR_MESSAGE = "Address is null!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	
	private long id;
	private String name;
	private String address;
	private static long count = 0;
	
	public Shop(String name, String address) throws InvalidArgumentValueException {
		this.setName(name);
		this.setAddress(address);
		count++;
		this.id = count;
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

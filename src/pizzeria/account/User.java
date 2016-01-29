package pizzeria.account;

import exceptions.InvalidArgumentValueException;

public class User extends Account {
	private static final String PHONE_NUMBER_NULL_ERROR_MESSAGE = "The phone number is null!";
	private static final String ADDRESS_IS_NULL_ERROR_MESSAGE = "Address is null!";
	private static final String NAME_IS_EMPTY_ERROR_MESSAGE = "Name is empty!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;

	public User(String username, String password, String email, String firstName,
			String lastName, String address, String phoneNumber) throws InvalidArgumentValueException {
		super(username, password, email);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidArgumentValueException {
		this.validateName(firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidArgumentValueException {
		this.validateName(lastName);
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws InvalidArgumentValueException {
		this.validateAddress(address);
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidArgumentValueException {
		this.validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	private void validateName(String name) throws InvalidArgumentValueException {
		if (name == null) {
			throw new InvalidArgumentValueException(NAME_IS_NULL_ERROR_MESSAGE);
		}
		
		if (name.equals("")) {
			throw new InvalidArgumentValueException(NAME_IS_EMPTY_ERROR_MESSAGE);
		}
	}
	
	private void validateAddress(String address) throws InvalidArgumentValueException {
		if (address == null) {
			throw new InvalidArgumentValueException(ADDRESS_IS_NULL_ERROR_MESSAGE);
		}
	}
	
	private void validatePhoneNumber(String phoneNumber) throws InvalidArgumentValueException {
		if (phoneNumber == null) {
			throw new InvalidArgumentValueException(PHONE_NUMBER_NULL_ERROR_MESSAGE);
		}
	}
}

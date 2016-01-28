package pizzeria.account;

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
			String lastName, String address, String phoneNumber) {
		super(username, password, email);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.validateName(firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.validateName(lastName);
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.validateAddress(address);
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	private void validateName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_IS_NULL_ERROR_MESSAGE);
		}
		
		if (name.equals("")) {
			throw new IllegalArgumentException(NAME_IS_EMPTY_ERROR_MESSAGE);
		}
	}
	
	private void validateAddress(String address) {
		if (address == null) {
			throw new IllegalArgumentException(ADDRESS_IS_NULL_ERROR_MESSAGE);
		}
	}
	
	private void validatePhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			throw new IllegalArgumentException(PHONE_NUMBER_NULL_ERROR_MESSAGE);
		}
	}
}

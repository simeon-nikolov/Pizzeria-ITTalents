package pizzeria.account;

import exceptions.InvalidArgumentValueException;

public class Administrator extends Account {

	public Administrator(String username, String password, String email) throws InvalidArgumentValueException {
		super(username, password, email);
	}

}

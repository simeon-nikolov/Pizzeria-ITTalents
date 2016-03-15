package pizzeria.account;

import exceptions.InvalidArgumentValueException;

public class Administrator extends Account {
	
	public Administrator() {
		
	}

	public Administrator(int id, String username, String password, String email) 
			throws InvalidArgumentValueException {
		super(id, username, password, email);
	}

}

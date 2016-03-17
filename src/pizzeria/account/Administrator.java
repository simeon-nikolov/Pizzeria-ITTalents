package pizzeria.account;

import database.AdministratorDb;
import exceptions.InvalidArgumentValueException;

public class Administrator extends Account {
	private AdministratorDb adminDao = new AdministratorDb();
	
	public Administrator() {
		
	}

	public Administrator(int id, String username, String password, String email) 
			throws InvalidArgumentValueException {
		super(id, username, password, email);
	}

	@Override
	public void register() {
		this.adminDao.addAdministrator(this);
	}
	
	@Override
	public boolean isAdmin() {
		return true;
	}

}

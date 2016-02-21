package pizzeria;

import java.sql.SQLException;

import pizzeria.account.Administrator;
import database.AdministratorDb;
import database.DatabaseConnection;
import exceptions.InvalidArgumentValueException;

public class Demo {

	public static void main(String[] args) {
		Pizzeria dominos = new Pizzeria();
		
		try {
			DatabaseConnection dbConn = new DatabaseConnection();
			AdministratorDb adminDao = new AdministratorDb(dbConn.getConnection());
			Administrator admin = new Administrator("admin2", "123456", "admin2@test.com");
			adminDao.addAdministrator(admin);
			dbConn.getConnection().close();
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

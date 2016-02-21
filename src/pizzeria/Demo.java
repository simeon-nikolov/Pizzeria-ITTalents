package pizzeria;

import java.sql.SQLException;
import java.util.Set;

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
			Administrator admin = new Administrator();
			admin.setPassword("123123");
			admin.setUsername("admincho");
			admin.setEmail("admin2@test.com");
			//adminDao.addAdministrator(admin);
			//adminDao.editAdministrator(8, admin);
			//adminDao.removeAdministrator(7);
			Set<Administrator> admins = adminDao.getAllAdministrator();
			
			for (Administrator administrator : admins) {
				System.out.println(administrator.getUsername());
			}
			
			dbConn.getConnection().close();
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

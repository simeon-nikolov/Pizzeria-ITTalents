package pizzeria;

import java.sql.Connection;
import java.sql.SQLException;

import pizzeria.account.Administrator;
import database.AdministratorDb;
import database.DatabaseConnection;
import exceptions.InvalidArgumentValueException;

public class AccountDemo {

	public static void main(String[] args) {
		Pizzeria dominos = new Pizzeria();
		Connection dbConnection = DatabaseConnection.getConnection();

		try {
			AdministratorDb adminDao = new AdministratorDb(dbConnection);
			Administrator admin = new Administrator();
			admin.setPassword("123123");
			admin.setUsername("admincho");
			admin.setEmail("admin2@test.com");
			// adminDao.addAdministrator(admin);
			// adminDao.editAdministrator(8, admin);
			// adminDao.removeAdministrator(7);
			// Set<Administrator> admins = adminDao.getAllAdministrator();
			//
			// for (Administrator administrator : admins) {
			// System.out.println(administrator.getUsername());
			// }

			// User user = new User();
			// user.setFirstName("Hsalfkdj");
			// user.setLastName("asldjalsjkd");
			// user.setUsername("user123");
			// user.setPassword("321321");
			// user.setEmail("user@asld.asd");
			// user.setPhoneNumber("11231123");
			// user.setAddress("fhs kkj fsdkjhf sdh kjdhf ksdjfkjd fkjfk");
			// UserDb userDao = new UserDb(dbConnection);
			// userDao.addUser(user);
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

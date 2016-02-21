package pizzeria;

import java.sql.SQLException;

import pizzeria.account.Administrator;
import pizzeria.account.User;
import database.AdministratorDb;
import database.DatabaseConnection;
import database.UserDb;
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
//			adminDao.addAdministrator(admin);
//			adminDao.editAdministrator(8, admin);
//			adminDao.removeAdministrator(7);
//			Set<Administrator> admins = adminDao.getAllAdministrator();
//			
//			for (Administrator administrator : admins) {
//				System.out.println(administrator.getUsername());
//			}
			
			User user = new User();
			user.setFirstName("Hsalfkdj");
			user.setLastName("asldjalsjkd");
			user.setUsername("user123");
			user.setPassword("321321");
			user.setEmail("user@asld.asd");
			user.setPhoneNumber("11231123");
			user.setAddress("fhs kkj fsdkjhf sdh kjdhf ksdjfkjd fkjfk");
			UserDb userDao = new UserDb(dbConn.getConnection());
			userDao.addUser(user);
			
			dbConn.getConnection().close();
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

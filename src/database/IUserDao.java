package database;

import java.util.List;

import pizzeria.account.User;

public interface IUserDao {

	int addUser(User user);

	void editUser(int idUser, User user);

	void removeUser(int idUser);

	User getUserById(int idUser);

	User getUserByUsername(String username);

	User getUserByEmail(String email);

	List<User> getAllUsers();

}
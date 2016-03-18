package database;

import pizzeria.account.Order;

public interface IOrderDao {

	int addOrder(Order order);

	void editOrder(int idOrder, Order order);

	void removeOrder(int idOrder);

	Order getOrderById(int idOrder);

	void deleteUserOrders(int userId);
}
package database;

import java.util.List;

import pizzeria.menu.IProduct;

public interface IShoppingCartDao {

	void addProductToShoppingCart(int userId, int productId, int quantity);

	void removeProductFromShoppingCart(int userId, int productId);

	void emptyShoppingCart(int userId);

	List<IProduct> getProducts(int userId);

}
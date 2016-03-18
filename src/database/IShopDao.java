package database;

import java.util.List;

import pizzeria.Shop;

public interface IShopDao {

	int addShop(Shop shop);

	void updateShop(Shop shop);

	void removeShop(Shop shop);

	Shop getShopById(int shopId);

	List<Shop> getAllShops();

}
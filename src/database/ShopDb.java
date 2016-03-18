package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import pizzeria.Shop;
import exceptions.InvalidArgumentValueException;

public class ShopDb extends DataAccessObject implements IShopDao {	
	private Connection connection = super.getConnection();
	
	@Override
	public int addShop(Shop shop) {
		String insertShopSql = "INSERT INTO `pizzeria`.`Shop` (`name`, `address`) VALUES "
				+ "(?, ?);";
		int shopId = 0;
		
		try {
			PreparedStatement ps = connection.prepareStatement(insertShopSql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, shop.getName());
			ps.setString(2, shop.getAddress());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			shopId = rs.getInt(1);
			connection.commit();
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return shopId;
	}
	
	@Override
	public void updateShop(Shop shop) {
		String updateShopSql = "UPDATE `pizzeria`.`Shop` SET `name`=?, `address`=? WHERE `idShop`=?;";
		
		try {
			PreparedStatement ps = connection.prepareStatement(updateShopSql);
			ps.setString(1, shop.getName());
			ps.setString(2, shop.getAddress());
			ps.setInt(3, shop.getId());
			int rows = ps.executeUpdate();
			connection.commit();
			
			if (rows == 0) {
				throw new SQLException("No rows affected!");
			}
			
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeShop(Shop shop) {
		String deleteShopSql = "DELETE FROM `pizzeria`.`Shop` WHERE `idShop`=?;";
		
		try {
			PreparedStatement ps = connection.prepareStatement(deleteShopSql);
			ps.setInt(1, shop.getId());
			int rows = ps.executeUpdate();
			connection.commit();
			
			if (rows == 0) {
				throw new SQLException("No rows affected!");
			}
			
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public Shop getShopById(int shopId) {
		String selectShopSql = "SELECT * FROM `pizzeria`.`Shop` WHERE `idShop`=?;";
		Shop shop = new Shop();
		
		try {
			PreparedStatement ps = connection.prepareStatement(selectShopSql);
			ps.setInt(1, shopId);
			ResultSet rs = ps.executeQuery();
			connection.commit();
			rs.next();
			shop.setId(rs.getInt("idShop"));
			shop.setName(rs.getString("name"));
			shop.setAddress(rs.getString("address"));
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return shop;
	}
	
	@Override
	public List<Shop> getAllShops() {
		String selectAllShopsSql = "SELECT * FROM `pizzeria`.`Shop`;";
		List<Shop> shops = new LinkedList<Shop>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(selectAllShopsSql);
			ResultSet rs = ps.executeQuery();
			connection.commit();
			
			while (rs.next()) {
				Shop shop = new Shop();
				shop.setId(rs.getInt("idShop"));
				shop.setName(rs.getString("name"));
				shop.setAddress(rs.getString("address"));
				shops.add(shop);
			}
			
			System.out.println("Success!");
		} catch (SQLException e) {	
			try {
				connection.rollback();
				System.out.println("Transaction ROLLBACK");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		return shops;
	}
}

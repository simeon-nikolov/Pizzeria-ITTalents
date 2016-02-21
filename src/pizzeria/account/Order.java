package pizzeria.account;

import java.util.ArrayList;
import java.util.List;

import pizzeria.Shop;
import pizzeria.menu.IProduct;
import exceptions.InvalidArgumentValueException;

public class Order {
	private long id;
	private User client;
	private List<IProduct> products;
	private double sum;
	private boolean isReady;
	private boolean isReceived;
	private Shop shop;
	
	public Order() {
		
	}
	
	public Order(int id, User client, ArrayList<IProduct> produts, double sum, Shop shop) 
			throws InvalidArgumentValueException {
		if (client == null) {
			throw new InvalidArgumentValueException("Client is null!");
		}
		
		if (produts == null) {
			throw new InvalidArgumentValueException("Products is null!");
		}
		
		if (produts.size() == 0) {
			throw new InvalidArgumentValueException("There are no products to be ordered!");
		}
		
		if (sum < 0.0) {
			throw new InvalidArgumentValueException("Sum can't be negative value!");
		}
		
		if (shop == null) {
			throw new InvalidArgumentValueException("Shop is null!");
		}
		
		this.id = id;
		this.client = client;
		this.products = new ArrayList<IProduct>();
		this.products.addAll(produts);	
		this.sum = sum;
		this.shop = shop;
	}
	
	public long getId() {
		return this.id;
	}
	
	public User getClient() {
		return this.client;
	}
	
	public void setReady() {
		this.isReady = true;
	}
	
	public boolean isReady() {
		return this.isReady;
	}
	
	public void setReceived() {
		this.isReceived = true;
	}

	public boolean isReceived() {
		return this.isReceived;
	}
	
	public Shop getShop() {
		return this.shop;
	}
	
	public List<IProduct> getProducts() {
		List<IProduct> result = new ArrayList<IProduct>();
		
		if (this.products != null && this.products.size() > 0) {
			result.addAll(this.products);
		}
		
		return result;
	}
	
}

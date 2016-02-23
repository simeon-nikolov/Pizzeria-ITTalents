package pizzeria.account;

import java.util.ArrayList;
import java.util.List;

import pizzeria.Shop;
import pizzeria.menu.IProduct;
import exceptions.InvalidArgumentValueException;

public class Order {
	private int id;
	private User client;
	private List<IProduct> products;
	private boolean isReady;
	private boolean isReceived;
	private Shop shop;
	
	public Order() {
		
	}
	
	public Order(User client, ArrayList<IProduct> produts, Shop shop) 
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
		
		if (shop == null) {
			throw new InvalidArgumentValueException("Shop is null!");
		}
		
		this.client = client;
		this.products = new ArrayList<IProduct>();
		this.products.addAll(produts);	
		this.shop = shop;
	}
	
	public double getSum() {
		double sum = 0;
		
		for (IProduct product : this.products) {
			sum += product.getPrice();
		}
		
		return sum;
	}

	public int getId() {
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

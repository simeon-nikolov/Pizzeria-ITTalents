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
		this.products = new ArrayList<IProduct>();
	}
	
	public Order(int id, User client, Shop shop) 
			throws InvalidArgumentValueException {
		this();
		this.setShop(shop);
		this.setClient(client);
	}
	
	public void addProduct(IProduct product) throws InvalidArgumentValueException {
		if (product == null) {
			throw new InvalidArgumentValueException("Product is null!");
		}
		
		this.products.add(product);
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setClient(User client) throws InvalidArgumentValueException {
		if (client == null) {
			throw new InvalidArgumentValueException("Client is null!");
		}
		
		this.client = client;
	}
	
	public User getClient() {
		return this.client;
	}
	
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	public boolean isReady() {
		return this.isReady;
	}
	
	public void setReceived(boolean isReceived) {
		this.isReceived = isReceived;
	}

	public boolean isReceived() {
		return this.isReceived;
	}
	
	public Shop getShop() {
		return this.shop;
	}
	
	public void setShop(Shop shop) throws InvalidArgumentValueException {
		if (shop == null) {
			throw new InvalidArgumentValueException("Shop is null!");
		}
		
		this.shop = shop;
	}
	
	public List<IProduct> getProducts() {
		List<IProduct> result = new ArrayList<IProduct>();
		
		if (this.products != null && this.products.size() > 0) {
			result.addAll(this.products);
		}
		
		return result;
	}
	
}

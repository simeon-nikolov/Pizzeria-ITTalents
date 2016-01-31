package pizzeria.account;

import java.util.ArrayList;

import pizzeria.menu.IProduct;
import exceptions.InvalidArgumentValueException;

public class Order {
	private long id;
	private User client;
	private ArrayList<IProduct> products;
	private double sum;
	private boolean isReady;
	private boolean isReceived;
	private static long count = 0;
	
	public Order(User client, ArrayList<IProduct> produts, double sum) throws InvalidArgumentValueException {
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
		
		this.client = client;
		this.products = new ArrayList<IProduct>();
		this.products.addAll(produts);	
		this.sum = sum;
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
	
	public ArrayList<IProduct> getProducts() {
		ArrayList<IProduct> result = new ArrayList<IProduct>();
		
		if (this.products != null && this.products.size() > 0) {
			result.addAll(this.products);
		}
		
		return result;
	}
	
}

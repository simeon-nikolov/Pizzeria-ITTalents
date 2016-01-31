package pizzeria.account;

import java.util.ArrayList;

import pizzeria.menu.IProduct;
import exceptions.InvalidArgumentValueException;

public class Order {
	private long id;
	private User client;
	private ArrayList<IProduct> products;
	private static long count = 0;
	
	public Order(User client, ArrayList<IProduct> produts) throws InvalidArgumentValueException {
		if (client == null) {
			throw new InvalidArgumentValueException("Client is null!");
		}
		
		if (produts == null) {
			throw new InvalidArgumentValueException("Products is null!");
		}
		
		if (produts.size() == 0) {
			throw new InvalidArgumentValueException("There are no products to be ordered!");
		}
		
		this.client = client;
		this.products = new ArrayList<IProduct>();
		this.products.addAll(produts);	
	}
	
	public long getId() {
		return this.id;
	}
	
	public User getClient() {
		return this.client;
	}
	
	public ArrayList<IProduct> getProducts() {
		ArrayList<IProduct> result = new ArrayList<IProduct>();
		
		if (this.products != null && this.products.size() > 0) {
			result.addAll(this.products);
		}
		
		return result;
	}
	
}

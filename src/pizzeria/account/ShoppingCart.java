package pizzeria.account;

import java.util.ArrayList;

import pizzeria.menu.IProduct;
import exceptions.InvalidArgumentValueException;

public class ShoppingCart {
	private static final String PRODUCT_IS_NULL_ERROR_MESSAGE = "Product is null!";
	private static final String OWNER_IS_NULL_ERROR_MESSAGE = "Owner is null!";
	
	private long id;
	private User owner;
	private ArrayList<IProduct> products;
	private double sum;
	private static long count = 0;
	
	public ShoppingCart(User owner) throws InvalidArgumentValueException {
		super();
		
		if (owner == null) {
			throw new InvalidArgumentValueException(OWNER_IS_NULL_ERROR_MESSAGE);
		}
		
		this.owner = owner;
		this.products = new ArrayList<IProduct>();
		this.sum = 0.0;
		ShoppingCart.count++;
		this.id = ShoppingCart.count;
	}
	
	public void addProduct(IProduct product) throws InvalidArgumentValueException {
		this.validateProduct(product);
		this.sum += product.getPrice();
		this.products.add(product);
	}
	
	public void removeProduct(IProduct product) throws InvalidArgumentValueException {
		this.validateProduct(product);
		
		for (int index = 0; index < this.products.size(); index++) {
			IProduct currentProduct = this.products.get(index);
			if (product.getId() == currentProduct.getId()) {
				this.sum -= currentProduct.getPrice();
				this.products.remove(index);
				break;
			}
		}
	}
	
	public ArrayList<IProduct> getProducts() {
		ArrayList<IProduct> result = new ArrayList<IProduct>();
		
		if (this.products != null && this.products.size() > 0) {
			result.addAll(this.products);
		}
		
		return result;
	}
	
	public double getSum() {
		return this.sum;
	}
	
	public void empty() {
		this.products.clear();
		this.sum = 0.0;
	}

	private void validateProduct(IProduct product) throws InvalidArgumentValueException {
		if (product == null) {
			throw new InvalidArgumentValueException(PRODUCT_IS_NULL_ERROR_MESSAGE);
		}
	}
}

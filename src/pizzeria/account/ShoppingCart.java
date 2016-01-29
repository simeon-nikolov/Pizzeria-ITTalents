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
	
	public ShoppingCart(User owner) throws InvalidArgumentValueException {
		super();
		
		if (owner == null) {
			throw new InvalidArgumentValueException(OWNER_IS_NULL_ERROR_MESSAGE);
		}
		
		this.owner = owner;
		this.products = new ArrayList<IProduct>();
		this.sum = 0.0;
	}
	
	public void addProduct(IProduct product) throws InvalidArgumentValueException {
		this.validateProduct(product);
		this.products.add(product);
	}
	
	public void removeProduct(IProduct product) throws InvalidArgumentValueException {
		this.validateProduct(product);
		
		for (int index = 0; index < this.products.size(); index++) {
			if (product.getId() == this.products.get(index).getId()) {
				this.products.remove(index);
				break;
			}
		}
	}

	private void validateProduct(IProduct product) throws InvalidArgumentValueException {
		if (product == null) {
			throw new InvalidArgumentValueException(PRODUCT_IS_NULL_ERROR_MESSAGE);
		}
	}
}

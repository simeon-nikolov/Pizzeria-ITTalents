package pizzeria.account;

import java.util.ArrayList;

import pizzeria.menu.IProduct;

public class ShoppingCart {
	private static final String PRODUCT_IS_NULL_ERROR_MESSAGE = "Product is null!";

	private static final String OWNER_IS_NULL_ERROR_MESSAGE = "Owner is null!";
	
	private User owner;
	private ArrayList<IProduct> products;
	private double sum;
	
	public ShoppingCart(User owner) {
		super();
		
		if (owner == null) {
			throw new IllegalArgumentException(OWNER_IS_NULL_ERROR_MESSAGE);
		}
		
		this.owner = owner;
		this.products = new ArrayList<IProduct>();
		this.sum = 0.0;
	}
	
	public void addProduct(IProduct product) {
		validateProduct(product);
		this.products.add(product);
	}
	
	public void removeProduct(IProduct product) {
		this.validateProduct(product);
		
		for (int index = 0; index < this.products.size(); index++) {
			if (product.getId() == this.products.get(index).getId()) {
				this.products.remove(index);
				break;
			}
		}
	}

	private void validateProduct(IProduct product) {
		if (product == null) {
			throw new IllegalArgumentException(PRODUCT_IS_NULL_ERROR_MESSAGE);
		}
	}
}

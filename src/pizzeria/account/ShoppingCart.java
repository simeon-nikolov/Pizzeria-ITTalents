package pizzeria.account;

import java.util.List;

import pizzeria.menu.IProduct;
import database.ShoppingCartDb;
import exceptions.InvalidArgumentValueException;

public class ShoppingCart {
	private static final String QUANTITY_ERROR_MESSAGE = "Quantity must be a positive number!";
	private static final String PRODUCT_IS_NULL_ERROR_MESSAGE = "Product is null!";
	private static final String OWNER_IS_NULL_ERROR_MESSAGE = "Owner is null!";
	
	private int id;
	private User owner;
	private ShoppingCartDb cartDao = new ShoppingCartDb();
	
	public ShoppingCart(User owner) throws InvalidArgumentValueException {
		if (owner == null) {
			throw new InvalidArgumentValueException(OWNER_IS_NULL_ERROR_MESSAGE);
		}
		
		this.owner = owner;
	}
	
	public void addProduct(IProduct product, int quantity) throws InvalidArgumentValueException {
		this.validateProduct(product);
		
		if (quantity <= 0) {
			throw new InvalidArgumentValueException(QUANTITY_ERROR_MESSAGE);
		}
		
		this.cartDao.addProductToShoppingCart(owner.getId(), product.getId(), quantity);
	}
	
	public void removeProduct(IProduct product) throws InvalidArgumentValueException {
		this.validateProduct(product);
		this.cartDao.removeProductFromShoppingCart(this.owner.getId(), product.getId());
	}
	
	public List<IProduct> getProducts() {
		return this.cartDao.getProducts(owner.getId());
	}
	
	public double getSum() {
		double sum = 0.0;
		
		List<IProduct> products = this.getProducts();
		for (IProduct product : products) {
			sum += product.getPrice();
		}
		
		return sum;
	}
	
	public void empty() {
		cartDao.emptyShoppingCart(this.owner.getId());
	}

	private void validateProduct(IProduct product) throws InvalidArgumentValueException {
		if (product == null) {
			throw new InvalidArgumentValueException(PRODUCT_IS_NULL_ERROR_MESSAGE);
		}
	}
}

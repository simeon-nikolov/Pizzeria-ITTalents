package pizzeria.menu;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.InvalidArgumentValueException;

public class Menu {

	private static final String NULL_PRODUCT_MESSAGE = "Product is null";
	private static Menu instance = null;
	private ArrayList<IProduct> products;

	private Menu() {
		this.products = new ArrayList<IProduct>();
	}

	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}

	public void addProduct(IProduct product) throws InvalidArgumentValueException {
		if (product != null) {
			products.add(product);
			return;
		} else {
			throw new InvalidArgumentValueException(NULL_PRODUCT_MESSAGE);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (IProduct iProduct : products) {
			sb.append(iProduct.getName() + " " + iProduct.getPrice());
			sb.append("\n");
		}
		return sb.toString();
	}
}

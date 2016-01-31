package pizzeria.menu;

import java.util.ArrayList;

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
		} else {
			throw new InvalidArgumentValueException(NULL_PRODUCT_MESSAGE);
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (IProduct iProduct : products) {
			sb.append("Product : ");
			sb.append(iProduct.getName());
			if (iProduct instanceof Food) {
				Food p = (Food) iProduct;
				for (Ingredient ingredients : p.getIngredients()) {
					sb.append(ingredients + " ");
				}
				sb.append(" " + iProduct.getPrice());
			}
			sb.append(iProduct.getPrice());
			sb.append("\n");
		}
		return sb.toString();
	}
}

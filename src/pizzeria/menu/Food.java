package pizzeria.menu;

import java.util.ArrayList;

public abstract class Food extends Product {
	private static final String GRAMMAGE_MESSAGE_ERROR = "Grammage is not correct";
	private static final int MIN_GRAMMAGE = 0;
	private int grammage;
	private ArrayList<Ingredient> ingredients;

	public Food(double price, short quantity, String name, int grammage) {
		super(price, quantity, name);
		setGrammage(grammage);
	}

	public int getGrammage() {
		return grammage;
	}

	public void setGrammage(int grammage) {
		this.validateGrammage(grammage);
		this.grammage = grammage;
	}

	private void validateGrammage(int grammage) {
		if (grammage < MIN_GRAMMAGE)
			throw new IllegalArgumentException(GRAMMAGE_MESSAGE_ERROR);
	}
}

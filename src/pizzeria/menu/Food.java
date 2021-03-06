package pizzeria.menu;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidArgumentValueException;

public abstract class Food extends Product {
	private static final int MAX_NUMBER_OF_INGREDIENTS = 10;
	private static final String GRAMMAGE_MESSAGE_ERROR = "Grammage is not correct";
	private static final int MIN_GRAMMAGE = 0;
	private int id;
	private int grammage;
	private List<Ingredient> ingredients;
	private short numberOfIngredients = 0;
	
	public Food(){
		
	}

	public Food(int id,double price, String image, String name, int grammage) throws InvalidArgumentValueException {
		super(id, price, image, name);
		setGrammage(grammage);
		this.ingredients = new ArrayList<Ingredient>();
	}

	public void addIngredient(Ingredient ingredient) {
		if (this.numberOfIngredients < MAX_NUMBER_OF_INGREDIENTS) {
			if (ingredient != null) {
				ingredients.add(ingredient);
				this.numberOfIngredients++;
			}
		} else {
			System.out.println("Maximum limit of ingredients is 10");
			return;
		}
	}

	public List<Ingredient> getIngredients() {
		List<Ingredient> result = new ArrayList<Ingredient>();
		result.addAll(this.ingredients);
		return result;
	}

	public int getGrammage() {
		return grammage;
	}

	public void setGrammage(int grammage) throws InvalidArgumentValueException {
		this.validateGrammage(grammage);
		this.grammage = grammage;
	}

	private void validateGrammage(int grammage) throws InvalidArgumentValueException {
		if (grammage < MIN_GRAMMAGE)
			throw new InvalidArgumentValueException(GRAMMAGE_MESSAGE_ERROR);
	}

	@Override
	public String toString() {
		return super.toString() + " grammage " + grammage;
	}
	
}

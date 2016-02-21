package pizzeria.menu;

import java.util.ArrayList;
import exceptions.InvalidArgumentValueException;

public abstract class Food extends Product {
	private static final int MAX_NUMBER_OF_INGREDIENTS = 10;
	private static final String GRAMMAGE_MESSAGE_ERROR = "Grammage is not correct";
	private static final int MIN_GRAMMAGE = 0;
	private int id;
	private int grammage;
	private ArrayList<Ingredient> ingredients;
	private short numberOfIngredients = 0;
	
	public Food(){
		super();
	}

	public Food(int id,double price, short quantity, String name, int grammage) throws InvalidArgumentValueException {
		super(id, price, quantity, name);
		setGrammage(grammage);
		this.ingredients = new ArrayList<Ingredient>();
	}

	public void addIngredients(Ingredient ingredient) {
		if (numberOfIngredients < MAX_NUMBER_OF_INGREDIENTS) {
			if (ingredient != null) {
				ingredients.add(ingredient);
				numberOfIngredients++;
			}
		} else {
			System.out.println("Maximum limit of ingredients is 10");
			return;
		}
	}

	public ArrayList<Ingredient> getIngredients() {
		ArrayList<Ingredient> result = new ArrayList<Ingredient>();
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
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

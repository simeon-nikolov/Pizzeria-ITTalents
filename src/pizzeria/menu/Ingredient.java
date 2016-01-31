package pizzeria.menu;

public class Ingredient {
	private static final String NAME_IS_EMPTY_ERROR_MESSAGE = "Name is empty!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	private static long count = 0;
	private long id;
	private String name;

	public Ingredient(String name) {
		setName(name);
		count++;
		this.id = count;
	}

	private void validateName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_IS_NULL_ERROR_MESSAGE);
		}

		if (name.equals("")) {
			throw new IllegalArgumentException(NAME_IS_EMPTY_ERROR_MESSAGE);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.validateName(name);
		this.name = name;
	}

}

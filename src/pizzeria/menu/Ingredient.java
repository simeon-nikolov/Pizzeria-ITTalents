package pizzeria.menu;

public class Ingredient {
	private static final String NAME_IS_EMPTY_ERROR_MESSAGE = "Name is empty!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	private int id;
	private String name;

	public Ingredient() {
		super();
	}

	public Ingredient(String name) {
		this();
		setName(name);
	}

	public Ingredient(int id, String name) {
		this(name);
		this.id = id;
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

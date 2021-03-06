package pizzeria.account;

import java.util.Set;

import pizzeria.Shop;
import pizzeria.menu.IProduct;
import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import database.OrderDb;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

public class User extends Account {
	private static final String INGREDIENTS_COUNT_ERROR_MESSAGE = "Ingredients must be no more than 10!";
	private static final String INGREDIENTS_NULL_ERROR_MESSAGE = "Ingredients are null!";
	private static final String PHONE_NUMBER_NULL_ERROR_MESSAGE = "The phone number is null!";
	private static final String ADDRESS_IS_NULL_ERROR_MESSAGE = "Address is null!";
	private static final String NAME_IS_EMPTY_ERROR_MESSAGE = "Name is empty!";
	private static final String NAME_IS_NULL_ERROR_MESSAGE = "Name is null!";
	
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private ShoppingCart shoppingCart;
	private UserDb userDao = new UserDb();
	
	public User() {
		try {
			this.shoppingCart = new ShoppingCart(this);
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
	}

	public User(int id, String username, String password, String email, String firstName,
			String lastName, String address, String phoneNumber) throws InvalidArgumentValueException {
		super(id, username, password, email);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
		this.shoppingCart = new ShoppingCart(this);
	}
	
	@Override
	public void register() {
		this.userDao.addUser(this);
	}
	
	public void updateUser(User user) {
		if (user != null) {
			this.userDao.editUser(this.getId(), user);
		}
	}
	
	public Order makeOrder(Shop shop) {
		Order order = null;
		
		try {
			order = new Order();
			order.setClient(this);
			order.setShop(shop);
			
			for (IProduct product : this.shoppingCart.getProducts()) {
				order.addProduct(product);
			}
			
			OrderDb orderDao = new OrderDb();
			orderDao.addOrder(order);
			this.shoppingCart.empty();
		} catch (InvalidArgumentValueException e) {
			System.out.println(e.getMessage());
		}
		
		return order;
	}
	
	public Pizza makePizzaByChoice(Set<Ingredient> ingredeients, String name, int size) throws InvalidArgumentValueException {
		Pizza pizza = null;
		
		if (ingredeients == null) {
			throw new InvalidArgumentValueException(INGREDIENTS_NULL_ERROR_MESSAGE);
		}
		
		if (ingredeients.size() > 10) {
			throw new InvalidArgumentValueException(INGREDIENTS_COUNT_ERROR_MESSAGE);
		}
		
		pizza = new Pizza();
		pizza.setName(name);
		pizza.setSize(size);
		
		for (Ingredient ingredient : ingredeients) {
			pizza.addIngredient(ingredient);
		}
		
		return pizza;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidArgumentValueException {
		this.validateName(firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidArgumentValueException {
		this.validateName(lastName);
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws InvalidArgumentValueException {
		this.validateAddress(address);
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidArgumentValueException {
		this.validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public boolean isAdmin() {
		return false;
	}

	private void validateName(String name) throws InvalidArgumentValueException {
		if (name == null) {
			throw new InvalidArgumentValueException(NAME_IS_NULL_ERROR_MESSAGE);
		}
		
		if (name.equals("")) {
			throw new InvalidArgumentValueException(NAME_IS_EMPTY_ERROR_MESSAGE);
		}
	}
	
	private void validateAddress(String address) throws InvalidArgumentValueException {
		if (address == null) {
			throw new InvalidArgumentValueException(ADDRESS_IS_NULL_ERROR_MESSAGE);
		}
	}
	
	private void validatePhoneNumber(String phoneNumber) throws InvalidArgumentValueException {
		if (phoneNumber == null) {
			throw new InvalidArgumentValueException(PHONE_NUMBER_NULL_ERROR_MESSAGE);
		}
	}
}

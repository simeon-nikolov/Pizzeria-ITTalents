package pizzeria.account;

public abstract class Account {
	private static final String EMAIL_IS_NULL_ERROR_MESSAGE = "Email is null!";
	private static final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password must be at least %1$1s characters long!";
	private static final String PASSWORD_IS_NULL_ERROR_MESSAGE = "Password is null!";
	private static final String USERNAME_CONTAINS_ERROR_MESSAGE = "Username can only have a..z, A..Z, 0..9 and _";
	private static final String USERNAME_STARTS_WITH_DIGIT_ERROR_MESSAGE = "Username can't start with a digit!";
	private static final String USERNAME_LENGTH_ERROR_MESSAGE = "Username should be at least %1$1s characters long!";
	private static final String USERNAME_IS_NULL_ERROR_MESSAGE = "Username is null!";
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MIN_USERNAME_LENGTH = 5;
	private static final String USERNAME_ALLOWED_CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789_";
	
	private long id;
	private String username;
	private String password;
	private String email;
	
	public Account(String username, String password, String email) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.validateUsername(username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.validatePassword(password);
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.validateEmail(email);
		this.email = email;
	}

	private void validateUsername(String username) {
		if (username == null) {
			throw new IllegalArgumentException(USERNAME_IS_NULL_ERROR_MESSAGE);
		}
		
		if (username.length() < MIN_USERNAME_LENGTH) {
			throw new IllegalArgumentException(String.format(USERNAME_LENGTH_ERROR_MESSAGE, MIN_USERNAME_LENGTH));
		}
		
		if (Character.isDigit(username.charAt(0))) {
			throw new IllegalArgumentException(USERNAME_STARTS_WITH_DIGIT_ERROR_MESSAGE);
		}
		
		for (char c : username.toCharArray()) {
			if (!USERNAME_ALLOWED_CHARS.contains(Character.toString(c))) {
				throw new IllegalArgumentException(USERNAME_CONTAINS_ERROR_MESSAGE);
			}
		}
	}
	
	private void validatePassword(String password) {
		if (password == null) {
			throw new IllegalArgumentException(PASSWORD_IS_NULL_ERROR_MESSAGE);
		}
		
		if (password.length() < MIN_PASSWORD_LENGTH) {
			throw new IllegalArgumentException(String.format(PASSWORD_LENGTH_ERROR_MESSAGE, MIN_PASSWORD_LENGTH));
		}
	}
	
	private void validateEmail(String email) {
		if (email == null) {
			throw new IllegalArgumentException(EMAIL_IS_NULL_ERROR_MESSAGE);
		}
	}
	
}

package pizzeria.account;

import java.util.Random;

import exceptions.InvalidArgumentValueException;

public abstract class Account {
	private static final String PASSWORD_MATCH_ERROR_MESSAGE = "Password doesn't match!";
	private static final String EMAIL_IS_NULL_ERROR_MESSAGE = "E-mail is null!";
	private static final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password must be at least %1$1s characters long!";
	private static final String PASSWORD_IS_NULL_ERROR_MESSAGE = "Password is null!";
	private static final String USERNAME_CONTAINS_ERROR_MESSAGE = "Username can only have a..z, A..Z, 0..9 and _";
	private static final String USERNAME_STARTS_WITH_DIGIT_ERROR_MESSAGE = "Username can't start with a digit!";
	private static final String USERNAME_MIN_LENGTH_ERROR_MESSAGE = "Username should be at least %1$1s characters long!";
	private static final String USERNAME_IS_NULL_ERROR_MESSAGE = "Username is null!";
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MIN_USERNAME_LENGTH = 5;
	private static final int MAX_USERNAME_LENGTH = 15;
	private static final String USERNAME_ALLOWED_CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789_";
	private static final int SESSION_KEY_LENGTH = 50;
	private static final String SESSION_KEY_CHARACTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	
	private long id;
	private String username;
	private String password;
	private String email;
	private String sessionKey;
	private static Random randomGenerator = new Random();

	public Account(String username, String password, String email) throws InvalidArgumentValueException {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}
	
	public void login(String password) throws InvalidArgumentValueException {
		if (!this.password.equals(password)) {
			throw new InvalidArgumentValueException(PASSWORD_MATCH_ERROR_MESSAGE);
		}
		
		this.sessionKey = this.generateSessionKey();
	}
	
	public void logout() {
		this.sessionKey = null;
	}
	
	private String generateSessionKey() {
		StringBuilder sessionKey = new StringBuilder();
		sessionKey.append(this.id);
		
		while (sessionKey.length() < SESSION_KEY_LENGTH) {
			int index = randomGenerator
					.nextInt(SESSION_KEY_CHARACTERS.length());
			sessionKey.append(SESSION_KEY_CHARACTERS.charAt(index));
		}

		return sessionKey.toString();
	}
	
	public String getSessionKey() {
		return sessionKey;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) throws InvalidArgumentValueException {
		this.validateUsername(username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws InvalidArgumentValueException {
		this.validatePassword(password);
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidArgumentValueException {
		this.validateEmail(email);
		this.email = email;
	}

	private void validateUsername(String username) throws InvalidArgumentValueException {
		if (username == null) {
			throw new InvalidArgumentValueException(USERNAME_IS_NULL_ERROR_MESSAGE);
		}
		
		if (username.length() < MIN_USERNAME_LENGTH) {
			throw new InvalidArgumentValueException(String.format(USERNAME_MIN_LENGTH_ERROR_MESSAGE, MIN_USERNAME_LENGTH));
		}
		
		if (username.length() > MAX_USERNAME_LENGTH) {
			throw new InvalidArgumentValueException(String.format("Username is too long! It must be no more than %1$1s characters long!", MAX_USERNAME_LENGTH));
		}
		
		if (Character.isDigit(username.charAt(0))) {
			throw new InvalidArgumentValueException(USERNAME_STARTS_WITH_DIGIT_ERROR_MESSAGE);
		}
		
		for (char c : username.toCharArray()) {
			if (!USERNAME_ALLOWED_CHARS.contains(Character.toString(c))) {
				throw new InvalidArgumentValueException(USERNAME_CONTAINS_ERROR_MESSAGE);
			}
		}
	}
	
	private void validatePassword(String password) throws InvalidArgumentValueException {
		if (password == null) {
			throw new InvalidArgumentValueException(PASSWORD_IS_NULL_ERROR_MESSAGE);
		}
		
		if (password.length() < MIN_PASSWORD_LENGTH) {
			throw new InvalidArgumentValueException(String.format(PASSWORD_LENGTH_ERROR_MESSAGE, MIN_PASSWORD_LENGTH));
		}
	}
	
	private void validateEmail(String email) throws InvalidArgumentValueException {
		if (email == null) {
			throw new InvalidArgumentValueException(EMAIL_IS_NULL_ERROR_MESSAGE);
		}
	}
	
}

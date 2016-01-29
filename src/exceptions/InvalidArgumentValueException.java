package exceptions;

public class InvalidArgumentValueException extends Exception {
	private static final long serialVersionUID = -248445104587756765L;

	public InvalidArgumentValueException() {
		super();
	}
	
	public InvalidArgumentValueException(String message) {
		super(message);
	}
	
	public InvalidArgumentValueException(Throwable cause) {
		super(cause);
	}
	
	public InvalidArgumentValueException(String message, Throwable cause) {
		super(message, cause);
	}
}

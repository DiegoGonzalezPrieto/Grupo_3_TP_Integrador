package exceptions;


public class PasswordIncorrectaException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordIncorrectaException() {
		super("La contraseña es incorrecta.");
	}
}

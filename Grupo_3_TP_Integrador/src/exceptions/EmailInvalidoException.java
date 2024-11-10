package exceptions;

public class EmailInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El email ingresado es inválido.";
	}

}

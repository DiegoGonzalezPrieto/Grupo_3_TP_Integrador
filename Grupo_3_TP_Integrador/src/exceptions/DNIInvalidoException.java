package exceptions;

public class DNIInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El DNI ingresado es invalido.";
	}

}

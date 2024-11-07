package exceptions;

public class DNIRepetidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El DNI ingresado ya existe.";
	}

}

package exceptions;

public class TelefonoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El teléfono ingresado es inválido.";
	}

}

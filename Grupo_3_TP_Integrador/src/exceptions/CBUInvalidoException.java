package exceptions;

public class CBUInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El CBU ingresado es inválido.";
	}

}

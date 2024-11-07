package exceptions;

public class CBURepetidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El CBU ingresado ya existe.";
	}

}

package exceptions;

public class NumeroCuentaRepetidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El número de cuenta ingresado ya existe.";
	}

}

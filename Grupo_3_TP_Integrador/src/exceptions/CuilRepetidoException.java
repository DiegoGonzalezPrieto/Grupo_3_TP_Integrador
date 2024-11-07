package exceptions;

public class CuilRepetidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El Cuil ingresado ya existe.";
	}

}

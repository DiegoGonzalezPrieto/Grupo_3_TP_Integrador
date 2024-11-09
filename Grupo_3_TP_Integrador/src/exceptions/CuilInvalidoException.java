package exceptions;

public class CuilInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "El Cuil ingresado es inválido.";
	}

}

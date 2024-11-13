package exceptions;

public class UsuarioNoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoException() {
		super("El usuario no existe.");
	}
}

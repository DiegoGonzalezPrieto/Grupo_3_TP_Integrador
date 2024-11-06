package negocio;

import java.util.ArrayList;

import dominio.TipoUsuario;

public interface TipoUsuarioNegocio {

	public TipoUsuario buscarPorId(int id);

	public ArrayList<TipoUsuario> buscarTodos();

}

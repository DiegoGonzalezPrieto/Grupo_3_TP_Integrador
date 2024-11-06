package negocioImpl;

import java.util.ArrayList;

import daoImpl.TipoUsuarioDaoImpl;
import dominio.TipoUsuario;
import negocio.TipoUsuarioNegocio;

public class TipoUsuarioNegocioImpl implements TipoUsuarioNegocio {

	@Override
	public TipoUsuario buscarPorId(int id) {
		TipoUsuarioDaoImpl dao = new TipoUsuarioDaoImpl();
		return dao.buscarPorId(id);
	}

	@Override
	public ArrayList<TipoUsuario> buscarTodos() {
		TipoUsuarioDaoImpl dao = new TipoUsuarioDaoImpl();
		return dao.buscarTodos();
	}

}

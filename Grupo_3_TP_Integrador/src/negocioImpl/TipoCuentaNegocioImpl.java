package negocioImpl;

import java.util.ArrayList;

import daoImpl.TipoCuentaDaoImpl;
import dominio.TipoCuenta;
import negocio.TipoCuentaNegocio;

public class TipoCuentaNegocioImpl implements TipoCuentaNegocio {

	@Override
	public TipoCuenta buscarPorId(int id) {
		TipoCuentaDaoImpl dao = new TipoCuentaDaoImpl();
		return dao.buscarPorId(id);
	}

	@Override
	public ArrayList<TipoCuenta> buscarTodos() {
		TipoCuentaDaoImpl dao = new TipoCuentaDaoImpl();
		return dao.buscarTodos();
	}

}

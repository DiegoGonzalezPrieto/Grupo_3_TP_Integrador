package negocioImpl;

import java.util.ArrayList;

import daoImpl.LocalidadDaoImpl;
import dominio.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	@Override
	public Localidad buscarPorId(int id) {
		LocalidadDaoImpl dao = new LocalidadDaoImpl();
		return dao.buscarPorId(id);
	}

	@Override
	public ArrayList<Localidad> buscarTodos() {
		LocalidadDaoImpl dao = new LocalidadDaoImpl();
		return dao.buscarTodos();
	}

}

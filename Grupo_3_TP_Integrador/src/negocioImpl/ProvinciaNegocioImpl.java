package negocioImpl;

import java.util.ArrayList;

import daoImpl.ProvinciaDaoImpl;
import dominio.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

	@Override
	public Provincia buscarPorId(int id) {
		ProvinciaDaoImpl dao = new ProvinciaDaoImpl();
		return dao.buscarPorId(id);
	}

	@Override
	public ArrayList<Provincia> buscarTodos() {
		ProvinciaDaoImpl dao = new ProvinciaDaoImpl();
		return dao.buscarTodos();
	}

}

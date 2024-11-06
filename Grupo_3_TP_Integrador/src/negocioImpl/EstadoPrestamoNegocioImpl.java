package negocioImpl;

import java.util.ArrayList;

import daoImpl.EstadoPrestamoDaoImpl;
import dominio.EstadoPrestamo;
import negocio.EstadoPrestamoNegocio;

public class EstadoPrestamoNegocioImpl implements EstadoPrestamoNegocio {

	@Override
	public EstadoPrestamo buscarPorId(int id) {
		EstadoPrestamoDaoImpl dao = new EstadoPrestamoDaoImpl();
		return dao.buscarPorId(id);
	}

	@Override
	public ArrayList<EstadoPrestamo> buscarTodos() {
		EstadoPrestamoDaoImpl dao = new EstadoPrestamoDaoImpl();
		return dao.buscarTodos();
	}

}

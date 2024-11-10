package negocioImpl;

import java.util.ArrayList;

import daoImpl.MovimientoDaoImpl;
import dominio.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	private MovimientoDaoImpl dao = new MovimientoDaoImpl();
	@Override
	public void insert(Movimiento m) {
		dao.insert(m);
		
	}

	@Override
	public void update(Movimiento m) {
		dao.update(m);
		
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
		
	}

	@Override
	public Movimiento encontrarPorId(int id) {
		//return dao.encontrarPorId(id)
		return null;
	}

	@Override
	public ArrayList<Movimiento> ListarMovimientosPorCuenta(int idCuenta) {
		
		return dao.ListarMovimientosPorCuenta(idCuenta);
	}

	@Override
	public ArrayList<Movimiento> ListarTodo() {
		
		return dao.ListarTodo();
	}

}

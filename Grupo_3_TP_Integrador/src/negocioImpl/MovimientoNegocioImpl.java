package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

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

	// @Override
	// public void delete(int id) {
	// dao.delete(id);
	//
	// }

	@Override
	public Movimiento encontrarPorId(int id) {
		return dao.encontrarPorId(id);
	}

	@Override
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta) {

		return dao.listarMovimientosPorCuenta(idCuenta);
	}

	@Override
	public ArrayList<Movimiento> listarTodo() {

		return dao.listarTodos();
	}

	@Override
	public int obtenerCantidadMovimientos(Date fechaInicio, Date fechaFin) {
		return dao.obtenerCantidadMovimientos(fechaInicio, fechaFin);
	}

	@Override
	public int obtenerCantidadAltasCuenta(Date fechaInicio, Date fechaFin) {
		return dao.obtenerCantidadAltasCuenta(fechaInicio, fechaFin);
	}

	@Override
	public int obtenerCantidadTransferencias(Date fechaInicio, Date fechaFin) {
		return dao.obtenerCantidadTransferencias(fechaInicio, fechaFin);
	}

	@Override
	public int obtenerCantidadAltasPrestamo(Date fechaInicio, Date fechaFin) {
		return dao.obtenerCantidadAltasPrestamo(fechaInicio, fechaFin);
	}

	@Override
	public int obtenerCantidadPagosPrestamo(Date fechaInicio, Date fechaFin) {
		return dao.obtenerCantidadPagosPrestamo(fechaInicio, fechaFin);
	}

}

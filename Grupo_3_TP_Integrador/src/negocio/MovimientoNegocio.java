package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import dominio.Movimiento;

public interface MovimientoNegocio {
	public void insert(Movimiento m);

	public void update(Movimiento m);
	// public void delete(int id); no haría falta eliminar movimientos

	public Movimiento encontrarPorId(int id);

	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta);

	public ArrayList<Movimiento> listarTodo();

	// REPORTES

	public int obtenerCantidadMovimientos(Date fechaInicio, Date fechaFin);

	public int obtenerCantidadAltasCuenta(Date fechaInicio, Date fechaFin);

	public int obtenerCantidadTransferencias(Date fechaInicio, Date fechaFin);

	public int obtenerCantidadAltasPrestamo(Date fechaInicio, Date fechaFin);

	public int obtenerCantidadPagosPrestamo(Date fechaInicio, Date fechaFin);

	public BigDecimal obtenerSumaTransferencias(Date fechaInicio, Date fechaFin);

	public BigDecimal obtenerPromedioTransferencias(Date fechaInicio, Date fechaFin);
}

package negocio;

import java.util.ArrayList;

import dominio.Movimiento;

public interface MovimientoNegocio {
	public void insert(Movimiento m);
	public void update(Movimiento m);
//	public void delete(int id); no haría falta eliminar movimientos
	
	public Movimiento encontrarPorId(int id);
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta);
	public ArrayList<Movimiento> listarTodo();
}

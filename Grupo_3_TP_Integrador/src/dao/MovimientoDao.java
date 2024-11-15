package dao;

import java.util.ArrayList;


import dominio.Movimiento;

public interface MovimientoDao {
	public void insert(Movimiento m);
	public void update(Movimiento m);
//	public void delete(int id); no tiene baja
	
	public Movimiento encontrarPorId(int id);
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta);
	public ArrayList<Movimiento> listarTodos();
	
}

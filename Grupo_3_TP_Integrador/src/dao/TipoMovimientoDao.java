package dao;

import java.util.ArrayList;

import dominio.TipoMovimiento;

public interface TipoMovimientoDao {

	public TipoMovimiento buscarPorId(int id);

	public ArrayList<TipoMovimiento> buscarTodos();

}

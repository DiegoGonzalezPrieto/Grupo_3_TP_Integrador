package negocio;

import java.util.ArrayList;

import dominio.TipoMovimiento;

public interface TipoMovimientoNegocio {

	public TipoMovimiento buscarPorId(int id);

	public ArrayList<TipoMovimiento> buscarTodos();

}

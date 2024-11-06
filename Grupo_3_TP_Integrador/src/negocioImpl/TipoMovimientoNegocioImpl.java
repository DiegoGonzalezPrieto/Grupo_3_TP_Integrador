package negocioImpl;

import java.util.ArrayList;

import daoImpl.TipoMovimientoDaoImpl;
import dominio.TipoMovimiento;
import negocio.TipoMovimientoNegocio;

public class TipoMovimientoNegocioImpl implements TipoMovimientoNegocio {

	@Override
	public TipoMovimiento buscarPorId(int id) {
		TipoMovimientoDaoImpl dao = new TipoMovimientoDaoImpl();
		return dao.buscarPorId(id);
	}

	@Override
	public ArrayList<TipoMovimiento> buscarTodos() {
		TipoMovimientoDaoImpl dao = new TipoMovimientoDaoImpl();
		return dao.buscarTodos();
	}

}

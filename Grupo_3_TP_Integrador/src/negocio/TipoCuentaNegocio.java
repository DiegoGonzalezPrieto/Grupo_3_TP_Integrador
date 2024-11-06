package negocio;

import java.util.ArrayList;

import dominio.TipoCuenta;

public interface TipoCuentaNegocio {

	public TipoCuenta buscarPorId(int id);

	public ArrayList<TipoCuenta> buscarTodos();

}

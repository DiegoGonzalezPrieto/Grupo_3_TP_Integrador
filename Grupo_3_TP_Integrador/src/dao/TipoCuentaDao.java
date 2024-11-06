package dao;

import java.util.ArrayList;

import dominio.TipoCuenta;

public interface TipoCuentaDao {

	public TipoCuenta buscarPorId(int id);

	public ArrayList<TipoCuenta> buscarTodos();

}

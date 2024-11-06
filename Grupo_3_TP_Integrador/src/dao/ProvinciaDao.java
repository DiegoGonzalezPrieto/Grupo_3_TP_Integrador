package dao;

import java.util.ArrayList;

import dominio.Provincia;

public interface ProvinciaDao {

	public Provincia buscarPorId(int id);

	public ArrayList<Provincia> buscarTodos();

}

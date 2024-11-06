package dao;

import java.util.ArrayList;

import dominio.Localidad;

public interface LocalidadDao {

	public Localidad buscarPorId(int id);

	public ArrayList<Localidad> buscarTodos();

}

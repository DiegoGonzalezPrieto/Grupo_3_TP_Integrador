package negocio;

import java.util.ArrayList;

import dominio.Localidad;

public interface LocalidadNegocio {

	public Localidad buscarPorId(int id);

	public ArrayList<Localidad> buscarTodos();

}

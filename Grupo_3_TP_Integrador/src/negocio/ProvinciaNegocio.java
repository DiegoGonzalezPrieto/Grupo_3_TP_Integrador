package negocio;

import java.util.ArrayList;

import dominio.Provincia;

public interface ProvinciaNegocio {

	public Provincia buscarPorId(int id);

	public ArrayList<Provincia> buscarTodos();

}

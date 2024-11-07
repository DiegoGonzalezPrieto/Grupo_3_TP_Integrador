package dao;

import java.util.ArrayList;
import dominio.Nacionalidad;

public interface NacionalidadDao {
	
	Nacionalidad buscarPorId(int id);
	ArrayList<Nacionalidad> buscarTodos();

}

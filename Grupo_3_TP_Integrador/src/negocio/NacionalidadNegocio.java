package negocio;

import java.util.ArrayList;
import dominio.Nacionalidad;

public interface NacionalidadNegocio {
	Nacionalidad buscarPorId(int id);
	ArrayList<Nacionalidad> buscarTodos();
}



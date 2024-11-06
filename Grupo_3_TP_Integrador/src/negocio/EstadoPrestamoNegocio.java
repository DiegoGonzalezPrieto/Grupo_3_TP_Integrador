package negocio;

import java.util.ArrayList;

import dominio.EstadoPrestamo;

public interface EstadoPrestamoNegocio {

	public EstadoPrestamo buscarPorId(int id);

	public ArrayList<EstadoPrestamo> buscarTodos();

}

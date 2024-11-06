package dao;

import java.util.ArrayList;

import dominio.EstadoPrestamo;

public interface EstadoPrestamoDao {

	public EstadoPrestamo buscarPorId(int id);

	public ArrayList<EstadoPrestamo> buscarTodos();

}

package dao;

import java.util.ArrayList;

import dominio.Cliente;

public interface ClienteDao {
	
	public boolean insert(Cliente c);
	public boolean update(int id, Cliente c);
	public boolean delete(int id);
	
	public Cliente encontrarPorId(int id);
	public Cliente encontrarPorNombre(String nombre);
	
	public ArrayList<Cliente> buscarTodos();
	public ArrayList<Cliente> buscarTodosActivos();

}

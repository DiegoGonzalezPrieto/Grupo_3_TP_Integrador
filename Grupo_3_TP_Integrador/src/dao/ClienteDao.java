package dao;

import java.util.ArrayList;
import java.util.HashMap;

import dominio.Cliente;

public interface ClienteDao {
	
	public boolean insert(Cliente c);
	public boolean update(int id, Cliente c);
	public boolean delete(int id);
	
	public Cliente encontrarPorId(int id);
	public int encontrarPorIdUsuario(int id);
	public Cliente encontrarPorNombre(String nombre);
	
	public ArrayList<Cliente> buscarTodos();
	public ArrayList<Cliente> buscarTodosActivos();
	
	public boolean existeDNI(String dni);
	public boolean existeCUIL(String cuil);

	// PARA REPORTES
	
	public int contarTodos();
	public int contarActivos();
	public int contarInactivos();
	public float obtenerEdadPromedioActivos();
	public HashMap<String, Integer> obtenerClientesPorProvincia();
	public HashMap<String, Integer> obtenerClientesPorNacionalidad();
}

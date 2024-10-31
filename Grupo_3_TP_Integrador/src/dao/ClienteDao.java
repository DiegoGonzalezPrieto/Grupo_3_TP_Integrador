package dao;

import dominio.Cliente;

public interface ClienteDao {
	
	public boolean insert(Cliente c);
	public boolean update(int id, Cliente c);
	public boolean delete(int id);
	
	public ClienteDao encontrarPorId(int id);
	public ClienteDao encontrarPorNombre(String nombre);

}

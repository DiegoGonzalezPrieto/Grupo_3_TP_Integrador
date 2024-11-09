package negocioImpl;

import java.util.ArrayList;

import daoImpl.ClienteDaoImpl;
import dominio.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

	private ClienteDaoImpl clienteDao = new ClienteDaoImpl();
	
	@Override
	public void insert(Cliente c) {
		clienteDao.insert(c);
		
	}

	@Override
	public void update(Cliente c) {
		clienteDao.update(c.getId(), c);
	}

	@Override
	public void delete(int id) {
		clienteDao.delete(id);
		
	}

	@Override
	public Cliente buscarPorId(int id) {
		return clienteDao.encontrarPorId(id);
		
	}

	@Override
	public ArrayList<Cliente> listar() {
		return clienteDao.buscarTodos();
		
	}

	@Override
	public Cliente buscarPorNombre(String nombre) {
		return clienteDao.encontrarPorNombre(nombre);
	}



}


package negocioImpl;

import java.util.ArrayList;
import java.util.HashMap;

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
		clienteDao.update(c.getIdCliente(), c);
	}

	@Override
	public void delete(int id) {
		clienteDao.delete(id);

	}

	@Override
	public Cliente buscarPorId(int id) {
		return clienteDao.encontrarPorId(id);

	}

	/**
	 * Busca todos los clientes (activos e inactivos).
	 */
	@Override
	public ArrayList<Cliente> listarTodos() {
		return clienteDao.buscarTodos();
	}

	/**
	 * Busca solo los clientes activos.
	 */
	@Override
	public ArrayList<Cliente> listarActivos() {
		return clienteDao.buscarTodosActivos();
	}

	@Override
	public Cliente buscarPorNombre(String nombre) {
		return clienteDao.encontrarPorNombre(nombre);
	}
	
	public int contarClientesActivos() {
		return clienteDao.buscarTodosActivos().size();
	}

	@Override
	public int contarTodos() {
		return clienteDao.contarTodos();
	}

	@Override
	public int contarActivos() {
		return clienteDao.contarActivos();
	}

	@Override
	public int contarInactivos() {
		return clienteDao.contarInactivos();

	}

	@Override
	public float obtenerEdadPromedioActivos() {
		return clienteDao.obtenerEdadPromedioActivos();
	}

	@Override
	public HashMap<String, Integer> obtenerClientesPorProvincia() {
		return clienteDao.obtenerClientesPorProvincia();
	}

	@Override
	public HashMap<String, Integer> obtenerClientesPorNacionalidad() {
		return clienteDao.obtenerClientesPorNacionalidad();
	}

}

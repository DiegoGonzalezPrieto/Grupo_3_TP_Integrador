package negocio;

import java.util.ArrayList;
import java.util.HashMap;

import dominio.Cliente;

public interface ClienteNegocio {

	void insert(Cliente c);

	void update(Cliente c);

	void delete(int id);

	Cliente buscarPorNombre(String nombre);

	Cliente buscarPorId(int id);

	int buscarPorIdUsuario(int id);

	ArrayList<Cliente> listarTodos();

	ArrayList<Cliente> listarActivos();

	int contarTodos();

	int contarActivos();

	int contarInactivos();

	float obtenerEdadPromedioActivos();

	HashMap<String, Integer> obtenerClientesPorProvincia();

	HashMap<String, Integer> obtenerClientesPorNacionalidad();

	public boolean existeDNI(String DNI);

	public boolean existeCUIL(String CUIL);

	public boolean cantCoutasImpagas(int id);
}

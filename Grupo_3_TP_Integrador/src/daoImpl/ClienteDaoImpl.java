package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.ClienteDao;
import dominio.Cliente;
import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;
import dominio.Usuario;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

public class ClienteDaoImpl implements ClienteDao {

	private int id;
	private String nombre;

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean insert(Cliente c) {
		String insert = "INSERT INTO clientes (id_usuario, dni, cuil, nombre, apellido, email, telefono, "
				+ "genero, id_nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(insert)) {
			statement.setInt(1, c.getId());
			statement.setString(2, c.getDni());
			statement.setString(3, c.getCuil());
			statement.setString(4, c.getNombre());
			statement.setString(5, c.getApellido());
			statement.setString(6, c.getCorreoElectronico());
			statement.setString(7, c.getTelefono());
			statement.setString(8, c.getGenero());
			statement.setInt(9, c.getNacionalidad().getId());
			statement.setDate(10, java.sql.Date.valueOf(c.getFechaNacimiento().toLocalDate()));
			statement.setString(11, c.getDireccion());
			statement.setInt(12, c.getLocalidad().getId());
			statement.setInt(13, c.getProvincia().getId());

			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(int id, Cliente c) {
		String updateCliente = "UPDATE clientes SET dni = ?, cuil = ?, nombre = ?, apellido = ?, email = ?, "
				+ "telefono = ?, genero = ?, id_nacionalidad = ?, fecha_nacimiento = ?, direccion = ?, "
				+ "id_localidad = ?, id_provincia = ? WHERE id_cliente = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(updateCliente)) {
			statement.setString(1, c.getDni());
			statement.setString(2, c.getCuil());
			statement.setString(3, c.getNombre());
			statement.setString(4, c.getApellido());
			statement.setString(5, c.getCorreoElectronico());
			statement.setString(6, c.getTelefono());
			statement.setString(7, c.getGenero());
			statement.setInt(8, c.getNacionalidad().getId());
			statement.setDate(9, new Date(c.getFechaNacimiento().getTime()));
			statement.setString(10, c.getDireccion());
			statement.setInt(11, c.getLocalidad().getId());
			statement.setInt(12, c.getProvincia().getId());
			statement.setInt(13, id);
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		UsuarioDaoImpl usuDao = new UsuarioDaoImpl();
		Cliente cliente = new Cliente();
		cliente = encontrarPorId(id);
		usuDao.eliminarUsuario(cliente.getId());
		return false;
	}

	@Override
	public Cliente encontrarPorId(int id) {
		String selectCliente = "Select "
				+ "	C.id_cliente, C.id_usuario, C.id_nacionalidad, C.id_localidad, C.id_provincia,  " + "    C.dni, "
				+ "    C.cuil, " + "    C.nombre, " + "    C.apellido, " + "    C.email, " + "    C.telefono, "
				+ "    C.genero, " + "    C.fecha_nacimiento," + "    C.direccion from clientes C"
				+ "	 Where C.id_cliente = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectCliente)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Usuario usuario = new UsuarioNegocioImpl().buscarPorId(resultSet.getInt("id_usuario"));
				Cliente cliente = new Cliente(usuario.getId(), usuario.getNombreUsuario(), usuario.getPass(),
						usuario.getTipoUsuario(), usuario.activo());
				cliente.setIdCliente(resultSet.getInt("id_cliente"));
				cliente.setDni(resultSet.getString("dni"));
				cliente.setCuil(resultSet.getString("cuil"));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setApellido(resultSet.getString("apellido"));
				cliente.setCorreoElectronico(resultSet.getString("email"));
				cliente.setTelefono(resultSet.getString("telefono"));
				cliente.setGenero(resultSet.getString("genero"));
				cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
				cliente.setDireccion(resultSet.getString("direccion"));

				Nacionalidad nacionalidad = new NacionalidadNegocioImpl()
						.buscarPorId(resultSet.getInt("id_nacionalidad"));
				cliente.setNacionalidad(nacionalidad);

				Provincia provincia = new ProvinciaNegocioImpl().buscarPorId(resultSet.getInt("id_provincia"));
				cliente.setProvincia(provincia);

				Localidad localidad = new LocalidadNegocioImpl().buscarPorId(resultSet.getInt("id_localidad"));
				cliente.setLocalidad(localidad);
				return cliente;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int encontrarPorIdUsuario(int idUsuario) {
		String query = "SELECT id_cliente FROM clientes WHERE id_usuario = ?";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			statement.setInt(1, idUsuario);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getInt("id_cliente");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0; // o lanzar excepción
	}

	@Override
	public Cliente encontrarPorNombre(String nombre) {

		String selectCliente = "Select C.id_cliente, C.id_usuario,  C.dni , C.cuil,"
				+ "    C.nombre,  C.apellido ,    C.email , C.telefono,    C.genero ,  C.fecha_nacimiento ,"
				+ "    C.direccion, C.id_nacionalidad, C.id_provincia, C.id_localidad "
				+ " from clientes C  Where C.nombre = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectCliente)) {

			statement.setString(1, nombre);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Usuario usuario = new UsuarioNegocioImpl().buscarPorId(resultSet.getInt("id_usuario"));
				Cliente cliente = new Cliente(usuario.getId(), usuario.getNombreUsuario(), usuario.getPass(),
						usuario.getTipoUsuario(), usuario.activo());
				cliente.setIdCliente(resultSet.getInt("id_Cliente"));
				cliente.setDni(resultSet.getString("dni"));
				cliente.setCuil(resultSet.getString("cuil"));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setApellido(resultSet.getString("apellido"));
				cliente.setCorreoElectronico(resultSet.getString("email"));
				cliente.setTelefono(resultSet.getString("telefono"));
				cliente.setGenero(resultSet.getString("genero"));
				cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
				cliente.setDireccion(resultSet.getString("direccion"));

				Nacionalidad nacionalidad = new NacionalidadNegocioImpl()
						.buscarPorId(resultSet.getInt("id_nacionalidad"));
				cliente.setNacionalidad(nacionalidad);

				Provincia provincia = new ProvinciaNegocioImpl().buscarPorId(resultSet.getInt("id_provincia"));
				cliente.setProvincia(provincia);

				Localidad localidad = new LocalidadNegocioImpl().buscarPorId(resultSet.getInt("id_localidad"));
				cliente.setLocalidad(localidad);

				return cliente;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Cliente> buscarTodos() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "Select	 C.id_cliente as id_cliente, C.id_usuario as id_usuario,   C.dni as dni,   C.cuil as cuil,    C.nombre as nombre,"
				+ "    C.apellido as apellido ,    C.email as email,    C.telefono as telefono ,     C.genero as genero ,"
				+ "    C.fecha_nacimiento as fecha_nacimiento ,     C.direccion as direccion , 	 C.id_localidad as id_localidad,     C.id_nacionalidad as id_nacionalidad,"
				+ "    C.id_provincia as id_provincia     from clientes C";

		ArrayList<Cliente> listado = new ArrayList<>();
		ArrayList<Nacionalidad> naciones = new NacionalidadDaoImpl().buscarTodos();
		ArrayList<Localidad> localidades = new LocalidadDaoImpl().buscarTodos();
		ArrayList<Provincia> provincias = new ProvinciaDaoImpl().buscarTodos();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Usuario usuario = new UsuarioNegocioImpl().buscarPorId(result.getInt("id_usuario"));
				Cliente cliente = new Cliente(result.getInt("id_cliente"), usuario.getNombreUsuario(),
						usuario.getPass(), usuario.getTipoUsuario(), usuario.activo());

				Nacionalidad n = null;
				Provincia p = null;
				Localidad l = null;
				for (Nacionalidad nacion : naciones) {
					if (nacion.getId() == result.getInt("id_nacionalidad")) {
						n = nacion;
						break;
					}
				}
				for (Provincia provincia : provincias) {
					if (provincia.getId() == result.getInt("id_provincia")) {
						p = provincia;
						break;
					}
				}
				for (Localidad localidad : localidades) {
					if (localidad.getId() == result.getInt("id_localidad")) {
						l = localidad;
						break;
					}
				}

				cliente.setIdCliente(result.getInt("id_cliente"));
				cliente.setDni(result.getString("dni"));
				cliente.setCuil(result.getString("cuil"));
				cliente.setNombre(result.getString("nombre"));
				cliente.setApellido(result.getString("apellido"));
				cliente.setCorreoElectronico(result.getString("email"));
				cliente.setTelefono(result.getString("telefono"));
				cliente.setGenero(result.getString("genero"));
				cliente.setFechaNacimiento(result.getDate("fecha_nacimiento"));
				cliente.setDireccion(result.getString("direccion"));
				cliente.setNacionalidad(n);
				cliente.setLocalidad(l);
				cliente.setProvincia(p);
				listado.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listado;
	}

	@Override
	public ArrayList<Cliente> buscarTodosActivos() {
		ArrayList<Cliente> todos = buscarTodos();
		todos.removeIf(c -> !c.activo());
		return todos;
	}

	@Override
	public boolean existeDNI(String dni) {
		String buscarDNI = "SELECT COUNT(*) FROM clientes WHERE dni = ?";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(buscarDNI)) {
			statement.setString(1, dni);

			ResultSet rs = statement.executeQuery();

			return rs.next() && rs.getInt(1) > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean existeCUIL(String cuil) {
		String buscarDNI = "SELECT COUNT(*) FROM clientes WHERE cuil = ?";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(buscarDNI)) {
			statement.setString(1, cuil);

			ResultSet rs = statement.executeQuery();

			return rs.next() && rs.getInt(1) > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean existeCuotasPendientes(int id) {
		String contarCuotas = "SELECT count(*) as totalCuotasImpagas FROM cuotas as c INNER JOIN prestamos as p ON c.id_prestamo = p.id_prestamo INNER JOIN clientes as cl\r\n"
				+ "ON p.id_cliente = cl.id_cliente WHERE p.id_estado_prestamo = 2 and c.estado_pago = 0 and cl.id_cliente = ?";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(contarCuotas)) {
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			return rs.next() && rs.getInt(1) == 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int contarTodos() {
		String selectTodos = "SELECT COUNT(*) as cantidad FROM banco.clientes;";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectTodos)) {

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("cantidad");
			} else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int contarActivos() {
		String selectActivos = "SELECT COUNT(*) as cantidad FROM banco.clientes"
				+ " JOIN banco.usuarios ON usuarios.id_usuario = clientes.id_usuario "
				+ " WHERE usuarios.estado_usuario = true;";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectActivos)) {

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("cantidad");
			} else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int contarInactivos() {
		String selectInactivos = "SELECT COUNT(*) as cantidad FROM banco.clientes"
				+ " JOIN banco.usuarios ON usuarios.id_usuario = clientes.id_usuario "
				+ " WHERE usuarios.estado_usuario = false;";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectInactivos)) {

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("cantidad");
			} else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public float obtenerEdadPromedioActivos() {
		String selectEdadPromedioActivos = "SELECT AVG((TO_DAYS(NOW())-TO_DAYS(fecha_nacimiento)))/365.242199 as edad_promedio "
				+ " FROM clientes JOIN banco.usuarios ON usuarios.id_usuario = clientes.id_usuario "
				+ " WHERE usuarios.estado_usuario = true;";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectEdadPromedioActivos)) {

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getFloat("edad_promedio");
			} else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public HashMap<String, Integer> obtenerClientesPorProvincia() {
		String selectClientesPorProvinciaActivos = "SELECT provincias.id_provincia, provincia, COUNT(clientes.id_usuario) as cantidad FROM banco.clientes "
				+ " JOIN provincias on clientes.id_provincia = provincias.id_provincia JOIN usuarios on clientes.id_usuario = usuarios.id_usuario "
				+ " WHERE usuarios.estado_usuario = true GROUP BY id_provincia, provincia ORDER BY COUNT(id_usuario) ASC;";

		HashMap<String, Integer> resultado = new HashMap<String, Integer>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return resultado;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectClientesPorProvinciaActivos)) {

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String provincia = resultSet.getString("provincia");
				int cantidad = resultSet.getInt("cantidad");
				resultado.put(provincia, cantidad);
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return resultado;
		}
	}

	@Override
	public HashMap<String, Integer> obtenerClientesPorNacionalidad() {
		String selectClientesPorNacionalidadActivos = "SELECT nacionalidades.id_nacionalidad, nacionalidad, COUNT(clientes.id_usuario) as cantidad "
				+ " FROM banco.clientes  JOIN nacionalidades on clientes.id_nacionalidad = nacionalidades.id_nacionalidad "
				+ " JOIN usuarios on clientes.id_usuario = usuarios.id_usuario "
				+ " WHERE usuarios.estado_usuario = true " + " GROUP BY id_nacionalidad, nacionalidad "
				+ " ORDER BY COUNT(id_usuario) DESC;";

		HashMap<String, Integer> resultado = new HashMap<String, Integer>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return resultado;
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectClientesPorNacionalidadActivos)) {

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String nacionalidad = resultSet.getString("nacionalidad");
				int cantidad = resultSet.getInt("cantidad");
				resultado.put(nacionalidad, cantidad);
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return resultado;
		}
	}

}

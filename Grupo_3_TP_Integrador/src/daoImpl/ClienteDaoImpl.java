package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ClienteDao;
import dao.UsuarioDao;
import dominio.Cliente;

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
		String insert = "INSERT INTO clientes (idCliente, dni, cuil, nombre, apellido, correoElectronico, telefono, "
                + "genero, nacionalidad, fechaNacimiento, direccion, localidad, provincia, tipoUsuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(insert)) {
			statement.setInt(1, c.getIdCliente());
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
	        statement.setInt(14, c.getTipoUsuario().getId());
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(int id, Cliente c) {
		String updateCliente = "UPDATE clientes SET dni = ?, cuil = ?, nombre = ?, apellido = ?, correoElectronico = ?, "
                + "telefono = ?, genero = ?, nacionalidad = ?, fechaNacimiento = ?, direccion = ?, "
                + "localidad = ?, provincia = ? WHERE idCliente = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
			statement.setString(8, c.getNacionalidad().toString()); 
			//statement.setDate(9, new java.sql.Date(c.getFechaNacimiento().getTime())); 
			statement.setString(10, c.getDireccion());
			statement.setString(11, c.getLocalidad().toString());  
			statement.setString(12, c.getProvincia().toString());  
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
		
		return false;
	}

	@Override
	public ClienteDaoImpl encontrarPorId(int id) {
		
		return null;
	}

	@Override
	public ClienteDaoImpl encontrarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}

package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ClienteDao;
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
		
		String insert = "INSERT INTO clientes (nombre) VALUES "
				+ " (?);";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(insert)) {

			statement.setString(1, c.getNombre());

			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(int id, Cliente c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClienteDaoImpl encontrarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDaoImpl encontrarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}

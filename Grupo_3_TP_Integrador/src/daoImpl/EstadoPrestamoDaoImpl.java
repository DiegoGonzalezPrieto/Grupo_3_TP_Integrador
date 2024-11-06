package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.EstadoPrestamoDao;
import dominio.EstadoPrestamo;

public class EstadoPrestamoDaoImpl implements EstadoPrestamoDao {

	@Override
	public EstadoPrestamo buscarPorId(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_estado_prestamo, estado_prestamo FROM estados_prestamo "
				+ "WHERE id_estado_prestamo = ?;";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				return new EstadoPrestamo(result.getInt("id_estado_prestamo"), result.getString("estado_prestamo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<EstadoPrestamo> buscarTodos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_estado_prestamo, estado_prestamo FROM estados_prestamo;";
		ArrayList<EstadoPrestamo> resultado = new ArrayList<EstadoPrestamo>();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				EstadoPrestamo estado = new EstadoPrestamo(result.getInt("id_estado_prestamo"),
						result.getString("estado_prestamo"));
				resultado.add(estado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}

}

package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TipoMovimientoDao;
import dominio.TipoMovimiento;

public class TipoMovimientoDaoImpl implements TipoMovimientoDao {

	@Override
	public TipoMovimiento buscarPorId(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_tipo_movimiento, tipo_movimiento FROM tipos_movimiento "
				+ "WHERE id_tipo_movimiento = ?;";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				return new TipoMovimiento(result.getInt("id_tipo_movimiento"), result.getString("tipo_movimiento"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<TipoMovimiento> buscarTodos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_tipo_movimiento, tipo_movimiento FROM tipos_movimiento;";
		ArrayList<TipoMovimiento> resultado = new ArrayList<TipoMovimiento>();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TipoMovimiento tipo = new TipoMovimiento(result.getInt("id_tipo_movimiento"),
						result.getString("tipo_movimiento"));
				resultado.add(tipo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}

}

package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TipoCuentaDao;
import dominio.TipoCuenta;

public class TipoCuentaDaoImpl implements TipoCuentaDao {

	@Override
	public TipoCuenta buscarPorId(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_tipo_cuenta, tipo_cuenta FROM tipos_cuenta " + "WHERE id_tipo_cuenta = ?;";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				return new TipoCuenta(result.getInt("id_tipo_cuenta"), result.getString("tipo_cuenta"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<TipoCuenta> buscarTodos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_tipo_cuenta, tipo_cuenta FROM tipos_cuenta;";
		ArrayList<TipoCuenta> resultado = new ArrayList<TipoCuenta>();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TipoCuenta tipo = new TipoCuenta(result.getInt("id_tipo_cuenta"), result.getString("tipo_cuenta"));
				resultado.add(tipo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}

}

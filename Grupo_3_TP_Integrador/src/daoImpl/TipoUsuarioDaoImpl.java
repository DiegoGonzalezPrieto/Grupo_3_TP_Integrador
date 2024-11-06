package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TipoUsuarioDao;
import dominio.TipoUsuario;

public class TipoUsuarioDaoImpl implements TipoUsuarioDao {

	@Override
	public TipoUsuario buscarPorId(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_tipo_usuario, tipo_usuario FROM tipos_usuario WHERE id_tipo_usuario = ?;";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				return new TipoUsuario(result.getInt("id_tipo_usuario"), result.getString("tipo_usuario"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<TipoUsuario> buscarTodos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_tipo_usuario, tipo_usuario FROM tipos_usuario;";
		ArrayList<TipoUsuario> resultado = new ArrayList<TipoUsuario>();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TipoUsuario tipo = new TipoUsuario(result.getInt("id_tipo_usuario"), result.getString("tipo_usuario"));
				resultado.add(tipo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}

}

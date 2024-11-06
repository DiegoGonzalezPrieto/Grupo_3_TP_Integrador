package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.LocalidadDao;
import dominio.Localidad;
import dominio.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {

	@Override
	public Localidad buscarPorId(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_localidad, localidad, id_provincia FROM localidades " + "WHERE id_localidad = ?;";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				Provincia prov = new ProvinciaDaoImpl().buscarPorId(result.getInt("id_provincia"));
				return new Localidad(result.getInt("id_localidad"), result.getString("localidad"), prov);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ArrayList<Localidad> buscarTodos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String select = "SELECT id_localidad, localidad, id_provincia FROM localidades;";

		ArrayList<Localidad> resultado = new ArrayList<Localidad>();
		ArrayList<Provincia> provincias = new ProvinciaDaoImpl().buscarTodos();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Provincia prov = new Provincia(0, "Provincia");
				for (Provincia provincia : provincias) {
					if (provincia.getId() == result.getInt("id_provincia")) {
						prov = provincia;
						break;
					}
				}
				Localidad localidad = new Localidad(result.getInt("id_Localidad"), result.getString("Localidad"), prov);
				resultado.add(localidad);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}

}

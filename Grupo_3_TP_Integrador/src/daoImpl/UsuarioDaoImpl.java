package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.UsuarioDao;
import dominio.TipoUsuario;
import dominio.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public Usuario buscarPorId(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String select = "SELECT id_usuario, nombre_usuario, pass, tipo_usuario, estado_usuario "
        		+ "FROM usuarios WHERE id_usuario = ?;";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(select)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
            	TipoUsuarioDaoImpl dao = new TipoUsuarioDaoImpl();
            	TipoUsuario tipo = dao.buscarPorId(result.getInt("tipo_usuario"));
                return new Usuario(
                    result.getInt("id_usuario"),
                    result.getString("nombre_usuario"),
                    result.getString("pass"),
                    tipo,
                    result.getBoolean("estado_usuario")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> buscarTodos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String select = "SELECT id_usuario, nombre_usuario, pass, tipo_usuario, estado_usuario "
        		+ "FROM usuarios WHERE estado_usuario = 1;";
        ArrayList<Usuario> resultado = new ArrayList<>();
        ArrayList<TipoUsuario> tipos = new TipoUsuarioDaoImpl().buscarTodos();

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(select)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
            	TipoUsuario tipo = new TipoUsuario(1, "Cliente");
            	for (TipoUsuario tipoUsuario : tipos) {
					if (tipoUsuario.getId() == result.getInt("tipo_usuario")) {
						tipo = tipoUsuario;
						break;
					}
				}
                Usuario usuario = new Usuario(
                    result.getInt("id_usuario"),
                    result.getString("nombre_usuario"),
                    result.getString("pass"),
                    tipo,
                    result.getBoolean("estado_usuario")
                );
                resultado.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return resultado;
        }
        return resultado;
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        String insert = "INSERT INTO usuarios (nombre_usuario, pass, tipo_usuario, estado_usuario) "
        		+ "VALUES (?, ?, ?, ?);";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(insert)) {

        	
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getPass());
            statement.setInt(3, usuario.getTipoUsuario().getId());
            statement.setBoolean(4, usuario.activo());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        String update = "UPDATE usuarios SET nombre_usuario = ?, pass = ?, tipo_usuario = ?, "
        		+ "estado_usuario = ? WHERE id_usuario = ?;";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(update)) {

            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getPass());
            statement.setInt(3, usuario.getTipoUsuario().getId());
            statement.setBoolean(4, usuario.activo());
            statement.setInt(5, usuario.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        String update = "UPDATE usuarios SET estado_usuario = 0 WHERE id_usuario = ?;";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(update)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public Usuario buscarPorNombre(String nombre) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String select = "SELECT id_usuario, nombre_usuario, pass, tipo_usuario, estado_usuario "
        		+ "FROM usuarios WHERE nombre_usuario = ?;";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(select)) {

            statement.setString(1, nombre);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
            	TipoUsuarioDaoImpl dao = new TipoUsuarioDaoImpl();
            	TipoUsuario tipo = dao.buscarPorId(result.getInt("tipo_usuario"));
                return new Usuario(
                    result.getInt("id_usuario"),
                    result.getString("nombre_usuario"),
                    result.getString("pass"),
                    tipo,
                    result.getBoolean("estado_usuario")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
	}
    
    

}

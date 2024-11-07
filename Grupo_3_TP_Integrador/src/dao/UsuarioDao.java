package dao;

import java.util.ArrayList;
import dominio.Usuario;

public interface UsuarioDao {
	Usuario buscarPorId(int id);
    ArrayList<Usuario> buscarTodos();
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int id);

}



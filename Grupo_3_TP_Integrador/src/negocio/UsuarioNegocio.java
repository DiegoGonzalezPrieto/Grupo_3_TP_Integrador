package negocio;

import java.util.ArrayList;


import dominio.Usuario;

public interface UsuarioNegocio {

	Usuario buscarPorId(int id);
    ArrayList<Usuario> buscarTodos();
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int id);

}

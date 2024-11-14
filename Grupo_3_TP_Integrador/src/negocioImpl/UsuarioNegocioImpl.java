
package negocioImpl;

import java.util.ArrayList;
import daoImpl.UsuarioDaoImpl;
import dominio.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

    private UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

    @Override
    public Usuario buscarPorId(int id) {
        return usuarioDao.buscarPorId(id);
    }

    @Override
    public ArrayList<Usuario> buscarTodos() {
        return usuarioDao.buscarTodos();
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarioDao.agregarUsuario(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioDao.eliminarUsuario(id);
    }

	@Override
	public Usuario buscarPorNombre(String nombre) {
		return usuarioDao.buscarPorNombre(nombre);
	}
}

package dao;

import java.util.ArrayList;

import dominio.TipoUsuario;

public interface TipoUsuarioDao {

	public TipoUsuario buscarPorId(int id);

	public ArrayList<TipoUsuario> buscarTodos();

}

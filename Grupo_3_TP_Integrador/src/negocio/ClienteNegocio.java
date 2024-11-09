package negocio;

import java.util.ArrayList;

import dominio.Cliente;
import dominio.Usuario;

public interface ClienteNegocio {
	
    void insert(Cliente c);
    void update(Cliente c);
    void delete(int id);
    
    Cliente buscarPorNombre(String nombre);
    Cliente buscarPorId(int id);
    ArrayList<Cliente> listar();
    
}



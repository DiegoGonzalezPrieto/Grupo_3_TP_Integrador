package negocio;

import dominio.Cliente;

public interface ClienteNegocio {
	
    void insert(Cliente c);
    void update(Cliente c);
    void delete(int id);
}



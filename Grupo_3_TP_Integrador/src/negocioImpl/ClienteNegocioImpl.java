package negocioImpl;

import daoImpl.ClienteDaoImpl;
import dominio.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

	private ClienteDaoImpl clienteDao = new ClienteDaoImpl();
	@Override
	public void insert(Cliente c) {
		clienteDao.insert(c);
		
	}

	@Override
	public void update(Cliente c) {
		clienteDao.update(c.getId(), c);
	}

	@Override
	public void delete(int id) {
		clienteDao.delete(id);
		
	}

}


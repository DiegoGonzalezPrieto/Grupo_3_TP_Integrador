package negocioImpl;

import java.util.ArrayList;
import daoImpl.NacionalidadDaoImpl;
import dominio.Nacionalidad;
import negocio.NacionalidadNegocio;

public class NacionalidadNegocioImpl implements NacionalidadNegocio {

    @Override
    public Nacionalidad buscarPorId(int id) {
        NacionalidadDaoImpl dao = new NacionalidadDaoImpl();
        return dao.buscarPorId(id);
    }

    @Override
    public ArrayList<Nacionalidad> buscarTodos() {
        NacionalidadDaoImpl dao = new NacionalidadDaoImpl();
        return dao.buscarTodos();
    }
}


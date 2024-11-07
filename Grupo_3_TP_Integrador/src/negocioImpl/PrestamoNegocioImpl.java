package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import dominio.Prestamo;

import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
	
	///ATRIBUTOS
	
	private PrestamoDao pDao = new PrestamoDaoImpl();	
	//private CuotaNegocio cuotaNeg = new CuotaNegImpl();
	//private CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	
	
	@Override
	public boolean crearPrestamo(Prestamo prestamo) throws SQLException {
		
		try {
			return pDao.insert(prestamo);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
		
	}

	@Override
	public boolean actualizarEstadoSolicitud(int idPrestamo, int estadoSolicitud) throws SQLException {
		
		try {
			return pDao.updateEstado(idPrestamo, estadoSolicitud);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {
		
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosEnEvaluacio() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int contarPrestamosAprobados() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int contarPrestamosRechazados() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int contarPrestamosEnEvaluacion() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal sumarPrestamosAprobados() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal sumarPrestamosRechazados() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

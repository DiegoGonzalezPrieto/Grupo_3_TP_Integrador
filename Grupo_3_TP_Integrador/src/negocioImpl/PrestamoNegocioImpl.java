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
	//private CuotaNegocio cuotaNeg = new CuotaNegImpl(); esto para el metodo de aprobar prestamo
	//private CuentaNegocio cuentaNeg = new CuentaNegocioImpl(); esto para el metodo aprobar prestamo
	
	
	
	//-------------------------------METODOS LOCALES------------------------------------//
	
	public boolean aprobarPrestamo(Prestamo prestamo) throws SQLException{
		
		boolean aprobacion = false;
		
		return aprobacion;
		
	}
	
	
	//-------------------------------METODOS DML ------------------------------------//
	
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

	//-------------------------------METODOS PARA LISTAR ------------------------------------//
	
	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {
		
		try {
			return pDao.obtenerPrestamoPorId(idPrestamo);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException {
		
		try {
			return pDao.listarPrestamosXCliente(idCliente);
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException {
		
		try {
			return pDao.listarTodosLosPrestamos();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException {
		
		try {
			return pDao.listarTodosLosPrestamosAprobados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException {
		
		try {
			return pDao.listarTodosLosPrestamosRechazados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosEnProceso() throws SQLException {
		
		try {
			return pDao.listarTodosLosPrestamosEnProceso();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosAprobados() throws SQLException {
		
		try {
			return pDao.contarPrestamosAprobados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosRechazados() throws SQLException {
		
		try {
			return pDao.contarPrestamosRechazados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosEnEvaluacion() throws SQLException {
		
		try {
			return pDao.contarPrestamosEnEvaluacion();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosAprobados() throws SQLException {
		
		try {
			return pDao.sumarPrestamosAprobados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosRechazados() throws SQLException {
		
		try {
			return pDao.sumarPrestamosRechazados();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException {
		
		try {
			return pDao.sumarPrestamosEnEvaluacion();
		} 
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

}

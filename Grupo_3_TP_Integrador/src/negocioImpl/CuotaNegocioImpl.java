package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.CuotaDao;
import daoImpl.CuotaDaoImp;
import dominio.Cuota;
import negocio.CuotaNegocio;
import dominio.Prestamo;

public class CuotaNegocioImpl implements CuotaNegocio {
	
	//DECLARAR VARIBLES	
	private CuotaDao cuotaDao = new CuotaDaoImp ();
	
	
	//--------------------------------METODOS DML------------------------------//
	@Override
	public boolean agregarCuotas(Prestamo prestamo) throws SQLException {
		
		Cuota cuota = new Cuota();
		try {
			
			boolean cuotasGeneradas = false;
			for(int i = 1; i <= prestamo.getCuotas(); i++) {
				
				cuota.setPrestamo(prestamo);
				cuota.setNumeroCuota(i);
										
				cuotaDao.insert(cuota);
				if(i == prestamo.getCuotas()) {
					cuotasGeneradas = true;
				}
			}
			return cuotasGeneradas;
			
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}	}

	@Override
	public boolean registrarPago(int idCuenta, Cuota cuota) throws Exception, SQLException {
		
		
		return false;
	}
	
	
	
	//--------------------------------METODOS PARA LISTAR------------------------------//

	@Override
	public Cuota obtenerCuotaPorId(int idCuota) throws SQLException {
		
		try {
			return cuotaDao.obtenerCuotaPorId(idCuota);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Cuota> listarCuotasPorPrestamo(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.listarCuotasPorIdPrestamo(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Cuota> listarCuotasPagadas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.listarCuotasPagadas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Cuota> listarCuotasPendientes(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.listarCuotasPendientes(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	
	//--------------------------------METODOS PARA INFORME------------------------------//

	@Override
	public int contarCuotasPagadas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.contarCuotasPagadas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarCuotasPendientes(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.contarCuotasPendientes(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarCuotas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.contarCuotas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarCuotasPagadas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.sumarCuotasPagadas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarCuotasPendientes(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.sumarCuotasPendientes(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarCuotas(int idPrestamo) throws SQLException {
		try {
			return cuotaDao.sumarCuotas(idPrestamo);
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
	}

	

}

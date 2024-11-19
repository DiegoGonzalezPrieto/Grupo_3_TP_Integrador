package negocioImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import dao.CuotaDao;
import daoImpl.CuotaDaoImp;
import dominio.Cuenta;
import dominio.Cuota;
import dominio.Movimiento;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.MovimientoNegocio;
import negocio.TipoMovimientoNegocio;
import dominio.Prestamo;
import dominio.TipoMovimiento;

public class CuotaNegocioImpl implements CuotaNegocio {
	
	//DECLARAR VARIBLES	
	private CuotaDao cuotaDao = new CuotaDaoImp ();
	
	
	//--------------------------------METODOS DML------------------------------//
	@Override
	public boolean agregarCuotas(Prestamo prestamo) throws SQLException {
		
		Cuota cuota = new Cuota();
		
		try {
			
			boolean cuotasGeneradas = false;
			Calendar calendar = Calendar.getInstance();
			///calendar.setTime(prestamo.getFechaAltaPrestamo());
			for(int i = 1; i <= prestamo.getCuotas(); i++) {
				
				cuota.setPrestamo(prestamo);
				cuota.setNumeroCuota(i);
				cuota.setMontoPagado(prestamo.getImporteMensual());

				calendar.add(Calendar.MONTH, 1);
				cuota.setFechaPago(new java.sql.Date(calendar.getTimeInMillis()));
				
										
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
		
		Cuenta cuenta = new Cuenta();
		CuentaNegocio cNeg = new CuentaNegocioImpl();
		
		MovimientoNegocio mNeg = new MovimientoNegocioImpl();
		
		TipoMovimiento tipoMovimiento;
		TipoMovimientoNegocio tmNeg = new TipoMovimientoNegocioImpl();
		
		try {
			
			boolean cuotaPagada = false;
			
			//SETEA LA CUENTA COMO PAGA 
			cuotaPagada = cuotaDao.registrarPago(cuota.getId(), 1);
			
			
			if(cuotaPagada) {
				//DESCONTAMOS SALDO A LA CUENTA
				cuenta = cNeg.obtenerCuentaPorId(idCuenta);
				cuenta.setSaldo(cuenta.getSaldo().subtract(cuota.getMontoPagado()));
				
				//ACTUALIZAMOS LA CUENTA 
				boolean cuentaActualizada=cNeg.actualizarCuenta(cuenta);
				
				if(cuentaActualizada) {
						
					//REGISTRA EL MOVIMIENTO EN BD
					tipoMovimiento = tmNeg.buscarPorId(2);
										
					java.util.Date date = new java.util.Date();
					java.sql.Date hoy = new java.sql.Date(date.getTime());							
					
					Movimiento movimientoCuota = new Movimiento(0,cuenta,tipoMovimiento,hoy,"Pago de Cuota",cuota.getMontoPagado());
					
										
					mNeg.insert(movimientoCuota);
					
				}else {throw new SQLException("no se actualizo la cuenta");}
				
			}else {throw new SQLException("no se registro el pago");}
			
			return cuotaPagada;
		}
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}	
		
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

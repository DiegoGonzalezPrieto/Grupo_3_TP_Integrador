
package negocioImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.Prestamo;
import dominio.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.MovimientoNegocio;
import negocio.PrestamoNegocio;
import negocio.TipoMovimientoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {

	/// ATRIBUTOS

	private PrestamoDao pDao = new PrestamoDaoImpl();
	private CuotaNegocio cNeg = new CuotaNegocioImpl();
	private CuentaNegocio cuNeg = new CuentaNegocioImpl();
	private Cuenta cuenta = new Cuenta();
	private BigDecimal saldoPrestamo = null;

	// -------------------------------METODOS
	// LOCALES------------------------------------//

	public Boolean aprobarPrestamo(Prestamo prestamo) throws SQLException {
		// DECLARACION DE VARIABLES
		boolean aprobacionOK = false;

		try {
			// VERIFICAMOS QUE EL PRESTAMO ESTE PENDIENTE
			if (prestamo.getEstadoValidacion().getId() == 1) {

				// 1 PASO: CREAMOS LAS CUOTAS PARA EL PRESTAMO.
				boolean generarCuotas = cNeg.agregarCuotas(prestamo);

				if (generarCuotas) {

					// 2 PASO: CAMBIAR EL ESTADO DEL PRESTAMO A ACEPTADO
					boolean actualizarEstado = actualizarEstadoSolicitud(prestamo.getId(), 2);

					// 3 TRAEMOS EL SALDO Y DEL PRESTAMO Y LA CUENTA A ACREDITAR
					saldoPrestamo = prestamo.getImportePrestamo();
					cuenta = cuNeg.obtenerCuentaPorId(prestamo.getCuenta().getId());

					// 4 PASO ACREDITAMOS SALDO A LA CUENTA
					cuenta.setSaldo(cuenta.getSaldo().add(saldoPrestamo));

					// 5 PASO ACTUALIZAMOS CUENTA
					boolean cuentaActualizada = cuNeg.actualizarCuenta(cuenta);

					// 5 PASO CREAR MOVIMIENTO
					if (cuentaActualizada) {

						/// REGISTRA EL MOVIMIENTO EN BD
						int idCuenta = prestamo.getCuenta().getId();
						CuentaNegocio cNeg = new CuentaNegocioImpl();

						MovimientoNegocio mNeg = new MovimientoNegocioImpl();

						TipoMovimiento tipoMovimiento;
						TipoMovimientoNegocio tmNeg = new TipoMovimientoNegocioImpl();

						cuenta = cNeg.obtenerCuentaPorId(idCuenta);
						tipoMovimiento = tmNeg.buscarPorId(2);

						java.util.Date date = new java.util.Date();
						java.sql.Date hoy = new java.sql.Date(date.getTime());

						Movimiento movimientoPrestamo = new Movimiento(0, cuenta, tipoMovimiento, hoy,
								"Acreditacion de Prestamo", prestamo.getImportePrestamo());
						mNeg.insert(movimientoPrestamo);

					} else {
						throw new SQLException("no se pudo crear el movimiento en la BD");
					}
				} else {
					throw new SQLException("no se pudo actualizar la cuenta en la BD");
				}
			} else {
				throw new SQLException("el prestamo no esta pendiente de aprobacion");
			}

		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}

		return aprobacionOK;
	}

	// -------------------------------METODOS DML
	// ------------------------------------//

	@Override
	public boolean crearPrestamo(Prestamo prestamo) throws SQLException {

		try {
			return pDao.insert(prestamo);
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}

	}

	@Override
	public boolean actualizarEstadoSolicitud(int idPrestamo, int estadoSolicitud) throws SQLException {

		try {
			return pDao.updateEstado(idPrestamo, estadoSolicitud);
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}

	}

	// -------------------------------METODOS PARA LISTAR
	// ------------------------------------//

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {

		try {
			return pDao.obtenerPrestamoPorId(idPrestamo);
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException {

		try {
			return pDao.listarPrestamosXCliente(idCliente);
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException {

		try {
			return pDao.listarTodosLosPrestamos();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException {

		try {
			return pDao.listarTodosLosPrestamosAprobados();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException {

		try {
			return pDao.listarTodosLosPrestamosRechazados();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosEnProceso() throws SQLException {

		try {
			return pDao.listarTodosLosPrestamosEnProceso();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosAprobados() throws SQLException {

		try {
			return pDao.contarPrestamosAprobados();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosRechazados() throws SQLException {

		try {
			return pDao.contarPrestamosRechazados();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosEnEvaluacion() throws SQLException {

		try {
			return pDao.contarPrestamosEnEvaluacion();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosAprobados() throws SQLException {

		try {
			return pDao.sumarPrestamosAprobados();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosRechazados() throws SQLException {

		try {
			return pDao.sumarPrestamosRechazados();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException {

		try {
			return pDao.sumarPrestamosEnEvaluacion();
		} catch (SQLException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int contarPrestamosAprobados(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.contarPrestamosAprobados(fechaInicio, fechaFin);
	}

	@Override
	public int contarPrestamosRechazados(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.contarPrestamosRechazados(fechaInicio, fechaFin);
	}

	@Override
	public int contarPrestamosEnEvaluacion(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.contarPrestamosEnEvaluacion(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal sumarPrestamosAprobados(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.sumarPrestamosAprobados(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal sumarPrestamosRechazados(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.sumarPrestamosRechazados(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal sumarPrestamosEnEvaluacion(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.sumarPrestamosEnEvaluacion(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal getPromedioPrestamos(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.getPromedioPrestamos(fechaInicio, fechaFin);
	}

	@Override
	public int contarPrestamosPagados(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.contarPrestamosPagados(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal sumarPrestamosPagados(Date fechaInicio, Date fechaFin) throws SQLException {
		return pDao.sumarPrestamosPagados(fechaInicio, fechaFin);
	}

}
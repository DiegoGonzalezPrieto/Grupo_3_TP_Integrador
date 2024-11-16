package negocioImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import dominio.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

	private CuentaDao cuentaDao;

	private static final int MAX_CUENTAS_ACTIVAS = 3;

	public CuentaNegocioImpl() {
		this.cuentaDao = new CuentaDaoImpl();
	}

	@Override
	public boolean crearCuenta(Cuenta cuenta) {
		if (!puedeCrearCuenta(cuenta.getCliente().getIdCliente())) {
			return false;
		}

		if (cuentaDao.existeCBU(cuenta.getCbu()) || !validarNumeroCuenta(cuenta.getNumeroCuenta())) {
			return false;
		}

		return cuentaDao.insert(cuenta);
	}

	@Override
	public boolean actualizarCuenta(Cuenta cuenta) {
		Cuenta cuentaExistente = obtenerCuentaPorId(cuenta.getId());
		if (cuentaExistente == null) {
			return false;
		}

		return cuentaDao.update(cuenta);
	}

	@Override
	public boolean eliminarCuenta(int id) {
		Cuenta cuenta = obtenerCuentaPorId(id);
		if (cuenta == null || cuenta.getSaldo().doubleValue() > 0) {
			return false;
		}

		return cuentaDao.delete(id);
	}

	@Override
	public Cuenta obtenerCuentaPorId(int id) {
		return cuentaDao.encontrarPorId(id);
	}

	@Override
	public List<Cuenta> listarTodas() {
		List<Cuenta> cuentas = cuentaDao.obtenerTodos();
		return cuentas != null ? cuentas : new ArrayList<>();
	}

	@Override
	public List<Cuenta> listarPorCliente(int idCliente) {
		List<Cuenta> cuentas = cuentaDao.obtenerTodoPorCliente(idCliente);
		return cuentas != null ? cuentas : new ArrayList<>();
	}

	@Override
	public boolean puedeCrearCuenta(int idCliente) {
		int cuentasActivas = cuentaDao.encontrarCuentaActivaPorCliente(idCliente);
		return cuentasActivas < MAX_CUENTAS_ACTIVAS;
	}

	@Override
	public boolean validarCBU(String cbu) {
		if (cbu == null || cbu.trim().isEmpty()) {
			return false;
		}

		if (!cbu.matches("\\d{22}")) {
			return false;
		}

		return !cuentaDao.existeCBU(cbu);
	}

	@Override
	public boolean validarNumeroCuenta(Long numeroCuenta) {
		if (numeroCuenta == null) {
			return false;
		}

		if (numeroCuenta <= 0) {
			return false;
		}

		return !cuentaDao.existeNumeroCuenta(numeroCuenta);
	}

	@Override
	public int obtenerReporteCantidadDeCuentas(Date fechaInicio, Date fechaFin) {
		return cuentaDao.obtenerReporteCantidadDeCuentas(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal obtenerReporteSumaDeSaldos(Date fechaInicio, Date fechaFin) {
		return cuentaDao.obtenerReporteSumaDeSaldos(fechaInicio, fechaFin);
	}

	@Override
	public BigDecimal obtenerReporteSaldoPromedio(Date fechaInicio, Date fechaFin) {
		return cuentaDao.obtenerReporteSaldoPromedio(fechaInicio, fechaFin);
	}

	public Long obtenerUltimoNumeroCuenta() {
		Long ultimoNumeroCuenta = cuentaDao.obtenerUltimoNumeroCuenta();
		return ultimoNumeroCuenta;
	}

	@Override
	public String obtenerUltimoCBU() {
		String ultimoCBU = cuentaDao.obtenerUltimoCBU();
		return ultimoCBU;
	}

	public int totalCuentasAbiertas() {
		return cuentaDao.obtenerTodos().size();
	}

	public List<Cuenta> listarCuentasRecientes() {
		return cuentaDao.obtenerCuentasRecientes();
	}

	@Override
	public Cuenta obtenerCuentaPorCbu(String cbu) {
		return cuentaDao.obtenerCuentaPorCbu(cbu);
	}

	@Override
	public List<Cuenta> listarActivasPorCliente(int idCliente) {
		List<Cuenta> cuentas = cuentaDao.obtenerTodoPorCliente(idCliente);
		cuentas.removeIf(c -> !c.Activa());
		return cuentas != null ? cuentas : new ArrayList<>();
	}

}

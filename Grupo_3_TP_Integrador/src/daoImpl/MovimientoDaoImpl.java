package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.CuentaDao;
import dao.MovimientoDao;
import dao.TipoMovimientoDao;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.TipoMovimiento;
import dominio.TipoUsuario;
import dominio.Usuario;

public class MovimientoDaoImpl implements MovimientoDao {

	@Override
	public void insert(Movimiento m) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "INSERT INTO movimientos(id_cuenta, id_tipo_movimiento, fecha_movimiento, concepto, importe_movimiento) VALUES (?, ?, ?, ?, ?);";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, m.getCuenta().getId());
			statement.setInt(2, m.getTipo().getId());
			statement.setDate(3, (Date) m.getFecha());
			statement.setString(4, m.getConcepto());
			statement.setBigDecimal(5, m.getMonto());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Movimiento m) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "UPDATE Movimientos SET " + " id_cuenta = ?, id_tipo_movimiento = ?, fecha_movimiento = ?, "
				+ " concepto = ?, importe_movimiento = ? " + " WHERE id_movimiento = ?;";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, m.getCuenta().getId());
			statement.setInt(2, m.getTipo().getId());
			statement.setDate(3, (Date) m.getFecha());
			statement.setString(4, m.getConcepto());
			statement.setBigDecimal(5, m.getMonto());
			statement.setInt(6, m.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// @Override
	// public void delete(int id) {
	//
	// try{
	// Class.forName("com.mysql.jdbc.Driver");
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	//
	// //TODO La tabla Movimientos en la BD no tiene estado_movimiento
	//
	// /*
	// String update = "UPDATE usuarios SET estado_usuario = 0 WHERE id_usuario =
	// ?;";
	//
	// try (Connection conexion = Conexion.getConnection();
	// PreparedStatement statement = conexion.prepareStatement(update)) {
	//
	// statement.setInt(1, id);
	// statement.executeUpdate();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// */
	// }

	@Override
	public Movimiento encontrarPorId(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "SELECT id_movimiento, id_cuenta, id_tipo_movimiento,  fecha_movimiento, concepto, importe_movimiento "
				+ " FROM movimientos WHERE id_movimiento = ?;";

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				TipoMovimientoDaoImpl movDao = new TipoMovimientoDaoImpl();
				CuentaDaoImpl cuentaDao = new CuentaDaoImpl();
				TipoMovimiento tipo = movDao.buscarPorId(result.getInt("id_tipo_movimiento"));
				Cuenta cuenta = cuentaDao.encontrarPorId(result.getInt("id_cuenta"));
				String concepto = result.getString("concepto");
				Date fecha = result.getDate("fecha_movimiento");
				int idMov = result.getInt("id_movimiento");
				BigDecimal monto = result.getBigDecimal("importe_movimiento");

				Movimiento mov = new Movimiento(idMov, cuenta, tipo, fecha, concepto, monto);
				return mov;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	@Override
	public ArrayList<Movimiento> listarMovimientosPorCuenta(int idCuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "SELECT id_movimiento, id_cuenta, id_tipo_movimiento, "
				+ " fecha_movimiento, concepto, importe_movimiento FROM movimientos WHERE id_cuenta = ?;";

		ArrayList<Movimiento> movimientos = new ArrayList<>();
		ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos();
		Cuenta cuenta = new CuentaDaoImpl().encontrarPorId(idCuenta);
		TipoMovimientoDao tipoDao = new TipoMovimientoDaoImpl();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			statement.setInt(1, idCuenta);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				TipoMovimiento t = tipoDao.buscarPorId(resultado.getInt("id_tipo_movimiento"));
				String concepto = resultado.getString("concepto");
				Date fecha = resultado.getDate("fecha_movimiento");
				int idMov = resultado.getInt("id_movimiento");
				BigDecimal monto = resultado.getBigDecimal("importe_movimiento");
				Movimiento mov = new Movimiento(idMov, cuenta, t, fecha, concepto, monto);
				movimientos.add(mov);
			}

			return movimientos;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movimientos;
	}

	@Override
	public ArrayList<Movimiento> listarTodos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String query = "SELECT id_movimiento, id_cuenta, id_tipo_movimiento, "
				+ " fecha_movimiento, concepto, importe_movimiento FROM movimientos";

		ArrayList<Movimiento> movimientos = new ArrayList<>();
		ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos();
		CuentaDao cuentaDao = new CuentaDaoImpl();
		TipoMovimientoDao tipoDao = new TipoMovimientoDaoImpl();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				Cuenta cuenta = cuentaDao.encontrarPorId(resultado.getInt("id_cuenta"));
				TipoMovimiento t = tipoDao.buscarPorId(resultado.getInt("id_tipo_movimiento"));
				String concepto = resultado.getString("concepto");
				Date fecha = resultado.getDate("fecha_movimiento");
				int idMov = resultado.getInt("id_movimiento");
				BigDecimal monto = resultado.getBigDecimal("importe_movimiento");
				Movimiento mov = new Movimiento(idMov, cuenta, t, fecha, concepto, monto);
				movimientos.add(mov);
			}

			return movimientos;
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return movimientos;
	}

	@Override
	public int obtenerCantidadMovimientos(java.util.Date fechaInicio, java.util.Date fechaFin) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String query = "SELECT COUNT(*) as cantidad FROM movimientos WHERE fecha_movimiento BETWEEN ? AND ?";

		int cantMovimientos = 0;
		ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos();
		CuentaDao cuentaDao = new CuentaDaoImpl();
		TipoMovimientoDao tipoDao = new TipoMovimientoDaoImpl();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			statement.setString(1, sdf.format(fechaInicio));
			statement.setString(2, sdf.format(fechaFin));

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				cantMovimientos = resultado.getInt("cantidad");
			}

			return cantMovimientos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cantMovimientos;
	}

	@Override
	public int obtenerCantidadAltasCuenta(java.util.Date fechaInicio, java.util.Date fechaFin) {
		return obtenerCantidadMovimientosPorTipo(fechaInicio, fechaFin, 1);
	}

	@Override
	public int obtenerCantidadTransferencias(java.util.Date fechaInicio, java.util.Date fechaFin) {
		return obtenerCantidadMovimientosPorTipo(fechaInicio, fechaFin, 4);
	}

	@Override
	public int obtenerCantidadAltasPrestamo(java.util.Date fechaInicio, java.util.Date fechaFin) {
		return obtenerCantidadMovimientosPorTipo(fechaInicio, fechaFin, 2);
	}

	@Override
	public int obtenerCantidadPagosPrestamo(java.util.Date fechaInicio, java.util.Date fechaFin) {
		return obtenerCantidadMovimientosPorTipo(fechaInicio, fechaFin, 3);

	}

	@Override
	public int obtenerCantidadMovimientosPorTipo(java.util.Date fechaInicio, java.util.Date fechaFin, int idTipo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String query = "SELECT COUNT(*) as cantidad FROM movimientos "
				+ " WHERE id_tipo_movimiento = ? AND fecha_movimiento BETWEEN ? AND ?;";

		int cantMovimientos = 0;
		ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos();
		CuentaDao cuentaDao = new CuentaDaoImpl();
		TipoMovimientoDao tipoDao = new TipoMovimientoDaoImpl();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			statement.setInt(1, idTipo);
			statement.setString(2, sdf.format(fechaInicio));
			statement.setString(3, sdf.format(fechaFin));
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				cantMovimientos = resultado.getInt("cantidad");
			}

			return cantMovimientos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cantMovimientos;
	}

	@Override
	public BigDecimal obtenerSumaTransferencias(java.util.Date fechaInicio, java.util.Date fechaFin) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String query = "SELECT COALESCE(SUM(importe_movimiento), 0) as suma FROM movimientos "
				+ " WHERE id_tipo_movimiento = 4 AND fecha_movimiento BETWEEN ? AND ?;";

		BigDecimal sumaMovimientos = new BigDecimal(0);
		ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos();
		CuentaDao cuentaDao = new CuentaDaoImpl();
		TipoMovimientoDao tipoDao = new TipoMovimientoDaoImpl();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			statement.setString(1, sdf.format(fechaInicio));
			statement.setString(2, sdf.format(fechaFin));
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				sumaMovimientos = resultado.getBigDecimal("suma");
			}

			return sumaMovimientos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sumaMovimientos;
	}

	@Override
	public BigDecimal obtenerPromedioTransferencias(java.util.Date fechaInicio, java.util.Date fechaFin) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String query = "SELECT COALESCE(AVG(importe_movimiento), 0) as promedio FROM movimientos "
				+ " WHERE id_tipo_movimiento = 4 AND fecha_movimiento BETWEEN ? AND ?;";

		BigDecimal promedioTransferencias = new BigDecimal(0);
		ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos();
		CuentaDao cuentaDao = new CuentaDaoImpl();
		TipoMovimientoDao tipoDao = new TipoMovimientoDaoImpl();

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(query)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			statement.setString(1, sdf.format(fechaInicio));
			statement.setString(2, sdf.format(fechaFin));
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				promedioTransferencias = resultado.getBigDecimal("promedio");
			}

			return promedioTransferencias;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return promedioTransferencias;
	}

}

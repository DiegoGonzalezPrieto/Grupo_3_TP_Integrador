package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CuentaDao;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;

public class CuentaDaoImpl implements CuentaDao {

	private static final int idCuentaExcluir = 0;

	@Override
	public boolean insert(Cuenta cuenta) {
		String insert = "INSERT INTO cuentas (id_cliente, id_tipo_cuenta, fecha_creacion, "
				+ "numero_cuenta, cbu, saldo, estado_cuenta) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(insert)) {

			statement.setInt(1, cuenta.getCliente().getIdCliente());
			statement.setInt(2, cuenta.getTipoCuenta().getId());
			statement.setDate(3, cuenta.getFechaCreacion());
			statement.setLong(4, cuenta.getNumeroCuenta());
			statement.setString(5, cuenta.getCbu());
			statement.setBigDecimal(6, cuenta.getSaldo());
			statement.setBoolean(7, cuenta.Activa());

			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Cuenta cuenta) {

		String update = "UPDATE cuentas SET id_tipo_cuenta = ?, saldo = ? WHERE id_cuenta = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(update)) {

			statement.setInt(1, cuenta.getTipoCuenta().getId());
			statement.setBigDecimal(2, cuenta.getSaldo());
			statement.setInt(3, cuenta.getId());

			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		String delete = "UPDATE cuentas SET estado_cuenta = false WHERE id_cuenta = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(delete)) {

			statement.setInt(1, id);
			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Cuenta encontrarPorId(int id) {
		String encontrarPorId = "SELECT c.*, cl.nombre as nombre_cliente, cl.apellido as apellido_cliente, tc.tipo_cuenta as tipo_cuenta "
				+ "FROM cuentas c INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente "
				+ "INNER JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta WHERE c.id_cuenta = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(encontrarPorId)) {

			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return mapResultSetDeCuenta(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Cuenta> obtenerTodos() {
		List<Cuenta> listaCuentas = new ArrayList<>();

		String obtenerTodos = "SELECT c.*, cl.nombre as nombre_cliente, cl.apellido as apellido_cliente, tc.tipo_cuenta as tipo_cuenta "
				+ "FROM cuentas c " + "INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente "
				+ "INNER JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta WHERE c.estado_cuenta = 1";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(obtenerTodos)) {

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				listaCuentas.add(mapResultSetDeCuenta(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCuentas;
	}

	@Override
	public List<Cuenta> obtenerTodoPorCliente(int idCliente) {
		List<Cuenta> listaCuentas = new ArrayList<>();

		String obtenerTodoPorCliente = "SELECT c.*, cl.nombre as nombre_cliente, cl.apellido as apellido_cliente, "
				+ "tc.tipo_cuenta as tipo_cuenta " + "FROM cuentas c "
				+ "INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente "
				+ "INNER JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta " + "WHERE c.id_cliente = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(obtenerTodoPorCliente)) {

			statement.setInt(1, idCliente);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				listaCuentas.add(mapResultSetDeCuenta(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCuentas;
	}

	@Override
	public int encontrarCuentaActivaPorCliente(int idCliente) {

		String cuentaActiva = "SELECT COUNT(*) as cantidad FROM cuentas WHERE id_cliente = ? AND estado_cuenta = true";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(cuentaActiva)) {

			statement.setInt(1, idCliente);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean existeCBU(String cbu) {
		String existeCBU = "SELECT COUNT(*) FROM cuentas WHERE cbu = ? AND id_cuenta != ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(existeCBU)) {

			statement.setString(1, cbu);
			statement.setInt(2, idCuentaExcluir);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean existeNumeroCuenta(Long numeroCuenta) {
		String existeCuenta = "SELECT COUNT(*) FROM cuentas WHERE numero_cuenta = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(existeCuenta)) {

			statement.setLong(1, numeroCuenta);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Long obtenerUltimoNumeroCuenta() {
		String select = "SELECT MAX(numero_cuenta) FROM cuentas";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Long ultimoNumero = rs.getLong(1);
				return ultimoNumero == 0 ? 100000L : ultimoNumero;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 100000L;
	}

	@Override
	public String obtenerUltimoCBU() {
		String select = "SELECT MAX(cbu) FROM cuentas";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(select)) {

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String ultimoCBU = rs.getString(1);
				return ultimoCBU == null ? "1000000000000000000000" : ultimoCBU;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "1000000000000000000000";
	}

	private Cuenta mapResultSetDeCuenta(ResultSet rs) throws SQLException {

		Cuenta cuenta = new Cuenta();
		cuenta.setId(rs.getInt("id_cuenta"));

		Cliente cliente = new Cliente();
		cliente.setIdCliente(rs.getInt("id_cliente"));
		cliente.setNombre(rs.getString("nombre_cliente"));
		cliente.setApellido(rs.getString("apellido_cliente"));

		cuenta.setCliente(cliente);

		TipoCuenta tipoCuenta = new TipoCuenta(rs.getInt("id_tipo_cuenta"), rs.getString("tipo_cuenta"));

		cuenta.setTipoCuenta(tipoCuenta);

		cuenta.setFechaCreacion(rs.getDate("fecha_creacion"));
		cuenta.setNumeroCuenta(rs.getLong("numero_cuenta"));
		cuenta.setCbu(rs.getString("cbu"));
		cuenta.setSaldo(rs.getBigDecimal("saldo"));
		cuenta.setActiva(rs.getBoolean("estado_cuenta"));

		return cuenta;
	}

	public List<Cuenta> obtenerCuentasRecientes() {
		List<Cuenta> cuentasRecientes = new ArrayList<>();
		String sql = " SELECT c.*, cl.nombre as nombre_cliente, cl.apellido as apellido_cliente, "
				+ " tc.tipo_cuenta as tipo_cuenta " + " FROM cuentas c "
				+ " INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente "
				+ " INNER JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta WHERE c.estado_cuenta = 1 "
				+ " ORDER BY c.fecha_creacion DESC LIMIT 5";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				cuentasRecientes.add(mapResultSetDeCuenta(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cuentasRecientes;
	}

	@Override
	public int obtenerReporteCantidadDeCuentas(Date fechaInicio, Date fechaFin) {
		String buscarCantidadCuentas = "SELECT  COALESCE(COUNT(*), 0) AS total FROM cuentas WHERE fecha_creacion BETWEEN ? AND ?;";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(buscarCantidadCuentas)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			statement.setString(1, sdf.format(fechaInicio));
			statement.setString(2, sdf.format(fechaFin));
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public BigDecimal obtenerReporteSumaDeSaldos(Date fechaInicio, Date fechaFin) {
		String buscarSumaSaldos = "SELECT  COALESCE(SUM(saldo), 0) AS total FROM cuentas WHERE estado_cuenta = TRUE AND fecha_creacion BETWEEN ? AND ?;";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(buscarSumaSaldos)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			statement.setString(1, sdf.format(fechaInicio));
			statement.setString(2, sdf.format(fechaFin));
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getBigDecimal(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal obtenerReporteSaldoPromedio(Date fechaInicio, Date fechaFin) {
		String buscarSumaSaldos = "SELECT COALESCE(AVG(saldo), 0) AS total FROM cuentas WHERE estado_cuenta = TRUE AND fecha_creacion BETWEEN ? AND ?;";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(buscarSumaSaldos)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			statement.setString(1, sdf.format(fechaInicio));
			statement.setString(2, sdf.format(fechaFin));
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getBigDecimal(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new BigDecimal(0);
	}

	@Override
	public Cuenta obtenerCuentaPorCbu(String cbu) {
		String encontrarPorId = "SELECT c.*, cl.nombre as nombre_cliente, cl.apellido as apellido_cliente, tc.tipo_cuenta as tipo_cuenta "
				+ "FROM cuentas c INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente "
				+ "INNER JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id_tipo_cuenta WHERE c.cbu = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(encontrarPorId)) {

			statement.setString(1, cbu);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return mapResultSetDeCuenta(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
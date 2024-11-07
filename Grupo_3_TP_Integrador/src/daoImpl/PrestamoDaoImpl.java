package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrestamoDao;
import dominio.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{
	
	/// ATRIBUTOS PARA TRABAJAR LA CONEXION
	
	///private PreparedStatement st;
	private ResultSet rs;
	
	/// CONSULTAS BASE DE DATOS
	// CONSULTA DML:
	private static final String insert = "INSERT INTO prestamos (id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo, importe_cuota, cantidad_cuotas, id_estado_prestamo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, )";
	private static final String updateEstado = "UPDATE prestamos SET id_estado_prestamo = ? WHERE id_prestamo = ?";
	
	//CONSULTA DE LISTA
	private static final String obtenerPrestamoPorId ="Select P.id_cliente,P.id_cuenta, P.fecha_alta_prestamo, P.importe_prestamo, P.meses_plazo, P.importe_cuota, P.cantidad_cuota, P.id_estado_prestamo, C.nombre, C.apellido from prestamos as P INNER JOIN clientes as C on P.id_cleinte = C.id_cliente WHERE P.id_cliente = ? ";
	private static final String listarPrestamosXCliente = "Select P.id_cliente,P.id_cuenta, P.fecha_alta_prestamo, P.importe_prestamo, P.meses_plazo, P.importe_cuota, P.cantidad_cuota, P.id_estado_prestamo, C.nombre, C.apellido, Ep.estado_prestamo from prestamos as P INNER JOIN clientes as C on P.id_cleinte = C.id_cliente INNER JOIN estados_prestamos as EP on P.id_estado_prestamo = EP.id_estado_prestamo WHERE C.id_cliente = ?";
	private static final String listarTodosLosPrestamos = "Select id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo,importe_cuota, cantidad_cuota, id_estado_prestamo from prestamos ";
	private static final String listarTodosLosPrestamosAprobados = "SELECT id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo,importe_cuota, cantidad_cuota, id_estado_prestamo from prestamos where id_estado_prestamo = 2";
	private static final String listarTodosLosPrestamosRechazados = "SELECT id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo,importe_cuota, cantidad_cuota, id_estado_prestamo from prestamos where id_estado_prestamo = 3";
	private static final String listarTodosLosPrestamosEnEvaluacion = "SELECT id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo,importe_cuota, cantidad_cuota, id_estado_prestamo from prestamos where id_estado_prestamo = 1";
	
	//CONSULTAS PARA INFORMES O REPORTES
	private static final String contarAprobados = "SELECT CASE WHEN COUNT(*) IS NULL THEN 0  ELSE COUNT(*) END AS cantidad FROM prestamos WHERE id_estado_prestamo = 2";
	private static final String contarRechazados = "SELECT CASE WHEN COUNT(*) IS NULL THEN 0  ELSE COUNT(*) END AS cantidad FROM prestamos WHERE id_estado_prestamo = 3";
	private static final String sumarValorAprobados = "SELECT CASE WHEN sum(importe_prestamo) IS NULL THEN 0 ELSE sum(importe_prestamo) END AS suma FROM prestamos WHERE id_estado_prestamo = 2";
	private static final String sumarValorRechazados = "SELECT CASE WHEN sum(importe_prestamo) IS NULL THEN 0 ELSE sum(importe_prestamo) END AS suma FROM prestamos WHERE id_estado_prestamo = 3";
	private static final String sumarValorEvaluacion = "SELECT CASE WHEN sum(importe_prestamo) IS NULL THEN 0 ELSE sum(importe_prestamo) END AS suma FROM prestamos WHERE id_estado_prestamo = 1";
	
	
	///METODOS ABSTRACTOS DE LA INTERFACE	
	@Override
	public boolean insert(Prestamo prestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO (ME FALTA CLIENTE Y CUENTA)		
		
		
		return false;
	}

	@Override
	public boolean updateEstado(int idPrestamo, int estadoPrestamo) throws SQLException {
		// // CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

			
		//DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(updateEstado)) {
			
			statement.setInt(1, estadoPrestamo);
			statement.setInt(2, idPrestamo);
			
			int filasAfectadas = statement.executeUpdate();
			
			if (filasAfectadas>0) {
				return true;				
			}		
			
		}
		catch(SQLException ex) {
			throw ex;
		}
		catch(Exception ex) {
			throw ex;
		}
		return false;
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO 
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		ArrayList<Prestamo> prestamoPorCliente = new ArrayList<Prestamo>();
		
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(listarPrestamosXCliente)) {
			
			statement.setInt(1, idCliente);
			rs = statement.executeQuery();
			
						
		}
		return prestamoPorCliente;
		
		
	}


	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		return null;
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosEnProceso() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		return null;
	}

	@Override
	public int contarPrestamosAprobados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		return 0;
	}

	@Override
	public int contarPrestamosRechazados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		return 0;
	}

	@Override
	public int contarPrestamosEnEvaluacion() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DESARROLLO DE METODO
		return 0;
	}

	@Override
	public BigDecimal sumarPrestamosAprobados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		return null;
	}

	@Override
	public BigDecimal sumarPrestamosRechazados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		return null;
	}

	@Override
	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		return null;
	}

}

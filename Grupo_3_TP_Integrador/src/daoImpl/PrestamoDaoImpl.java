package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import dominio.Cliente;
import dominio.EstadoPrestamo;
import dominio.Prestamo;
import dominio.Seguro;
import dominio.TipoCuenta;
import dominio.TipoSeguro;

public class PrestamoDaoImpl implements PrestamoDao{
	
	/// ATRIBUTOS PARA TRABAJAR LA CONEXION
	
	///private PreparedStatement st;
	private ResultSet rs;
	
	/// CONSULTAS BASE DE DATOS
	// CONSULTA DML:
	private static final String insert = "INSERT INTO prestamos (id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo, importe_cuota, cantidad_cuotas, id_estado_prestamo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, )";
	private static final String updateEstado = "UPDATE prestamos SET id_estado_prestamo = ? WHERE id_prestamo = ?";
	
	//CONSULTA DE LISTA
	private static final String obtenerPrestamoPorId ="Select P.id_cliente,P.id_cuenta, P.fecha_alta_prestamo, P.importe_prestamo, P.meses_plazo, P.importe_cuota, P.cantidad_cuotas, P.id_estado_prestamo, C.nombre, C.apellido from prestamos as P INNER JOIN clientes as C on P.id_cliente = C.id_cliente WHERE P.id_prestamo = ? ";
	private static final String listarPrestamosXCliente = "Select P.id_prestamo, P.id_cliente,P.id_cuenta, P.fecha_alta_prestamo, P.importe_prestamo, P.meses_plazo, P.importe_cuota, P.cantidad_cuota, P.id_estado_prestamo, C.nombre, C.apellido, Ep.estado_prestamo from prestamos as P INNER JOIN clientes as C on P.id_cliente = C.id_cliente INNER JOIN estados_prestamos as EP on P.id_estado_prestamo = EP.id_estado_prestamo WHERE C.id_cliente = ?";
	private static final String listarTodosLosPrestamos = "Select id_prestamo,id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo, importe_cuota, cantidad_cuotas, id_estado_prestamo from prestamos ";
	private static final String listarTodosLosPrestamosAprobados = "SELECT id_prestamo,id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo,importe_cuota, cantidad_cuotas, id_estado_prestamo from prestamos where id_estado_prestamo = 2";
	private static final String listarTodosLosPrestamosRechazados = "SELECT id_prestamo, id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo,importe_cuota, cantidad_cuotas, id_estado_prestamo from prestamos where id_estado_prestamo = 3";
	private static final String listarTodosLosPrestamosEnEvaluacion = "SELECT id_prestamo, id_cliente, id_cuenta, fecha_alta_prestamo, importe_prestamo, meses_plazo,importe_cuota, cantidad_cuotas, id_estado_prestamo from prestamos where id_estado_prestamo = 1";
	
	//CONSULTAS PARA INFORMES O REPORTES
	private static final String contarAprobados = "SELECT CASE WHEN COUNT(*) IS NULL THEN 0  ELSE COUNT(*) END AS cantidad FROM prestamos WHERE id_estado_prestamo = 2";
	private static final String contarRechazados = "SELECT CASE WHEN COUNT(*) IS NULL THEN 0  ELSE COUNT(*) END AS cantidad FROM prestamos WHERE id_estado_prestamo = 3";
	private static final String contarPendiente = "SELECT CASE WHEN COUNT(*) IS NULL THEN 0  ELSE COUNT(*) END AS cantidad FROM prestamos WHERE id_estado_prestamo = 1";
	private static final String sumarValorAprobados = "SELECT CASE WHEN sum(importe_prestamo) IS NULL THEN 0 ELSE sum(importe_prestamo) END AS suma FROM prestamos WHERE id_estado_prestamo = 2";
	private static final String sumarValorRechazados = "SELECT CASE WHEN sum(importe_prestamo) IS NULL THEN 0 ELSE sum(importe_prestamo) END AS suma FROM prestamos WHERE id_estado_prestamo = 3";
	private static final String sumarValorEvaluacion = "SELECT CASE WHEN sum(importe_prestamo) IS NULL THEN 0 ELSE sum(importe_prestamo) END AS suma FROM prestamos WHERE id_estado_prestamo = 1";
	
	
	//-----------------------METODOS DML ----------------------------//
	
	@Override
	public boolean insert(Prestamo prestamo) throws SQLException {
		
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO (ME FALTA CLIENTE Y CUENTA)		
		
		try (Connection conexion = Conexion.getConnection();
			 PreparedStatement statement = conexion.prepareStatement(insert)) {
			
			statement.setInt(1, prestamo.getCliente().getIdCliente()); 
			statement.setInt(2, prestamo.getCuenta().getId()); 
			statement.setDate(3,prestamo.getFechaAltaPrestamo()); 
			statement.setBigDecimal(4, prestamo.getImportePrestamo()); 
			statement.setInt(5, prestamo.getMesesPlazo()); 
			statement.setBigDecimal(6, prestamo.getImporteMensual());
			statement.setInt(7, prestamo.getCuotas()); 
			statement.setInt(8, prestamo.getEstadoValidacion().getId()); 
			
			int filasAfectadas = statement.executeUpdate();
			if(filasAfectadas >0) {
				return true;
			}
			
		}		
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}		
		
		return false;
	}

	
	
	@Override
	public boolean updateEstado(int idPrestamo, int estadoPrestamo) throws SQLException {
		
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
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

	
	//---------------------------METODOS PARA LISTAR-----------------------------//
	
	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DECLARACION DE VARIABLES
		Prestamo prestamo = new Prestamo();
		
		//DESARROLLO DE METODO 
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(obtenerPrestamoPorId)) {
			
			statement.setInt(1, idPrestamo);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				
				prestamo = getPrestamo(rs);
				
			}
		
			return prestamo;		
		}
		
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DECLARACION DE VARIABLES
		ArrayList<Prestamo> prestamoPorCliente = new ArrayList<Prestamo>();
		
		//DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(listarPrestamosXCliente)) {
			
			statement.setInt(1, idCliente);
			rs = statement.executeQuery();
			
			while(rs.next())
			{
				prestamoPorCliente.add(getPrestamo(rs));
			}
			
			return prestamoPorCliente;
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
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//DECLARACION DE VARIABLES
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<Prestamo>();
				
		//DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
			 PreparedStatement statement = conexion.prepareStatement(listarTodosLosPrestamos)) {
					
			rs = statement.executeQuery();
					
				while(rs.next())
				{
						todosLosPrestamos.add(getPrestamo(rs));
				}
					
					return todosLosPrestamos;
				}				
				
		catch (SQLException ex)
		{
			throw ex;
		}
				
		catch (Exception ex)
		{
			throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DECLARACION DE VARIABLES
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<Prestamo>();
		
		//DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
			 PreparedStatement statement = conexion.prepareStatement(listarTodosLosPrestamosAprobados)) {
							
			rs = statement.executeQuery();
							
				while(rs.next())
				{
					todosLosPrestamos.add(getPrestamo(rs));
				}
							
					return todosLosPrestamos;
				}				
						
		catch (SQLException ex)
		{
			throw ex;
		}
						
		catch (Exception ex)
		{
		    throw ex;
		}
	}

	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DECLARACION DE VARIABLES
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<Prestamo>();
				
		//DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(listarTodosLosPrestamosRechazados)) {
									
			 rs = statement.executeQuery();
									
					while(rs.next())
					{
						todosLosPrestamos.add(getPrestamo(rs));
					}
									
						return todosLosPrestamos;
					}				
								
		catch (SQLException ex)
		{
			throw ex;
		}
								
		catch (Exception ex)
		{
		    throw ex;
		}
	}

	
	@Override
	public ArrayList<Prestamo> listarTodosLosPrestamosEnProceso() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//DECLARACION DE VARIABLES
		ArrayList<Prestamo> todosLosPrestamos = new ArrayList<Prestamo>();
						
		//DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(listarTodosLosPrestamosEnEvaluacion)) {
											
				rs = statement.executeQuery();
											
					while(rs.next())
					{
					  todosLosPrestamos.add(getPrestamo(rs));
					}
											
					  return todosLosPrestamos;
					}				
										
		catch (SQLException ex)
		{
			throw ex;
		}
										
		catch (Exception ex)
		{
			throw ex;
		}
		
	}

	
	//-------------------------------METODOS PARA REPORTES O INFO GRAL-----------------------------//
	
	@Override
	public int contarPrestamosAprobados() throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//DESARROLLO DE METODO

		try (Connection conexion = Conexion.getConnection();
			 PreparedStatement statement = conexion.prepareStatement(contarAprobados)) {
			
			 ResultSet rs = statement.executeQuery();
			 
			 if(rs.next()) {
				 return rs.getInt("cantidad");
			 }
	    }
		
		catch (SQLException ex) {
	        throw ex;
		}
				
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
		
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(contarRechazados)) {
				
				 ResultSet rs = statement.executeQuery();
				 
				 if(rs.next()) {
					 return rs.getInt("cantidad");
				 }
		    }
			
			catch (SQLException ex) {
		        throw ex;
			}
					
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
		
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(contarPendiente)) {
				
				 ResultSet rs = statement.executeQuery();
				 
				 if(rs.next()) {
					 return rs.getInt("cantidad");
				 }
		    }
			
			catch (SQLException ex) {
		        throw ex;
			}
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
		
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(sumarValorAprobados)) {
				
				 ResultSet rs = statement.executeQuery();
				 
				 if(rs.next()) {
					 return rs.getBigDecimal("suma");
				 }
		    }
			
			catch (SQLException ex) {
		        throw ex;
			}
		return BigDecimal.ZERO;
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
		
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(sumarValorRechazados)) {
				
				 ResultSet rs = statement.executeQuery();
				 
				 if(rs.next()) {
					 return rs.getBigDecimal("suma");
				 }
		    }
			
			catch (SQLException ex) {
		        throw ex;
			}
		
		return BigDecimal.ZERO;
		
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
		
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(sumarValorEvaluacion)) {
				
				 ResultSet rs = statement.executeQuery();
				 
				 if(rs.next()) {
					 return rs.getBigDecimal("suma");
				 }
		    }
			
			catch (SQLException ex) {
		        throw ex;
			}
		
		return BigDecimal.ZERO;
		
	}
	
	//METODO PARA MEJORAR LA CAPTURA DE INFO POR COMPOSICION
	
	private Prestamo getPrestamo(ResultSet rs ) {
		
		//DECLARO EL PRESTAMO
		Prestamo prestamo = new Prestamo();
		
		//DECLARO LOS OBJETOS QUE COMPONEN A PRESTAMO
		
		//OBJETO CLIENTE
		Cliente cliente = new Cliente();
		cliente.setIdCliente(rs.getInt("id_cliente"));
		cliente.setNombre(rs.getString("nombre"));
		cliente.setApellido(rs.getString("apellido"));
			
		
		//OBJETO CUENTA
		Cuenta cuenta = new Cuenta();
		cuenta.setId(rs.getInt("id_cuenta"));
		
				
		//OBJETO ESTADO PRESTAMO
		EstadoPrestamo estado = new EstadoPrestamo ();
		estado.setId(rs.getInt("id_estado_prestamo"));
		estado.setNombre(rs.getString("estado_prestamo"));
		
			
		//COMPOSICION
		
		prestamo.setId(rs.getInt("id_prestamo"));
		prestamo.setCliente(cliente);
		prestamo.setCuenta(cuenta);
		prestamo.setFechaAltaPrestamo(rs.getDate("fecha_alta_prestamo"));
		prestamo.setImportePrestamo(rs.getBigDecimal("importe_prestamo"));
		prestamo.setMesesPlazo(rs.getInt("meses_plazo"));
		prestamo.setImporteMensual(rs.getBigDecimal("importe_cuota"));
		prestamo.setCuotas(rs.getInt("cantidad_cuotas"));
		prestamo.setEstadoValidacion(estado);
		
				
		return prestamo;
	}

}

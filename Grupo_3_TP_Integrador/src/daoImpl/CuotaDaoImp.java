package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import dao.CuotaDao;
import dominio.Cuota;

public class CuotaDaoImp implements CuotaDao {
	
	//ATRIBUTOS
	//private PreparedStatement st;
	private ResultSet rs;
	
	
	//CONSULTAS SQL
	
	private static final String insert = "INSERT INTO cuotas (id_prestamo, numero_cuota, monto_pagado, fecha_pago, estado_pago) VALUES (?, ?, ?, ?, ?)";
	private static final String registrarPago = "UPDATE cuotas SET estado_pago = ? WHERE id_cuota= ?";
	
	private static final String obtenerCuotaPorId = "SELECT C.id_cuota, C.id_prestamo, C.numero_cuota, C.monto_pagado, C.fecha_pago, C.estado_pago, P.id_cliente, P.fecha_alta_prestamo, P.importe_prestamo  from cuotas as C INNER JOIN prestamos as P on C.id_prestamo = P.id_prestamo where C.id_cuota = ?";
	private static final String listarCuotasPorIdPrestamo = "SELECT C.id_cuota, C.id_prestamo, C.numero_cuota, C.monto_pagado, C.fecha_pago, C.estado_pago, P.fecha_alta_prestamo FROM cuotas as C INNER JOIN prestamos as P ON C.id_prestamo = P.id_prestamo WHERE P.id_prestamo = ?";
	private static final String listarCuotasPagadoPorIdPrestamo = "SELECT C.id_cuota, C.id_prestamo, C.numero_cuota, C.monto_pagado, C.fecha_pago, C.estado_pago, P.id_cuenta, P.id_cliente FROM cuotas as C INNER JOIN prestamos AS P ON  C.id_prestamo = P.id_prestamo WHERE C.id_prestamo = ? and C.estado_pago = 1";
	private static final String listarCuotasPendientePorIdPrestamo = "SELECT C.id_cuota, C.id_prestamo, C.numero_cuota, C.monto_pagado, C.fecha_pago, C.estado_pago, P.id_cuenta, P.id_cliente FROM cuotas as C INNER JOIN prestamos AS P ON  C.id_prestamo = P.id_prestamo WHERE C.id_prestamo = ? and C.estado_pago = 0";
	
	private static final String contarCuotasPagas = " SELECT CASE WHEN COUNT(*) IS NULL THEN 0 ELSE COUNT(*) END AS cuenta FROM cuotas AS C INNER JOIN prestamos AS P ON C.id_prestamo = P.id_prestamo WHERE C.id_Prestamo = ? AND C.estado_pago = 1";
	private static final String contarCuotasPendientes = " SELECT CASE WHEN COUNT(*) IS NULL THEN 0 ELSE COUNT(*) END AS cuenta FROM cuotas AS C INNER JOIN prestamos AS P ON C.id_prestamo = P.id_prestamo WHERE C.id_Prestamo = ? AND C.estado_pago = 0";
	private static final String contarCuotas = " SELECT CASE WHEN COUNT(*) IS NULL THEN 0 ELSE COUNT(*) END AS cuenta FROM cuotas AS C INNER JOIN prestamos AS P ON C.id_prestamo = P.id_prestamo WHERE P.id_prestamo= ? ";
	private static final String sumarCuotasPagas = "SELECT COALESCE(SUM(C.monto_pagado), 0) AS suma FROM cuotas AS C INNER JOIN prestamos AS P ON C.id_prestamo = P.id_prestamo WHERE C.id_prestamo = ? AND C.estado_pago = 1";
	private static final String sumarCuotasPendientes = "SELECT COALESCE(SUM(C.monto_pagado) , 0) AS suma FROM cuotas AS C INNER JOIN prestamos AS P ON C.id_prestamo = P.id_prestamo WHERE C.id_prestamo = ? AND C.estado_pago = 0";
	private static final String sumarCuotas = "SELECT COALESCE(SUM(P.importe_cuota), 0) AS suma FROM cuotas AS C INNER JOIN prestamos AS P ON C.id_prestamo = P.id_prestamo WHERE C.id_prestamo = ?";
			
	//------------------------------METODO DML-----------------------------//		

	@Override
	public boolean insert(Cuota cuota) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(insert)) {
			
			statement.setInt(1, cuota.getPrestamo().getId());
			statement.setInt(2, cuota.getNumeroCuota());
			statement.setBigDecimal(3, cuota.getMontoPagado());
			statement.setDate(4, cuota.getFechaPago());
			statement.setBoolean(5, cuota.getEstadoPago());
			
			int filasAfectadas = statement.executeUpdate();
			if(filasAfectadas>0) {
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
	public boolean registrarPago(int idCuota, int estado) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(registrarPago)) {

			statement.setInt(1, estado);
			statement.setInt(2, idCuota);

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
	
	
	
	//-----------------------------LISTAR CUOTAS--------------------------//
	
	@Override
	public Cuota obtenerCuotaPorId(int idCuota) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DECLARACION DE VARIABLE
		Cuota cuota = new Cuota ();
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(obtenerCuotaPorId)) {
			
			
			statement.setInt(1, idCuota);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				
				cuota = getCuota(rs);
			}
			
			return cuota;
			
		}
		
				
	}

	@Override
	public ArrayList<Cuota> listarCuotasPorIdPrestamo(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// DECLARACION DE VARIABLES
		ArrayList<Cuota> cuotaPorPrestamo = new ArrayList<Cuota>();
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(listarCuotasPorIdPrestamo)) {
			
			statement.setInt(1, idPrestamo);
			rs = statement.executeQuery();
			while (rs.next()) {
				cuotaPorPrestamo.add(getCuota(rs));
			}
			return cuotaPorPrestamo;
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
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// DECLARACION DE VARIABLES
				ArrayList<Cuota> cuotasPagas = new ArrayList<Cuota>();
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
			  PreparedStatement statement = conexion.prepareStatement(listarCuotasPagadoPorIdPrestamo)) {
					
				statement.setInt(1, idPrestamo);
				rs = statement.executeQuery();
				
				while (rs.next()) {
						cuotasPagas.add(getCuota(rs));
				}
					return cuotasPagas;
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
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DECLARACION DE VARIABLES
		ArrayList<Cuota> cuotasImpagas = new ArrayList<Cuota>();
				
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
			  PreparedStatement statement = conexion.prepareStatement(listarCuotasPendientePorIdPrestamo)) {
							
			  statement.setInt(1, idPrestamo);
			  rs = statement.executeQuery();
						
			  while (rs.next())
			  {
				cuotasImpagas.add(getCuota(rs));
			  }
				return cuotasImpagas;
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

	
	
	//----------------------------INFORMES----------------------------//
	
	@Override
	public int contarCuotasPagadas(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(contarCuotasPagas)) {
			
			statement.setInt(1, idPrestamo);
	        ResultSet rs = statement.executeQuery();
	        
	        	if (rs.next()) 
	        	{
	        		return rs.getInt("cuenta");
	        	}	        
	        
		}
	    catch (SQLException ex) {
	    		throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
			
		return 0;
	}

	
	@Override
	public int contarCuotasPendientes(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		
		try (Connection conexion = Conexion.getConnection();
			  PreparedStatement statement = conexion.prepareStatement(contarCuotasPendientes)) {
					
			  statement.setInt(1, idPrestamo);
			  ResultSet rs = statement.executeQuery();
			        
			  	if (rs.next()) 
			  	{
			       return rs.getInt("cuenta");
			  	}	        
			        
			}
			   
			catch (SQLException ex) 
			{
			    throw ex;
			}
			catch (Exception ex) 
			{
				throw ex;
			}
					
			
		return 0;
		
	}

	@Override
	public int contarCuotas(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(contarCuotas)) {
						
			 statement.setInt(1, idPrestamo);
			 ResultSet rs = statement.executeQuery();
				        
				if (rs.next()) 
				  	{
				       return rs.getInt("cuenta");
				  	}	        
				        
		}
				   
		catch (SQLException ex) 
		{
			throw ex;
		}
		catch (Exception ex) 
		{
			throw ex;
		}
		return 0;
	}

	@Override
	public BigDecimal sumarCuotasPagadas(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(sumarCuotasPagas)) {
						
			 statement.setInt(1, idPrestamo);
			 ResultSet rs = statement.executeQuery();
				        
				if (rs.next()) 
				  	{
				       return rs.getBigDecimal("suma");
				  	}	        
				        
		}
				   
		catch (SQLException ex) 
		{
			throw ex;
		}
		catch (Exception ex) 
		{
			throw ex;
		}
		return BigDecimal.ZERO;
		
	}

	@Override
	public BigDecimal sumarCuotasPendientes(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		try (Connection conexion = Conexion.getConnection();
				 PreparedStatement statement = conexion.prepareStatement(sumarCuotasPendientes)) {
						
			 statement.setInt(1, idPrestamo);
			 ResultSet rs = statement.executeQuery();
				        
				if (rs.next()) 
				  	{
				       return rs.getBigDecimal("suma");
				  	}	        
				        
		}
				   
		catch (SQLException ex) 
		{
			throw ex;
		}
		catch (Exception ex) 
		{
			throw ex;
		}
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal sumarCuotas(int idPrestamo) throws SQLException {
		// CONFIGURACION ESTANDAR PARA TRABAJAR CON JDBC
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// DESARROLLO DE METODO
		
		try (Connection conexion = Conexion.getConnection();
			  PreparedStatement statement = conexion.prepareStatement(sumarCuotas)) {
								
				statement.setInt(1, idPrestamo);
				ResultSet rs = statement.executeQuery();
						        
				if (rs.next()) 
				{
				    return rs.getBigDecimal("suma");
				}	        
						        
				}
						   
		catch (SQLException ex) 
		{
			throw ex;
		}
		catch (Exception ex) 
		{
			throw ex;
		}
		return BigDecimal.ZERO;
	}

	
	//METODO PARA MEJORAR LA CAPTURA DE INFO POR COMPOSICION
	
	private Cuota getCuota (ResultSet rs) {
		
		//DECLARO LA CUOTA
		Cuota cuota = new Cuota();
		Prestamo prestamo = new Prestamo ();
		
		
		try {
			
			//ASIGNO PRESTAMO
			prestamo.setId(rs.getInt("id_prestamo"));
			prestamo.setCliente().setId(rs.getInt("id_cliente"));
			prestamo.setFechaAltaPrestamo(rs.getDate("fecha_alta_prestamo"));
			prestamo.setImportePrestamo(rs.getBigDecimal("importe_prestamo"));
			
			//COMPOSICION CUOTA
			cuota.setId(rs.getInt("id_cuota"));
			cuota.setPrestamo(prestamo);
			cuota.setNumeroCuota(rs.getInt("numero_cuota"));
			cuota.setMontoPagado(rs.getBigDecimal("monto_pagado"));
			cuota.setFechaPago(rs.getDate("fecha_pago"));
			cuota.setEstadoPago(rs.getBoolean("estado_pago"));		
			
		}
		
		catch (SQLException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return cuota;
	}

}

package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MovimientoDao;
import dao.TipoMovimientoDao;
import dominio.Movimiento;
import dominio.TipoMovimiento;
import dominio.TipoUsuario;
import dominio.Usuario;

public class MovimientoDaoImpl implements MovimientoDao {

	@Override
	public void insert(Movimiento m) {
		 try{
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 String query = "Insert Into movimientos(id_cuenta, id_tipo_movimiento, fecha_movimiento, concepto, importe_movimiento) Values (?, ?, ?, ?, ?);";
		 
		 try(Connection conexion = Conexion.getConnection();
	         PreparedStatement statement = conexion.prepareStatement(query)){ 
			 //statement.setInt(1, m.getCuenta().getId());
			 statement.setInt(2, m.getTipo().getId());
			 statement.setDate(3, (Date)m.getFecha());
			 statement.setString(4, m.getConcepto());
			 statement.setBigDecimal(5, m.getMonto());
			 statement.executeUpdate();	 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	}

	@Override
	public void update(Movimiento m) {
		 try{
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 String query = "Update Movimientos Set id_cuenta = ?, id_tipo_movimiento = ?, fecha_mocimiento = ?, concepto = ?, importe_movimiento = ? wjere id_movimiento = ?;";
		 
		 try(Connection conexion = Conexion.getConnection();
	         PreparedStatement statement = conexion.prepareStatement(query)){ 
			 //statement.setInt(1, m.getCuenta().getId());
			 statement.setInt(2, m.getTipo().getId());
			 statement.setDate(3, (Date)m.getFecha());
			 statement.setString(4, m.getConcepto());
			 statement.setBigDecimal(5, m.getMonto());
			 statement.setInt(6, m.getId());
			 statement.executeUpdate();	 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	}

	@Override
	public void delete(int id) {
		
		 try{
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		
		//TODO La tabla Movimientos en la BD no tiene estado_movimiento
		
		/*
		String update = "UPDATE usuarios SET estado_usuario = 0 WHERE id_usuario = ?;";

        try (Connection conexion = Conexion.getConnection();
             PreparedStatement statement = conexion.prepareStatement(update)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {  
            e.printStackTrace();
        }
        */
	}

	@Override
	public Movimiento encontrarPorId(int id) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String query = "Select id_movimiento, id_cuenta, id_tipo_movimiento, fecha_movimiento, concepto, importe_movimiento From movimientos Where id_movimiento = ?;";
		 try (Connection conexion = Conexion.getConnection();
	             PreparedStatement statement = conexion.prepareStatement(query)) {

	            statement.setInt(1, id);

	            ResultSet result = statement.executeQuery();

	            if (result.next()) {
	            	//FALTA LA CLASE CUENTA PARA COMPLETAR ESTO! REVISAR QUE COINCIDAN NOMBRES DE CLASES Y MÉTODOS
	            	
	            	TipoMovimientoDaoImpl movDao = new TipoMovimientoDaoImpl();
	            	//CuentaDaoImpl cuentaDao = new CuentaDaoImpl();
	            	TipoMovimiento tipo = movDao.buscarPorId(result.getInt("id_tipo_movimiento"));
	            	//Cuenta cuenta = cuentaDao.buscarPorId(result.getInt("id_cuenta"))
	            	Movimiento mov = new Movimiento();
	            	mov.setConcepto(result.getString("concepto"));
	            	mov.setFecha(result.getDate("fecha_movimiento"));
	            	mov.setId(result.getInt("id_movimiento"));
	            	mov.setMonto(result.getBigDecimal("importe_movimiento"));
	            	mov.setTipo(tipo);
	            	//mov.setCuenta(cuenta);
	            	return mov;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return null;
		 
	}

	@Override
	public ArrayList<Movimiento> ListarMovimientosPorCuenta(int idCuenta) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String query = "Select id_movimiento, id_cuenta, id_tipo_movimiento, fecha_movimiento, concepto, "
				+ "importe_movimiento From movimientos Where id_cuenta = ?;";
		ArrayList<Movimiento> listado = new ArrayList<>();
		ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos(); 
		//CuentaDaoImpl cuentaDao = new CuentaDaoImpl();
		//Cuenta cuenta = cuentaDao.buscarPorId(idCuenta);
		
		 TipoMovimiento t = null;
		 try(Connection conexion = Conexion.getConnection();
		         PreparedStatement statement = conexion.prepareStatement(query)){ 
				 
			 statement.setInt(1, idCuenta);
			 ResultSet resultado = statement.executeQuery();
			 
			 while(resultado.next()) {
				 for(TipoMovimiento tipoM : tipos) {
					 if(tipoM.getId() == resultado.getInt("id_tipo_movimiento")) {
						 t = new TipoMovimiento(tipoM.getId(), tipoM.getNombre());
					 }
				 }
				/* if(resultado.getInt("id_cuenta") == cuenta.getId()) {
					 
					 
					 Movimiento mov = new Movimiento(); 
					 mov.setConcepto(resultado.getString("concepto"));
					 mov.setFecha(resultado.getDate("fecha_movimiento"));
					 mov.setId(resultado.getInt("id_movimiento"));
					 mov.setMonto(resultado.getBigDecimal("importe_movimiento"));
					 //FALTA EL ATRIBUTO CUENTA!
					 listado.add(mov);
				 }
				*/
				 
			 }
			 return listado;
			 }catch(SQLException e) {
				 e.printStackTrace();
				 
			 }
		 
		return listado;
		
	}

	@Override
	public ArrayList<Movimiento> ListarTodo() {
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 //Tipo movimiento tiene int id y string nombre.
		 String query = "Select id_movimiento, id_cuenta, id_tipo_movimiento, fecha_movimiento, concepto, importe_movimiento From movimientos Where estado_movimiento = 1;";
		 ArrayList<Movimiento> listado = new ArrayList<>();
		 ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos(); 
		 //ArrayList de cuenta cuando la clase exista, ajustar segun implementacion
		 //ArrayList<Cuenta> cuentas = new CuentaDaoImpl():buscarTodos();
		 TipoMovimiento t = null;
		 //Cuenta c = null;
		 try(Connection conexion = Conexion.getConnection();
		         PreparedStatement statement = conexion.prepareStatement(query)){ 
				 
			 ResultSet resultado = statement.executeQuery();
			 while(resultado.next()) {
				 for(TipoMovimiento tipoM : tipos) {
					 if(tipoM.getId() == resultado.getInt("id_tipo_movimiento")) {
						 t = new TipoMovimiento(tipoM.getId(), tipoM.getNombre());
					 }
				 }
				 /*
				  //Buscar cuenta asociada al movimiento, cuando la clase cuenta este hecha.
				 for(Cuenta cuenta : Cuentas) {
					 if(cuenta.getId() == resultado.getInt("id_cuenta")) {
						 cuenta = new Cuenta(//PARAMETROS PARA EL CONSTRUCTOR);
					 }
				 }
				 */
				 Movimiento mov = new Movimiento(); 
				 mov.setConcepto(resultado.getString("concepto"));
				 mov.setFecha(resultado.getDate("fecha_movimiento"));
				 mov.setId(resultado.getInt("id_movimiento"));
				 mov.setMonto(resultado.getBigDecimal("importe_movimiento"));
				 //FALTA EL ATRIBUTO CUENTA!
				 listado.add(mov);
				 
			 }
			 return listado;
			 }catch(SQLException e) {
				 e.printStackTrace();
				 
			 }
		 
		return listado;
	}

}

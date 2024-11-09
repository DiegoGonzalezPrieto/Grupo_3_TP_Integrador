package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MovimientoDao;
import dao.TipoMovimientoDao;
import dominio.Movimiento;
import dominio.TipoMovimiento;

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
		 
		 String query = "Select id_movimiento, id_cuenta, id_tipo_movimiento, fecha_movimiento, concepto, importe_movimiento From movimientos Where estado_movimiento = 1;";
		 ArrayList<Movimiento> listado = new ArrayList<>();
		 ArrayList<TipoMovimiento> tipos = new TipoMovimientoDaoImpl().buscarTodos(); 
		 
		 
		 return null;
	}

	@Override
	public ArrayList<Movimiento> ListarMovimientosPorCuenta(int idCuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movimiento> ListarTodo() {
		// TODO Auto-generated method stub
		return null;
	}

}

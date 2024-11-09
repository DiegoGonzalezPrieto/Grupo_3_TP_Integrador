package negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Cuota;
import dominio.Prestamo;

public interface CuotaNegocio {
	
	//CONSULTAS INSERT (SENTENCIAS DML)
	public boolean agregarCuotas (Prestamo prestamo) throws SQLException;
	public boolean registrarPago(int idCuenta, Cuota cuota) throws Exception, SQLException;
	
	
	//CONSULTAS PARA OBTENER DIFERENTES TIPOS DE LISTA
	public Cuota obtenerCuotaPorId (int idCuota) throws SQLException;
	public ArrayList<Cuota> listarCuotasPorPrestamo (int idPrestamo) throws SQLException;
	public ArrayList<Cuota> listarCuotasPagadas(int idPrestamo)throws SQLException;
	public ArrayList<Cuota> listarCuotasPendientes(int idPrestamo)throws SQLException;
	
	//CONSULTAS PARA CALCULAR CUOTAS SEGUN ESTADO Y GRAL
	public int contarCuotasPagadas(int idPrestamo)throws SQLException;
	public int contarCuotasPendientes(int idPrestamo)throws SQLException;
	public int contarCuotas(int idPrestamo)throws SQLException;
	
	//CONSULTAS PARA CALCULAR IMPORTES
	public BigDecimal sumarCuotasPagadas(int idPrestamo)throws SQLException;
	public BigDecimal sumarCuotasPendientes(int idPrestamo)throws SQLException;
	public BigDecimal sumarCuotas(int idPrestamo)throws SQLException;
	

}

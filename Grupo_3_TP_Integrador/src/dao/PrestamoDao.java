package dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.Prestamo;

public interface PrestamoDao {
	
	//INSERT Y UPDATE (SENTENCIAS DML)
	public boolean insert (Prestamo prestamo)throws SQLException;
	public boolean updateEstado (int idPrestamo, int estadoPrestamo)throws SQLException;
	
	//OBTENER DIFERENTES TIPOS DE LISTA
	public Prestamo obtenerPrestamoPorId (int idPrestamo) throws SQLException;	
	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException;
	public ArrayList<Prestamo> listarTodosLosPrestamosEnProceso() throws SQLException;
	
	//CALCULAR PRESTAMOS SEGUN ESTADO (INFORME O REPORTE)
	public int contarPrestamosAprobados() throws SQLException;
	public int contarPrestamosRechazados() throws SQLException;
	public int contarPrestamosEnEvaluacion() throws SQLException;
	
	//CALCULAR IMPORTES SEGUN ESTADO (INFORME O REPORTE)
	public BigDecimal sumarPrestamosAprobados()throws SQLException;
	public BigDecimal sumarPrestamosRechazados()throws SQLException;
	public BigDecimal sumarPrestamosEnEvaluacion()throws SQLException;

}

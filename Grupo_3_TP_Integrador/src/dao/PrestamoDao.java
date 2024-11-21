package dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dominio.Prestamo;

public interface PrestamoDao {

	// CONSULTAS INSERT Y UPDATE (SENTENCIAS DML)
	public boolean insert(Prestamo prestamo) throws SQLException;

	public boolean updateEstado(int idPrestamo, int estadoPrestamo) throws SQLException;

	// CONSULTAS PARA OBTENER DIFERENTES TIPOS DE LISTA
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException;

	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamosEnProceso() throws SQLException;

	// CONSULTAS PARA CALCULAR PRESTAMOS SEGUN ESTADO (INFORME O REPORTE)
	public int contarPrestamosAprobados() throws SQLException;

	public int contarPrestamosRechazados() throws SQLException;

	public int contarPrestamosEnEvaluacion() throws SQLException;

	// CONSULTAS PARA CALCULAR IMPORTES SEGUN ESTADO (INFORME O REPORTE)
	public BigDecimal sumarPrestamosAprobados() throws SQLException;

	public BigDecimal sumarPrestamosRechazados() throws SQLException;

	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException;

	// PARA INFORME, CON FECHAS
	public int contarPrestamosAprobados(Date fechaInicio, Date fechaFin) throws SQLException;

	public int contarPrestamosRechazados(Date fechaInicio, Date fechaFin) throws SQLException;

	public int contarPrestamosEnEvaluacion(Date fechaInicio, Date fechaFin) throws SQLException;

	public int contarPrestamosPagados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosAprobados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosRechazados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosEnEvaluacion(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosPagados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal getPromedioPrestamos(Date fechaInicio, Date fechaFin) throws SQLException;

}

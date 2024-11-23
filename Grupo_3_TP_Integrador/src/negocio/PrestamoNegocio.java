package negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.Prestamo;

public interface PrestamoNegocio {

	// DML
	public boolean crearPrestamo(Prestamo prestamo) throws SQLException;

	public boolean actualizarEstadoSolicitud(int idPrestamo, int estadoSolicitud) throws SQLException;

	// METODOS LISTAR
	public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException;

	public ArrayList<Prestamo> listarPrestamosXCliente(int idCliente) throws SQLException;

	public List<Prestamo> listarPrestamosPendientesXCliente(int idCliente) throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamos() throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamosAprobados() throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamosRechazados() throws SQLException;

	public ArrayList<Prestamo> listarTodosLosPrestamosEnProceso() throws SQLException;

	// METODOS PARA INFORME
	public int contarPrestamosAprobados() throws SQLException;

	public int contarPrestamosRechazados() throws SQLException;

	public int contarPrestamosEnEvaluacion() throws SQLException;

	public BigDecimal sumarPrestamosAprobados() throws SQLException;

	public BigDecimal sumarPrestamosRechazados() throws SQLException;

	public BigDecimal sumarPrestamosEnEvaluacion() throws SQLException;

	public int contarPrestamosAprobados(Date fechaInicio, Date fechaFin) throws SQLException;

	public int contarPrestamosRechazados(Date fechaInicio, Date fechaFin) throws SQLException;

	public int contarPrestamosEnEvaluacion(Date fechaInicio, Date fechaFin) throws SQLException;

	public int contarPrestamosPagados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosAprobados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosRechazados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosEnEvaluacion(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal sumarPrestamosPagados(Date fechaInicio, Date fechaFin) throws SQLException;

	public BigDecimal getPromedioPrestamos(Date fechaInicio, Date fechaFin) throws SQLException;

	public Boolean aprobarPrestamo(Prestamo prestamo) throws SQLException;

}

package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.ReporteGuardado;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class GenerarReporteServlet
 */
@WebServlet("/GenerarReporteServlet")
public class GenerarReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerarReporteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("reportes") == null) {
			session.setAttribute("reportes", new ArrayList<ReporteGuardado>());
		}

		request.getRequestDispatcher("Reportes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// crear atributo reportes si no existe
		HttpSession session = request.getSession();
		if (session.getAttribute("reportes") == null) {
			session.setAttribute("reportes", new ArrayList<ReporteGuardado>());
		}

		String tipoReporte = request.getParameter("tipoReporte");
		String reporte = "";
		String nombreReporte = "reporte";
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		if (tipoReporte.equals("clientes")) {

			reporte = generarReporteClientes();
			nombreReporte = "reporte_clientes_" + fecha + ".txt";

		} else if (tipoReporte.equals("cuentas")) {

			String[] fragmentosFechaInicio = request.getParameter("fechaInicio").split("-");
			String[] fragmentosFechaFin = request.getParameter("fechaFin").split("-");
			if (fragmentosFechaInicio.length != 3 || fragmentosFechaFin.length != 3)
				return;
			Date fechaInicio = new Date(Integer.parseInt(fragmentosFechaInicio[0]) - 1900,
					Integer.parseInt(fragmentosFechaInicio[1]) - 1, Integer.parseInt(fragmentosFechaInicio[2]));
			Date fechaFin = new Date(Integer.parseInt(fragmentosFechaFin[0]) - 1900,
					Integer.parseInt(fragmentosFechaFin[1]) - 1, Integer.parseInt(fragmentosFechaFin[2]));
			reporte = generarReporteCuentas(fechaInicio, fechaFin);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String periodo = sdf.format(fechaInicio) + " a " + sdf.format(fechaFin);
			nombreReporte = "reporte_cuentas_" + periodo + ".txt";

		} else if (tipoReporte.equals("prestamos")) {

			String[] fragmentosFechaInicio = request.getParameter("fechaInicio").split("-");
			String[] fragmentosFechaFin = request.getParameter("fechaFin").split("-");
			if (fragmentosFechaInicio.length != 3 || fragmentosFechaFin.length != 3)
				return;
			Date fechaInicio = new Date(Integer.parseInt(fragmentosFechaInicio[0]) - 1900,
					Integer.parseInt(fragmentosFechaInicio[1]) - 1, Integer.parseInt(fragmentosFechaInicio[2]));
			Date fechaFin = new Date(Integer.parseInt(fragmentosFechaFin[0]) - 1900,
					Integer.parseInt(fragmentosFechaFin[1]) - 1, Integer.parseInt(fragmentosFechaFin[2]));
			reporte = generarReportePrestamos(fechaInicio, fechaFin);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String periodo = sdf.format(fechaInicio) + " a " + sdf.format(fechaFin);
			nombreReporte = "reporte_prestamos_" + periodo + ".txt";
		}

		if (!reporte.trim().isEmpty()) {
			request = guardarReporteEnSesion(request, reporte, nombreReporte, tipoReporte);
		}

		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename=" + nombreReporte);

		try (OutputStream out = response.getOutputStream()) {
			out.write(reporte.getBytes());
		}
	}

	private String generarReporteCuentas(Date fechaInicio, Date fechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String periodo = sdf.format(fechaInicio) + "_" + sdf.format(fechaFin);
		String reporte = "Reporte de Cuentas " + periodo + "\n";
		reporte += "========================================\n\n";

		CuentaNegocio n = new CuentaNegocioImpl();
		MovimientoNegocio nm = new MovimientoNegocioImpl();
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.ITALIAN);
		df.setMaximumFractionDigits(2);

		// Cuentas

		reporte += "- Cantidad de Cuentas creadas durante el período: "
				+ n.obtenerReporteCantidadDeCuentas(fechaInicio, fechaFin) + "\n\n";
		reporte += "- Suma de saldos de Cuentas creadas durante el período: $"
				+ df.format(n.obtenerReporteSumaDeSaldos(fechaInicio, fechaFin)) + "\n";
		reporte += "- Promedio de saldos de Cuentas creadas durante el período: $"
				+ df.format(n.obtenerReporteSaldoPromedio(fechaInicio, fechaFin)) + "\n\n";

		// Movimientos

		int cantMovimientos = nm.obtenerCantidadMovimientos(fechaInicio, fechaFin);
		int cantidadAltasCuenta = nm.obtenerCantidadAltasCuenta(fechaInicio, fechaFin);
		int cantidadTransferencias = nm.obtenerCantidadTransferencias(fechaInicio, fechaFin);
		int cantidadAltasPrestamo = nm.obtenerCantidadAltasPrestamo(fechaInicio, fechaFin);
		int cantidadPagosPrestamo = nm.obtenerCantidadPagosPrestamo(fechaInicio, fechaFin);

		BigDecimal sumaTransferencias = nm.obtenerSumaTransferencias(fechaInicio, fechaFin);
		BigDecimal promedioTransferencias = nm.obtenerPromedioTransferencias(fechaInicio, fechaFin);

		reporte += "- Cantidad de Movimientos durante el período: " + cantMovimientos + "\n";
		reporte += "- Cantidad de Altas de Cuenta durante el período: " + cantidadAltasCuenta + "\n";
		reporte += "- Cantidad de Transferencias realizadas durante el período: " + cantidadTransferencias + " - "
				+ cantidadTransferencias * 2 + " movimientos" + "\n";
		reporte += "- Cantidad de Altas de Préstamo durante el período: " + cantidadAltasPrestamo + "\n";
		reporte += "- Cantidad de Pagos de Préstamo durante el período: " + cantidadPagosPrestamo + "\n\n";

		reporte += "- Monto transferido durante el período: $" + df.format(sumaTransferencias) + "\n";
		reporte += "- Promedio de montos transferidos durante el período: $" + df.format(promedioTransferencias)
				+ "\n\n";

		return reporte;
	}

	private String generarReportePrestamos(Date fechaInicio, Date fechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String periodo = sdf.format(fechaInicio) + "_" + sdf.format(fechaFin);
		String reporte = "Reporte de Préstamos " + periodo + "\n";
		reporte += "==========================================\n\n";

		PrestamoNegocio n = new PrestamoNegocioImpl();
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.ITALIAN);
		df.setMaximumFractionDigits(2);

		try {

			// TODO : estadísticas de cuotas

			int cantAprobados = n.contarPrestamosAprobados(fechaInicio, fechaFin);
			int cantRechazados = n.contarPrestamosRechazados(fechaInicio, fechaFin);
			int cantPendientes = n.contarPrestamosEnEvaluacion(fechaInicio, fechaFin);
			BigDecimal sumaAprobados = n.sumarPrestamosAprobados(fechaInicio, fechaFin);
			BigDecimal sumaRechazados = n.sumarPrestamosRechazados(fechaInicio, fechaFin);
			BigDecimal sumaPendientes = n.sumarPrestamosEnEvaluacion(fechaInicio, fechaFin);
			BigDecimal promedio = n.getPromedioPrestamos(fechaInicio, fechaFin);

			reporte += "- Cantidad de Prestamos aprobados que fueron creados durante el período: " + cantAprobados
					+ "\n";
			reporte += "- Cantidad de Prestamos rechazados que fueron creados durante el período: " + cantRechazados
					+ "\n";
			reporte += "- Cantidad de Prestamos en evaluacion que fueron creados durante el período: " + cantPendientes
					+ "\n\n";

			reporte += "- Monto total de Prestamos aprobados que fueron creados durante el período: $"
					+ df.format(sumaAprobados) + "\n";
			reporte += "- Monto total de Prestamos rechazados que fueron creados durante el período: $"
					+ df.format(sumaRechazados) + "\n";
			reporte += "- Monto total de Prestamos en evaluación que fueron creados durante el período: $"
					+ df.format(sumaPendientes) + "\n\n";

			reporte += "- Monto promedio de Prestamos que fueron creados durante el período: $" + df.format(promedio)
					+ "\n\n";

		} catch (Exception e) {
			e.printStackTrace();
			reporte = "Error al generar el reporte.";
		}

		return reporte;
	}

	private String generarReporteClientes() {

		String reporte = "Reporte de Clientes\n";
		reporte += "===================\n\n";

		ClienteNegocio nc = new ClienteNegocioImpl();

		// Cantidades

		int cantidadTotal = nc.contarTodos();
		int cantidadActivos = nc.contarActivos();
		int cantidadInactivos = nc.contarInactivos();

		// Edad

		float edadPromedio = nc.obtenerEdadPromedioActivos();

		// Provincia
		String cantidadPorProvincia = "";
		HashMap<String, Integer> clientesPorProvincia = nc.obtenerClientesPorProvincia();
		for (Entry<String, Integer> provCantidad : clientesPorProvincia.entrySet()) {
			String provincia = provCantidad.getKey();
			Integer cantidad = provCantidad.getValue();
			cantidadPorProvincia += "\t* " + provincia + ": " + cantidad.toString() + "\n";
		}

		// Nacionalidad
		String cantidadPorNacionalidad = "";
		HashMap<String, Integer> clientesPorNacionalidad = nc.obtenerClientesPorNacionalidad();
		for (Entry<String, Integer> nacCantidad : clientesPorNacionalidad.entrySet()) {
			String nacionalidad = nacCantidad.getKey();
			Integer cantidad = nacCantidad.getValue();
			cantidadPorNacionalidad += "\t* " + nacionalidad + ": " + cantidad.toString() + "\n";
		}

		reporte += "- Cantidad de Clientes (total): " + cantidadTotal + "\n";
		reporte += "- Cantidad de Clientes Activos: " + cantidadActivos + "\n";
		reporte += "- Cantidad de Clientes Inactivos: " + cantidadInactivos + "\n\n";

		reporte += "- Porcentaje de Clientes Activos: " + (cantidadActivos * 100.00 / cantidadTotal) + "%\n";
		reporte += "- Porcentaje de Clientes Inactivos: " + (cantidadInactivos * 100.00 / cantidadTotal) + "%\n\n";

		reporte += "- Edad promedio de Clientes Activos: " + (int) edadPromedio + "\n\n";

		reporte += "- Clientes Activos por provincia: \n\n" + cantidadPorProvincia + "\n";

		reporte += "- Nacionalidades de Clientes Activos:\n\n" + cantidadPorNacionalidad;

		return reporte;
	}

	HttpServletRequest guardarReporteEnSesion(HttpServletRequest request, String reporte, String nombre, String tipo) {
		HttpSession session = request.getSession();

		List<ReporteGuardado> reportes = (List<ReporteGuardado>) session.getAttribute("reportes");

		ReporteGuardado reporteGuardado = new ReporteGuardado();
		reporteGuardado.setContenido(reporte);
		reporteGuardado.setId(reportes.size() + 1);
		reporteGuardado.setNombre(nombre);
		if (tipo.equals("clientes")) {
			reporteGuardado.setTipo("Reporte de Clientes");
			reporteGuardado.setFechas(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		} else {
			String tipoReporte = tipo.equals("prestamos") ? "Reporte de Préstamos" : "Reporte de Cuentas";
			reporteGuardado.setTipo(tipoReporte);
			reporteGuardado.setFechas(request.getParameter("fechaInicio") + " a " + request.getParameter("fechaFin"));
		}

		reportes.add(reporteGuardado);
		session.setAttribute("reportes", reportes);
		return request;
	}

}

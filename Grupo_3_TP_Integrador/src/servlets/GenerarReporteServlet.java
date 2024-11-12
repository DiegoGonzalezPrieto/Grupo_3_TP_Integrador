package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.Nacionalidad;
import dominio.Provincia;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tipoReporte = request.getParameter("tipoReporte");
		String reporte = "";
		String nombreReporte = "reporte";
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (tipoReporte.equals("clientes")) {
			reporte = generarReporteClientes();
			nombreReporte = "reporte_clientes_" + fecha + ".txt";
		}

		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename=" + nombreReporte);
		try (OutputStream out = response.getOutputStream()) {
			out.write(reporte.getBytes());
		}
	}

	private String generarReporteClientes() {

		String reporte = "Reporte de Clientes\n";
		reporte += "===================\n\n";

		ClienteNegocio nc = new ClienteNegocioImpl();
		ArrayList<Cliente> clientesActivos = nc.listarActivos();
		ArrayList<Cliente> clientesTodos = nc.listarTodos();

		// Cantidades

		int cantidadTotal = clientesTodos.size();
		int cantidadActivos = clientesActivos.size();
		int cantidadInactivos = cantidadTotal - cantidadActivos;

		// Edad

		int sumaEdades = 0;
		for (Cliente cliente : clientesActivos) {
			sumaEdades += calcularEdad(cliente.getFechaNacimiento());
		}
		int edadPromedio = sumaEdades / cantidadActivos;

		// Provincia
		ArrayList<Provincia> provincias = new ProvinciaNegocioImpl().buscarTodos();
		Provincia provinciaMax = new Provincia(0, "Provincia");
		int cantidadProvinciaMax = 0;
		for (Provincia provincia : provincias) {
			int totalProvincia = 0;
			for (Cliente cli : clientesActivos) {
				if (cli.getProvincia().getId() == provincia.getId()) {
					totalProvincia++;
				}
			}
			if (totalProvincia > cantidadProvinciaMax) {
				cantidadProvinciaMax = totalProvincia;
				provinciaMax = provincia;
			}
		}

		// Nacionalidad
		ArrayList<Nacionalidad> nacionalidades = new NacionalidadNegocioImpl().buscarTodos();

		String porcentajesNacionalidad = "";
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		for (Nacionalidad nacionalidad : nacionalidades) {
			int contador = 0;
			for (Cliente cli : clientesActivos) {
				if (cli.getNacionalidad().getId() == nacionalidad.getId()) {
					contador++;
				}
			}
			if (contador > 0) {
				porcentajesNacionalidad += "\t* " + nacionalidad.getNombre() + " - "
						+ df.format(((float)contador * 100.00 / cantidadActivos) )+ "%\n";
			}
		}

		reporte += "- Cantidad de Clientes (total): " + cantidadTotal + "\n";
		reporte += "- Cantidad de Clientes Activos: " + cantidadActivos + "\n";
		reporte += "- Cantidad de Clientes Inactivos: " + cantidadInactivos + "\n\n";

		reporte += "- Porcentaje de Clientes Activos: " + (cantidadActivos * 100 / cantidadTotal) + "%\n";
		reporte += "- Porcentaje de Clientes Inactivos: " + (cantidadInactivos * 100 / cantidadTotal) + "%\n\n";

		reporte += "- Edad promedio de Clientes: " + edadPromedio + "\n\n";

		reporte += "- Provincia con mayor cantidad de Clientes Activos: " + provinciaMax.getNombre() + " - "
				+ cantidadProvinciaMax + " cliente(s)." + "\n\n";

		reporte += "- Nacionalidades de Clientes Activos:\n\n" + porcentajesNacionalidad;

		return reporte;
	}

	private int calcularEdad(java.sql.Date fechaNacimiento) {
		Date hoy = new Date();
		long diferenciaMilisegundos = Math.abs(hoy.getTime() - fechaNacimiento.getTime());
		long diff = TimeUnit.DAYS.convert(diferenciaMilisegundos, TimeUnit.MILLISECONDS);

		return (int) diff / 365;
	}

}

package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.ReporteGuardado;

/**
 * Servlet implementation class DescargarReporteServlet
 */
@WebServlet("/DescargarReporteServlet")
public class DescargarReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DescargarReporteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ReporteGuardado reporte = new ReporteGuardado();

		if (request.getParameter("id") == null) {
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));

		ArrayList<ReporteGuardado> reportes = (ArrayList<ReporteGuardado>) request.getSession()
				.getAttribute("reportes");
		if (reportes == null || reportes.size() == 0) {
			return;
		}

		boolean existe = false;
		for (ReporteGuardado reporteGuardado : reportes) {
			if (reporteGuardado.getId() == id) {
				reporte = reporteGuardado;
				existe = true;
				break;
			}
		}

		if (!existe) {
			return;
		}

		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename=" + reporte.getNombre());

		try (OutputStream out = response.getOutputStream()) {
			out.write(reporte.getContenido().getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

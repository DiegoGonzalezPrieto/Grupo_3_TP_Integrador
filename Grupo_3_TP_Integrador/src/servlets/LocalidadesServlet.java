package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Localidad;
import dominio.Provincia;
import negocio.LocalidadNegocio;
import negocioImpl.LocalidadNegocioImpl;

/**
 * Servlet implementation class LocalidadesServlet
 */
@WebServlet("/LocalidadesServlet")
public class LocalidadesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	LocalidadNegocio locNeg = new LocalidadNegocioImpl();

	public LocalidadesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("id") == null)
			return;

		// en resultado se arma un string con formato JSON
		// luego se recibe en el front y se procesa para
		// completar el select de localidades
		String resultado = "{";

		try {
			int idProvincia = Integer.parseInt(request.getParameter("id"));
			ArrayList<Localidad> localidades = locNeg.buscarTodos();

			for (Localidad localidad : localidades) {
				if (localidad.getProvincia().getId() == idProvincia) {
					if (resultado.length() == 1)
						resultado += "\"" + localidad.getNombre() + "\":" + localidad.getId() + "";
					else
						resultado += ",\"" + localidad.getNombre() + "\":" + localidad.getId() + "";
				}
			}
			resultado += "}";

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
			response.getWriter().write(resultado);
		} catch (Exception e) {
			e.printStackTrace();
			return;
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

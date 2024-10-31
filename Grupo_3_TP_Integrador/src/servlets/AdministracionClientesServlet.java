package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDaoImpl;
import dominio.Cliente;

/**
 * Servlet implementation class AdministracionClientesServlet
 */
@WebServlet("/AdministracionClientesServlet")
public class AdministracionClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministracionClientesServlet() {
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
		String nombre = request.getParameter("nombre");
		boolean resultado = false;

		if (nombre != null) {
			ClienteDaoImpl dao = new ClienteDaoImpl();
			Cliente c = new Cliente();
			c.setNombre(nombre);
			resultado = dao.insert(c);
		}

		request.setAttribute("creado", resultado);
		request.getRequestDispatcher("AdministracionClientes.jsp").forward(request, response);

	}

}

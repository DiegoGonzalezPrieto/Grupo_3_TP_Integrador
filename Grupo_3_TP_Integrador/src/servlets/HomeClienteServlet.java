package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Prestamo;
import dominio.Usuario;
import negocio.ClienteNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class HomeClienteServlet
 */
@WebServlet("/HomeClienteServlet")
public class HomeClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocioImpl negocioCuentas;

	ClienteNegocio negocioCliente;
	PrestamoNegocio negocioPrestamo;

	public HomeClienteServlet() {
		super();
		negocioCuentas = new CuentaNegocioImpl();
		negocioCliente = new ClienteNegocioImpl();
		negocioPrestamo = new PrestamoNegocioImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			List<Prestamo> prestamosCliente = new ArrayList<Prestamo>();
			if (usuario == null) {
				response.sendRedirect("Login.jsp");
				return;
			}

			int idCliente = negocioCliente.buscarPorIdUsuario(usuario.getId());

			Cliente cliente = negocioCliente.buscarPorId(idCliente);

			List<Cuenta> cuentasCliente = negocioCuentas.listarActivasPorCliente(idCliente);
			
			request.setAttribute("cuentasCliente", cuentasCliente);
			request.setAttribute("cliente", cliente);
	
			prestamosCliente = negocioPrestamo.listarPrestamosXCliente(idCliente);
			request.setAttribute("prestamos", prestamosCliente);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeCliente.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error: " + e.getMessage());
			request.getSession().setAttribute("tipoMensaje", "danger");
			e.printStackTrace();
			response.sendRedirect("HomeCliente.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

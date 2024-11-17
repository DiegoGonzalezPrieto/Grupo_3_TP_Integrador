package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.EstadoPrestamo;
import dominio.Prestamo;
import dominio.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.EstadoPrestamoNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.EstadoPrestamoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class SolicitudPrestamoServlet
 */
@WebServlet("/SolicitudPrestamoServlet")
public class SolicitudPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteNegocio negoCliente;
	private Cliente cliente;
	private CuentaNegocio negoCuenta;
	private Cuenta cuenta;
	private ArrayList<Cuenta> cuentasCliente;
	private Prestamo prestamo;
	private PrestamoNegocio negoPrestamo;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SolicitudPrestamoServlet() {
		super();
		negoCliente = new ClienteNegocioImpl();
		negoCuenta = new CuentaNegocioImpl();
		prestamo = new Prestamo();
		negoPrestamo = new PrestamoNegocioImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idString = request.getParameter("id"); // Cambiar a getParameter
		int id = 0;

		if (idString != null) {
			try {
				id = Integer.parseInt(idString);
				cliente = negoCliente.buscarPorId(id);
				request.setAttribute("cliente", cliente);
			} catch (NumberFormatException e) {

				request.setAttribute("error", "ID inv�lido");
			}
		} else {
			request.setAttribute("error", "No se proporcion� un ID");
		}

		try {
			cuentasCliente = (ArrayList<Cuenta>) negoCuenta.listarPorCliente(cliente.getIdCliente());
			request.setAttribute("listaCuentas", cuentasCliente);

		} catch (Exception e) {
			response.sendRedirect("SolicitudPrestamo.jsp?mensaje=Error al ObtenerCuentas");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/SolicitudPrestamo.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		
		int idCliente = negoCliente.buscarPorIdUsuario(usuario.getId());

		cliente = negoCliente.buscarPorId(idCliente);

		cuentasCliente = (ArrayList<Cuenta>) negoCuenta.listarPorCliente(cliente.getIdCliente());

		request.setAttribute("cliente", cliente);
		request.setAttribute("listaCuentas", cuentasCliente);

		if (request.getParameter("btnSolicitar") != null) {
			try {
				Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

				EstadoPrestamoNegocio negoEstado = new EstadoPrestamoNegocioImpl();
				EstadoPrestamo estado = negoEstado.buscarPorId(1);

				cuenta = negoCuenta.obtenerCuentaPorId(Integer.parseInt(request.getParameter("cuenta")));
				
				
				///CALCULO DE MESES SEGUN CUOTAS
				int plazoMes = calculoMeses(Integer.parseInt(request.getParameter("CantidadDeCuotas")));
				
				prestamo.setCliente(cliente);
				prestamo.setCuenta(cuenta);
				prestamo.setFechaAltaPrestamo(fechaActual);
				prestamo.setImportePrestamo(new BigDecimal(request.getParameter("ImporteaSolicitar")));
				prestamo.setMesesPlazo(plazoMes);
				prestamo.setImporteMensual(new BigDecimal(request.getParameter("montoCuota")));
				prestamo.setCuotas(Integer.parseInt(request.getParameter("CantidadDeCuotas")));
				prestamo.setEstadoValidacion(estado);

				if (negoPrestamo.crearPrestamo(prestamo)) {
					request.setAttribute("mensaje", "Préstamo solicitado exitosamente, tiempo de aprobacion 48hs.");
					request.setAttribute("tipoMensaje", "success");
				} else {
					request.setAttribute("mensaje", "Error al crear el préstamo.");
					request.setAttribute("tipoMensaje", "danger");
				}

			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al crear el préstamo: " + e.getMessage());
				request.setAttribute("tipoMensaje", "danger");
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/SolicitudPrestamo.jsp");
		rd.forward(request, response);

	}
	
	public int calculoMeses(int x) {
		
		int cantidadCuotas = x;
		int plazoMeses = 0;
		
		
		if (cantidadCuotas == 6) {
	        plazoMeses = 6;  // 6 cuotas = 6 meses
	    } else if (cantidadCuotas == 12) {
	        plazoMeses = 12;  // 12 cuotas = 12 meses
	    } else if (cantidadCuotas == 18) {
	        plazoMeses = 18;  // 18 cuotas = 18 meses
	    } else if (cantidadCuotas == 24) {
	        plazoMeses = 24;  // 24 cuotas = 24 meses
	    } 
				
		
		return plazoMeses;
	}

}

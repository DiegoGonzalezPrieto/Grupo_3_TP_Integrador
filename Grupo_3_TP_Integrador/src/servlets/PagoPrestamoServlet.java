package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Cuota;
import dominio.Prestamo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/PagoPrestamoServlet")
public class PagoPrestamoServlet extends HttpServlet {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;

	private ClienteNegocio clienteNeg;
	private Cliente cliente;

	private CuentaNegocio cuentaNeg;
	private Cuenta cuenta;
	private ArrayList<Cuenta> cuentasCliente;

	private Prestamo prestamo;
	private PrestamoNegocio pNeg;

	private CuotaNegocio cuotaNeg;
	private ArrayList<Cuota> cuotasPrestamo;

	public PagoPrestamoServlet() {
		super();
		clienteNeg = new ClienteNegocioImpl();
		cuentaNeg = new CuentaNegocioImpl();

		prestamo = new Prestamo();

		pNeg = new PrestamoNegocioImpl();
		cuotaNeg = new CuotaNegocioImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("id") != null) {

			int idPrestamo = Integer.parseInt(request.getParameter("id"));
			int idCliente;

			try {
				prestamo = pNeg.obtenerPrestamoPorId(idPrestamo);
				idCliente = prestamo.getCliente().getIdCliente();

				cliente = clienteNeg.buscarPorId(idCliente);
				request.setAttribute("cliente", cliente);
				request.setAttribute("prestamo", prestamo);
			} catch (SQLException e) {
				request.setAttribute("error", "No se pudo Obtener El Cliente");
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID invalido");
			}

			try {
				cuotasPrestamo = cuotaNeg.listarCuotasPorPrestamo(idPrestamo);
				request.setAttribute("listaCuotas", cuotasPrestamo);
			} catch (SQLException e) {
				request.setAttribute("error", "No se pudo Obtener las cuotas");
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID PRESTAMO invalido");
			}

			try {
				cuentasCliente = (ArrayList<Cuenta>) cuentaNeg.listarPorCliente(cliente.getIdCliente());
				request.setAttribute("listaCuentas", cuentasCliente);
			} catch (Exception e) {
				response.sendRedirect("PagoPrestamo.jsp?mensaje=Error al ObtenerCuentas");
			}

		} else {
			request.setAttribute("error", "No se proporciono un ID");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/PagoPrestamo.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String accion = request.getParameter("accion");
			String idPrestamo = request.getParameter("idPrestamo");
			String idCuenta = request.getParameter("cuentas");

			if ("pagarCuotaSeleccionada".equals(accion)) {
				String cuotaSeleccionada = request.getParameter("cuotas");
				cuenta = cuentaNeg.obtenerCuentaPorId(Integer.parseInt(idCuenta));

				if (cuotaSeleccionada != null && !cuotaSeleccionada.isEmpty()) {
					Cuota cuota = cuotaNeg.obtenerCuotaPorId(Integer.parseInt(cuotaSeleccionada));
					if (cuenta.getSaldo().compareTo(cuota.getMontoPagado()) >= 0) {
						try {
							cuotaNeg.registrarPago(cuenta.getId(), cuota);
							request.setAttribute("mensaje", "Pago realizado con éxito");
							request.setAttribute("tipoMensaje", "success");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						request.setAttribute("mensaje", "Saldo insuficiente");
						request.setAttribute("tipoMensaje", "danger");
					}
				}
			} else if ("pagarTodas".equals(accion)) {
				cuenta = cuentaNeg.obtenerCuentaPorId(Integer.parseInt(idCuenta));
				cuotasPrestamo = cuotaNeg.listarCuotasPorPrestamo(Integer.parseInt(idPrestamo));
				for (Cuota cuota : cuotasPrestamo) {
					if (!cuota.getEstadoPago() && (cuenta.getSaldo().compareTo(cuota.getMontoPagado()) >= 0)) {
						try {
							cuotaNeg.registrarPago(cuenta.getId(), cuota);
							request.setAttribute("mensaje", "Todos los pagos realizados con éxito");
							request.setAttribute("tipoMensaje", "success");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						request.setAttribute("mensaje", "No se pudieron realizar todos los pagos. Saldo insuficiente");
						request.setAttribute("tipoMensaje", "danger");
					}
				}

			}

			prestamo = pNeg.obtenerPrestamoPorId(Integer.parseInt(idPrestamo));
			int idCliente = prestamo.getCliente().getIdCliente();
			cliente = clienteNeg.buscarPorId(idCliente);
			request.setAttribute("cliente", cliente);
			request.setAttribute("prestamo", prestamo);

			cuotasPrestamo = cuotaNeg.listarCuotasPorPrestamo(Integer.parseInt(idPrestamo));
			request.setAttribute("listaCuotas", cuotasPrestamo);

			cuentasCliente = (ArrayList<Cuenta>) cuentaNeg.listarPorCliente(cliente.getIdCliente());
			request.setAttribute("listaCuentas", cuentasCliente);

		} catch (Exception e) {
			request.setAttribute("mensaje", "Error: " + e.getMessage());
			request.setAttribute("tipoMensaje", "danger");
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/PagoPrestamo.jsp");
		rd.forward(request, response);
	}

}

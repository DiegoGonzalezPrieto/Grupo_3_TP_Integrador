package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cuenta;
import dominio.Movimiento;
import dominio.TipoMovimiento;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.TipoMovimientoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.TipoMovimientoNegocioImpl;

/**
 * Servlet implementation class TransferenciaServlet
 */
@WebServlet("/TransferenciaServlet")
public class TransferenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CuentaNegocio negCuenta;
	MovimientoNegocio negMovimiento;
	TipoMovimientoNegocio negTipoMovimiento;
	ClienteNegocio negCliente;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferenciaServlet() {
		super();
		negCuenta = new CuentaNegocioImpl();
		negMovimiento = new MovimientoNegocioImpl();
		negTipoMovimiento = new TipoMovimientoNegocioImpl();
		negCliente = new ClienteNegocioImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("cliente") == null) {
			response.sendRedirect("HomeCliente.jsp");
			return;
		}

		int idCliente = Integer.parseInt(request.getParameter("cliente"));
		List<Cuenta> cuentasPropias = negCuenta.listarActivasPorCliente(idCliente);
		int idUsuarioCuentas = negCliente.buscarPorId(idCliente).getId();

		request.setAttribute("cuentasPropias", cuentasPropias);
		request.setAttribute("idUsuarioCuentas", idUsuarioCuentas);
		request.getRequestDispatcher("Transferencia.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// Validar Parameters
			if (request.getParameter("cuentaOrigen") == null || request.getParameter("monto") == null
					|| (request.getParameter("cbuDestino") == null && request.getParameter("cuentaPropia") == null)) {
				request.setAttribute("mensaje", "Error: Faltan datos para realizar la transferencia.");
				request.setAttribute("claseMensaje", "danger");
				doGet(request, response);
				return;
			}

			int idCuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
			request.getParameter("cbuDestino");
			BigDecimal monto = new BigDecimal(request.getParameter("monto"));

			// Obtener Cuenta Destino
			String cbuCuentaDestino = request.getParameter("cbuDestino") != null ? request.getParameter("cbuDestino")
					: "";
			int idCuentaPropiaDestino = request.getParameter("cuentaPropia") != null
					? Integer.parseInt(request.getParameter("cuentaPropia"))
					: 0;

			Cuenta cuentaDestino = cbuCuentaDestino.isEmpty() ? negCuenta.obtenerCuentaPorId(idCuentaPropiaDestino)
					: negCuenta.obtenerCuentaPorCbu(cbuCuentaDestino);

			// Validar cuenta origen existe
			Cuenta cuentaOrigen = negCuenta.obtenerCuentaPorId(idCuentaOrigen);
			if (cuentaOrigen == null) {
				request.setAttribute("mensaje", "Error: La cuenta de origen seleccionada no existe.");
				request.setAttribute("claseMensaje", "danger");
				doGet(request, response);
				return;
			}

			// Validar cuenta origen tiene saldo suficiente
			if (cuentaOrigen.getSaldo().compareTo(monto) == -1) {
				request.setAttribute("mensaje", "Error: Fondos insuficientes.");
				request.setAttribute("claseMensaje", "danger");
				doGet(request, response);
				return;
			}
			// Validar que el monto sea superior a 0
			if (monto.compareTo(BigDecimal.ZERO) != 1) {
				request.setAttribute("mensaje", "Error: El monto debe ser mayor a 0.");
				request.setAttribute("claseMensaje", "danger");
				doGet(request, response);
				return;
			}

			// Validar cuenta destino (existe)
			if (cuentaDestino == null || !cuentaDestino.Activa()) {
				request.setAttribute("mensaje", "Error: La cuenta de destino seleccionada no existe.");
				request.setAttribute("claseMensaje", "danger");
				doGet(request, response);
				return;
			}

			// Validar cuenta destino no es la cuenta de origen
			if (cuentaDestino.getCbu().equals(cuentaOrigen.getCbu())) {
				request.setAttribute("mensaje",
						"Error: La cuenta de origen no puede ser igual a la cuenta de destino.");
				request.setAttribute("claseMensaje", "danger");
				doGet(request, response);
				return;
			}

			// 2 cambios de saldo
			modificarSaldos(cuentaOrigen, cuentaDestino, monto);

			// 2 movimientos
			generarMovimientos(cuentaOrigen, cuentaDestino, monto);

			// �xito
			request.setAttribute("mensaje", "Transferencia exitosa!");
			request.setAttribute("claseMensaje", "success");
			doGet(request, response);

		} catch (Exception e) {
			request.setAttribute("mensaje", "Ups! Hubo un error inesperado...");
			request.setAttribute("claseMensaje", "danger");
			doGet(request, response);
		}
	}

	void modificarSaldos(Cuenta cuentaOrigen, Cuenta cuentaDestino, BigDecimal monto) {
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
		cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));

		negCuenta.actualizarCuenta(cuentaOrigen);
		negCuenta.actualizarCuenta(cuentaDestino);
	}

	void generarMovimientos(Cuenta cuentaOrigen, Cuenta cuentaDestino, BigDecimal monto) {
		TipoMovimiento tipoTransfDebitada = negTipoMovimiento.buscarPorId(5);
		TipoMovimiento tipoTransfAcreditada = negTipoMovimiento.buscarPorId(4);
		Date hoy = new Date(new java.util.Date().getTime());

		Movimiento movSalida = new Movimiento(0, cuentaOrigen, tipoTransfDebitada, hoy, "Débito por transferencia.",
				monto.negate());
		Movimiento movEntrada = new Movimiento(0, cuentaDestino, tipoTransfAcreditada, hoy,
				"Crédito por transferencia.", monto);
		negMovimiento.insert(movSalida);
		negMovimiento.insert(movEntrada);
	}

}

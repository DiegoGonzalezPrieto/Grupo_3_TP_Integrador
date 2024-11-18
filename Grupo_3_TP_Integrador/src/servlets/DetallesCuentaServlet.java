package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cuenta;
import dominio.Movimiento;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/DetallesCuentaServlet")
public class DetallesCuentaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	ClienteNegocioImpl negCliente;
	CuentaNegocioImpl negCuenta;
	MovimientoNegocioImpl negMovimiento;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetallesCuentaServlet() {
		super();
		negCliente = new ClienteNegocioImpl();
		negCuenta = new CuentaNegocioImpl();
		negMovimiento = new MovimientoNegocioImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("id") == null) {
			response.sendRedirect("HomeCliente.jsp");
			return;
		}
		
		int idCuenta = Integer.parseInt(request.getParameter("id"));
		Cuenta cuenta = negCuenta.obtenerCuentaPorId(idCuenta);
		List<Movimiento> movimientosPropios = negMovimiento.listarMovimientosPorCuenta(idCuenta);
		
		if (cuenta != null) {

			request.setAttribute("tipoCuenta", cuenta.getTipoCuenta().getNombre());
			request.setAttribute("numeroCuenta", cuenta.getNumeroCuenta());
			request.setAttribute("cbu", cuenta.getCbu());
			request.setAttribute("saldo", cuenta.getSaldo());
		}
			request.setAttribute("movimientosPropios", movimientosPropios);
			request.getRequestDispatcher("DetallesCuenta.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

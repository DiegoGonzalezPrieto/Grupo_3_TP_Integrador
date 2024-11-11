package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class AgregarCuentaServlet
 */
@WebServlet("/AgregarCuentaServlet")
public class AgregarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio negocioCuentas;
    
    public AgregarCuentaServlet() {
        super();
        negocioCuentas = new CuentaNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int idCliente = Integer.parseInt(request.getParameter("cliente"));
            int idTipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
            Long numeroCuenta = Long.parseLong(request.getParameter("numeroCuenta"));
            String cbu = request.getParameter("cbu");
            
            Cuenta cuenta = new Cuenta();
            
            Cliente cliente = new Cliente();
            cliente.setIdCliente(idCliente);
            cuenta.setCliente(cliente);
            
            TipoCuenta tipoCuenta = new TipoCuenta();
            tipoCuenta.setId(idTipoCuenta);
            cuenta.setTipoCuenta(tipoCuenta);
            
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setCbu(cbu);
            
            negocioCuentas.crearCuenta(cuenta);
          
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionCuentasServlet");
        dispatcher.forward(request, response);
    }
	

}

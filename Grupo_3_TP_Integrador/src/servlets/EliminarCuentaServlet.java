package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cuenta;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class EliminarCuentaServlet
 */
@WebServlet("/EliminarCuentaServlet")
public class EliminarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocioImpl negocioCuentas;
    
    public EliminarCuentaServlet() {
        super();
        negocioCuentas = new CuentaNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int idCuenta = Integer.parseInt(request.getParameter("id"));
            
            Cuenta cuenta = negocioCuentas.obtenerCuentaPorId(idCuenta);
            
            if(cuenta != null) {
                if(cuenta.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                    if(negocioCuentas.eliminarCuenta(idCuenta)) {
                        request.setAttribute("mensaje", "Cuenta eliminada exitosamente");
                        request.setAttribute("tipoMensaje", "success");
                    } else {
                        request.setAttribute("mensaje", "Error al eliminar la cuenta");
                        request.setAttribute("tipoMensaje", "danger");
                    }
                } else {
                    request.setAttribute("mensaje", 
                        "No se puede eliminar la cuenta. El saldo debe ser $0 (saldo actual: $" + 
                        String.format("%,.2f", cuenta.getSaldo()) + ")");
                    request.setAttribute("tipoMensaje", "warning");
                }
            }
            
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al procesar la solicitud: " + e.getMessage());
            request.setAttribute("tipoMensaje", "danger");
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionCuentasServlet");
        dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

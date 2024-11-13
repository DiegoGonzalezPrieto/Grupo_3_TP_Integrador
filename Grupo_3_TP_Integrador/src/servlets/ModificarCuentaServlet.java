package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cuenta;
import dominio.TipoCuenta;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;

/**
 * Servlet implementation class ModificarCuentaServlet
 */
@WebServlet("/ModificarCuentaServlet")
public class ModificarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CuentaNegocioImpl negocioCuentas;
    private TipoCuentaNegocioImpl negocioTiposCuenta;
       
    public ModificarCuentaServlet() {
        super();
        negocioCuentas = new CuentaNegocioImpl();
        negocioTiposCuenta = new TipoCuentaNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int idCuenta = Integer.parseInt(request.getParameter("id"));
            
            Cuenta cuenta = negocioCuentas.obtenerCuentaPorId(idCuenta);
            
            if(cuenta != null) {
                List<TipoCuenta> listaTiposCuenta = negocioTiposCuenta.buscarTodos();
                
                request.setAttribute("cuenta", cuenta);
                request.setAttribute("listaTiposCuenta", listaTiposCuenta);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCuenta.jsp");
                dispatcher.forward(request, response);
              
            } else {
                request.setAttribute("mensaje", "No se encontró la cuenta especificada");
                request.setAttribute("tipoMensaje", "danger");
                response.sendRedirect("AdministracionCuentasServlet");
            }
            
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al cargar la cuenta: " + e.getMessage());
            request.setAttribute("tipoMensaje", "danger");
            response.sendRedirect("AdministracionCuentasServlet");
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
            int idTipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
            BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
            
            Cuenta cuenta = negocioCuentas.obtenerCuentaPorId(idCuenta);
            if(cuenta == null) {
                request.setAttribute("mensaje", "No se encontró la cuenta a modificar");
                request.setAttribute("tipoMensaje", "danger");
                response.sendRedirect("AdministracionCuentasServlet");
                return;
            }
            
            TipoCuenta tipoCuenta = new TipoCuenta();
            tipoCuenta.setId(idTipoCuenta);
            cuenta.setTipoCuenta(tipoCuenta);
            cuenta.setSaldo(saldo);
            
            if(negocioCuentas.actualizarCuenta(cuenta)) {
                request.setAttribute("mensaje", "Cuenta actualizada exitosamente");
                request.setAttribute("tipoMensaje", "success");
            } else {
                request.setAttribute("mensaje", "Error al actualizar la cuenta");
                request.setAttribute("tipoMensaje", "danger");
            }
            
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "Error en el formato de los datos");
            request.setAttribute("tipoMensaje", "danger");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al procesar la solicitud: " + e.getMessage());
            request.setAttribute("tipoMensaje", "danger");
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionCuentasServlet");
        dispatcher.forward(request, response);
    }

}

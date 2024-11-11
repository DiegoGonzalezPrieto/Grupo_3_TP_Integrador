package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;

/**
 * Servlet implementation class AdministracionCuentasServlet
 */
@WebServlet("/AdministracionCuentasServlet")
public class AdministracionCuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministracionCuentasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CuentaNegocioImpl cuentas = new CuentaNegocioImpl();
		
		ClienteNegocioImpl clientes = new ClienteNegocioImpl();
        TipoCuentaNegocioImpl tiposCuenta = new TipoCuentaNegocioImpl();
		        
        List<Cuenta> listaCuentas = cuentas.listarTodas();
        
        List<Cliente> listaClientes = clientes.listarTodos();
        List<TipoCuenta> listaTiposCuenta = tiposCuenta.buscarTodos();
        
        request.setAttribute("listaCuentas", listaCuentas);
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaTiposCuenta", listaTiposCuenta);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionCuentas.jsp");
        dispatcher.forward(request, response);
    }
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

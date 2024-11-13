package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import dominio.Cliente;
import dominio.Cuenta;

@WebServlet("/HomeAdministradorServlet")
public class HomeAdministradorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !usuario.esAdmin()) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
        CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
        PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();

        int clientesActivos = clienteNegocio.contarClientesActivos();
        int cuentasAbiertas = cuentaNegocio.totalCuentasAbiertas();
        int prestamosAutorizados = 0;
		try {
			prestamosAutorizados = prestamoNegocio.contarPrestamosAprobados();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int prestamosPendientes = 0;
		try {
			prestamosPendientes = prestamoNegocio.contarPrestamosEnEvaluacion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        List<Cuenta> cuentasRecientes = cuentaNegocio.listarCuentasRecientes();
        
        for (Cuenta cuenta : cuentasRecientes) {
        	
        	//Lineas provisorias: (agregar apellido en mapResultSet de Cuenta y cambiar if)
        	Cliente cliente = clienteNegocio.buscarPorId(cuenta.getCliente().getIdCliente());
            cuenta.setCliente(cliente);
        	
            //if (cuenta.getCliente()!= null) {
        	if (cliente != null) {
            	request.setAttribute("numeroCuenta_" + cuenta.getNumeroCuenta(), cuenta.getNumeroCuenta().toString());
	        	request.setAttribute("tipoCuenta_" + cuenta.getNumeroCuenta(), cuenta.getTipoCuenta().getNombre());
	        	request.setAttribute("saldo_" + cuenta.getNumeroCuenta(), cuenta.getSaldo().toString());
	        	request.setAttribute("nombreCliente_" + cuenta.getNumeroCuenta(), cuenta.getCliente().getNombre());
	        	request.setAttribute("apellidoCliente_" + cuenta.getNumeroCuenta(), cuenta.getCliente().getApellido());
        	}else {
        		System.out.println("Cliente es null para la cuenta: " + cuenta.getNumeroCuenta());
        	}
        }
        
        
        request.setAttribute("clientesActivos", clientesActivos);
        request.setAttribute("cuentasAbiertas", cuentasAbiertas);
        request.setAttribute("prestamosAutorizados", prestamosAutorizados);
        request.setAttribute("prestamosPendientes", prestamosPendientes);
        request.setAttribute("cuentasRecientes", cuentasRecientes);
        
        RequestDispatcher rd = request.getRequestDispatcher("HomeAdministrador.jsp");
        rd.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

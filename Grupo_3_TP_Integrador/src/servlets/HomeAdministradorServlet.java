package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
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
        //PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();

        int clientesActivos = clienteNegocio.contarClientesActivos();
        int cuentasAbiertas = cuentaNegocio.totalCuentasAbiertas();
        //int prestamosAutorizados = prestamoNegocio.contarPrestamosAutorizados();.
        int prestamosAutorizados = 268;
        //int prestamosPendientes = prestamoNegocio.contarPrestamosPendientes();
        int prestamosPendientes = 130;
        List<Cuenta> cuentasRecientes = cuentaNegocio.listarCuentasRecientes();
        
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

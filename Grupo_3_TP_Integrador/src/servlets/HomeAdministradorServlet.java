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
//import dominio.Cuenta;

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
        
        //ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
        //Cliente cliente = clienteNegocio.buscarPorIdUsuario(usuario.getId());

        //CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
        //PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();

        int clientesActivos = clienteNegocio.contarClientesActivos();
        int cuentasAbiertas = cuentaNegocio.contarCuentasAbiertas();
        int prestamosAutorizados = prestamoNegocio.contarPrestamosAutorizados();
        int prestamosPendientes = prestamoNegocio.contarPrestamosPendientes();
        List<Cuenta> cuentasRecientes = cuentaNegocio.obtenerCuentasRecientes();

        // Pasar los datos a la vista
        request.setAttribute("clientesActivos", clientesActivos);
        request.setAttribute("cuentasAbiertas", cuentasAbiertas);
        request.setAttribute("prestamosAutorizados", prestamosAutorizados);
        request.setAttribute("prestamosPendientes", prestamosPendientes);
        request.setAttribute("cuentasRecientes", cuentasRecientes);

        // Redirigir a la vista
        RequestDispatcher rd = request.getRequestDispatcher("HomeAdministrador.jsp");
        rd.forward(request, response);
    }
}

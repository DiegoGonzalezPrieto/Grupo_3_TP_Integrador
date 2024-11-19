package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import dominio.Cliente;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

/**
 * Servlet implementation class AdministracionClientesServlet
 */
@WebServlet("/AdministracionClientesServlet")
public class AdministracionClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	ClienteNegocioImpl negoCli = new ClienteNegocioImpl();
	
	public AdministracionClientesServlet() {
		super();
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		
		List<Cliente> listaClientes = negoCli.listarActivos();

		
		request.setAttribute("listaClientes", listaClientes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionClientes.jsp");
        dispatcher.forward(request, response);
    }

    private void verCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = negoCli.buscarPorId(clienteId);
        request.setAttribute("cliente", cliente);
        RequestDispatcher rd = request.getRequestDispatcher("/GestionDatos.jsp");
        rd.forward(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = negoCli.buscarPorId(clienteId);
        request.setAttribute("cliente", cliente);
        request.setAttribute("accion", "editar");
        RequestDispatcher rd = request.getRequestDispatcher("/GestionDatos.jsp");
        rd.forward(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int clienteId = Integer.parseInt(request.getParameter("id"));
       
        response.sendRedirect("AdministracionClientesServlet?action=listar");
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Cliente> listadoClientes = negoCli.listarTodos();
        request.setAttribute("listaC", listadoClientes);
        RequestDispatcher rd = request.getRequestDispatcher("/AdministracionClientes.jsp");
        rd.forward(request, response);
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		
	}
	

}

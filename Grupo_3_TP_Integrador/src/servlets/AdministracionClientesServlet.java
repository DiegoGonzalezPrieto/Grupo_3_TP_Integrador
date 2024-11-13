package servlets;

import java.io.IOException;
import java.util.ArrayList;

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
	

	ClienteNegocio negoCli = new ClienteNegocioImpl();
	ClienteDao daoCliente = new ClienteDaoImpl();
	public AdministracionClientesServlet() {
		super();
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String action = request.getParameter("action");
	    String clienteIdParam = request.getParameter("clienteId");
	    Cliente cliente = null;

	    if (clienteIdParam != null) {
	        int clienteId = Integer.parseInt(clienteIdParam);

	        if ("ver".equals(action)) {
	            verCliente(request, response);
	            request.setAttribute("accion", "ver");

	        } else if ("editar".equals(action)) {
	        	editarCliente(request, response);
	            request.setAttribute("accion", "editar");

	        } else if ("eliminar".equals(action)) {
	        	eliminarCliente(request, response);
	            request.setAttribute("accion", "eliminar");
	       
	        }
	        
	  
	        request.setAttribute("cliente", cliente);
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/GestionDatos.jsp");
	        rd.forward(request, response);

	    } else {
	    	listarClientes(request, response);
	        response.sendRedirect("error.jsp");
	    }
    }

    private void verCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = daoCliente.encontrarPorId(clienteId);
        request.setAttribute("cliente", cliente);
        RequestDispatcher rd = request.getRequestDispatcher("/GestionDatos.jsp");
        rd.forward(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = daoCliente.encontrarPorId(clienteId);
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
        ArrayList<Cliente> listadoClientes = daoCliente.buscarTodos();
        request.setAttribute("listaC", listadoClientes);
        RequestDispatcher rd = request.getRequestDispatcher("/AdministracionClientes.jsp");
        rd.forward(request, response);
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/administracionClientes.jsp"); 
		
	}
	

}

package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import exceptions.PasswordIncorrectaException;
import exceptions.UsuarioNoEncontradoException;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// hace logout y lleva al login.jsp
		
		HttpSession session = request.getSession();
		session.removeAttribute("usuario");
		
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("usuario");
		String password = request.getParameter("password");

		
		if (user == null || password == null) { 
			request.setAttribute("error", "Faltan datos."); 
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp"); 
			rd.forward(request, response); return; 
		}
		
		UsuarioNegocioImpl n = new UsuarioNegocioImpl();
		
		try { 
			Usuario usuario = validarUsuario(user, password, n);
			confirmarLogin(usuario, request); String ruta = obtenerRuta(usuario);
			RequestDispatcher rd = request.getRequestDispatcher(ruta);
			rd.forward(request, response);
			} catch (UsuarioNoEncontradoException | PasswordIncorrectaException e) {
				request.setAttribute("error", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
	}
	
	private Usuario validarUsuario(String user, String password, UsuarioNegocioImpl n) throws UsuarioNoEncontradoException, PasswordIncorrectaException {
		ArrayList<Usuario> usuarios = n.buscarTodos();
		
		for (Usuario usuario : usuarios) { 
			if (usuario.getNombreUsuario().equals(user)) { 
				if (usuario.getPass().equals(password)) { 
					return usuario;
					} else {
						throw new PasswordIncorrectaException();
					}
				}
			}
			throw new UsuarioNoEncontradoException();
		}
	
	void confirmarLogin(Usuario usuario, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("usuario", usuario);
	}
	
	
	String obtenerRuta(Usuario usuario) {
		return usuario.esAdmin() ? "HomeAdministrador.jsp" : "HomeCliente.jsp";
	}
}

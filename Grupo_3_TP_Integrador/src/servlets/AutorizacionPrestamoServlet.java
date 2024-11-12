package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Prestamo;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;


@WebServlet("/AutorizacionPrestamoServlet")
public class AutorizacionPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrestamoNegocio pNeg = new PrestamoNegocioImpl();   
   
    public AutorizacionPrestamoServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//OBTENER TODOS LOS PRESTAMOS
			ArrayList<Prestamo> todosLosPrestamos = new ArrayList<Prestamo>();
			todosLosPrestamos=pNeg.listarTodosLosPrestamos();
			
			if(todosLosPrestamos == null || todosLosPrestamos.isEmpty()) {
				System.out.println("no hay prestamos");
			}
			else {
				System.out.println("se encontraron prestamos");
			}
			request.setAttribute("listaPrestamos", todosLosPrestamos);
								
			//MANDA LA INFO AL JSP.
			RequestDispatcher rd = request.getRequestDispatcher("/AutorizacionPrestamos.jsp");
			rd.forward(request, response); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idPrestamo = request.getParameter("id");
		String accion = request.getParameter("accion");
		
		///VERIFICO QUE NO LLEGUE VACIO O NULL
		
		if(idPrestamo == null || idPrestamo.isEmpty() || accion == null || accion.isEmpty()) {
			response.sendRedirect("error.jsp?mensaje=Parametros invalidos");
			return;
		}
		
		try {
			
			//BUSCO EL PRESTAMO
			int prestamoId = Integer.parseInt(idPrestamo);
			Prestamo prestamo = pNeg.obtenerPrestamoPorId(prestamoId);
			
			if(prestamo == null) {
				response.sendRedirect("error.jsp?mensaje=Prestamo no encontrado");
				return;
			}
			
			//PRESTAMO APROBADO
			
			if("Aprobar".equals(accion)) {
				pNeg.aprobarPrestamo(prestamo);
			}
			else if ("Rechazar".equals(accion)) {
				pNeg.actualizarEstadoSolicitud(prestamo.getId(),3);
			}
			else {
				response.sendRedirect("error.jsp?mensaje=Accion no valida");
				return;
			}
			
			response.sendRedirect("AutorizacionPrestamosServet"); // ENVIO AL SERVLET PARA QUE VUELVA A CARGAR LA LISTA Y APAREZCA COMO CORRESPONDE
		}
		catch(NumberFormatException e) {
			response.sendRedirect("error.jsp?mensaje= ID de prestamo invalido");
			
		} catch (SQLException e) {
			response.sendRedirect("error.jsp?mensaje= Error al Procesar la solicitud");
		}
		
		
	}

}

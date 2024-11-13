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
	
	//DECLARACION DE VARIABLES
	private static final long serialVersionUID = 1L;
    private PrestamoNegocio pNeg = new PrestamoNegocioImpl(); 
    static final int APROBADO = 2;
    static final int RECHAZADO = 3;
    
    
    public AutorizacionPrestamoServlet() {
        super();
        
    }
    
    private boolean validarParametros(String idPrestamo, String accion) {
    	 return idPrestamo != null && !idPrestamo.isEmpty() && accion != null && !accion.isEmpty();
    }
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//OBTENER TODOS LOS PRESTAMOS
			ArrayList<Prestamo> todosLosPrestamos = pNeg.listarTodosLosPrestamos();
			request.setAttribute("listaPrestamos", todosLosPrestamos);
								
			//MANDA LA INFO AL JSP.
			RequestDispatcher rd = request.getRequestDispatcher("/AutorizacionPrestamos.jsp");
			rd.forward(request, response); 
			
		} catch (Exception e) {
			response.sendRedirect("error.jsp?mensaje=Error al Obtener los Prestamos");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idPrestamo = request.getParameter("id");
		String accion = request.getParameter("accion");
		
		///VERIFICO QUE NO LLEGUE VACIO O NULL
		
		if(!validarParametros(idPrestamo,accion)) {
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
			
			//MANEJO DE PRESTAMO 
			
			if("Aprobar".equals(accion)) {
				pNeg.aprobarPrestamo(prestamo);
				//pNeg.actualizarEstadoSolicitud(prestamo.getId(),APROBADO);
			}
			else if ("Rechazar".equals(accion)) {
				pNeg.actualizarEstadoSolicitud(prestamo.getId(),RECHAZADO);
			}
			else {
				response.sendRedirect("error.jsp?mensaje=Accion no valida");
				return;
			}

			request.setAttribute("mensajeExito", "La solicitud fue procesada correctamente");
			RequestDispatcher rd = request.getRequestDispatcher("/AutorizacionPrestamos.jsp");
			rd.forward(request, response);
			
		}
		catch(NumberFormatException e) {
			response.sendRedirect("error.jsp?mensaje= ID de prestamo invalido");
			
		} catch (SQLException e) {
			response.sendRedirect("error.jsp?mensaje= Error al Procesar la solicitud");
		}
		
		
	}

}

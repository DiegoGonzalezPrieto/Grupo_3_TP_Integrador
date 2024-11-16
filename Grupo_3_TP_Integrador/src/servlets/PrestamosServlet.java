package servlets;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ClienteNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import dominio.Prestamo;
import dominio.Cliente;

@WebServlet("/PrestamosServlet")
public class PrestamosServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	//ATRIBUTOS
    PrestamoNegocio pNeg = new PrestamoNegocioImpl();  
    ClienteNegocio cNeg = new ClienteNegocioImpl();
    Cliente cliente = new Cliente ();
    ArrayList<Prestamo> listaPrestamo = new ArrayList<Prestamo>();
  
    public PrestamosServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id") != null) {
			
			int idCliente = Integer.parseInt(request.getParameter("id"));
			
			try {
				//CAPTURA LA LISTA DE PRESTAMOS POR CLIENTE
				listaPrestamo = pNeg.listarPrestamosXCliente(idCliente);
				cliente = cNeg.buscarPorId(idCliente);
				request.setAttribute("listaPrestamos", listaPrestamo);
				request.setAttribute("cliente", cliente);
				
				
				//MANDA LA INFO AL JSP.
				RequestDispatcher rd = request.getRequestDispatcher("/Prestamo.jsp");
				rd.forward(request, response); 
				
			} catch (SQLException e) {
				response.sendRedirect("Prestamo.jsp?mensaje=Error al Obtener los Prestamos");
			}
			
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Tomamos los parámetros del formulario (filtros)
	    String tipoCuenta = request.getParameter("tipoCuenta");
	    String estadoPrestamo = request.getParameter("estadoPrestamo");
	    String cuotasJsp = request.getParameter("cuotas");
	    String importe = request.getParameter("importeMax");
	    
	    // Imprimir los valores de los parámetros
	    System.out.println("tipoCuenta: " + tipoCuenta);
	    System.out.println("estadoPrestamo: " + estadoPrestamo);
	    System.out.println("cuotas: " + cuotasJsp);
	    System.out.println("importeMax: " + importe);
	    
	    // Declaramos las variables para usar en los filtros
	    BigDecimal importeMax = null;
	    Integer cuotas = null;  // Cambié el valor inicial a null para manejar la ausencia de cuota correctamente
	    
	    // Convertimos el importe máximo a BigDecimal si no es vacío
	    if (importe != null && !importe.isEmpty()) {
	        try {
	            importeMax = new BigDecimal(importe);
	        } catch (NumberFormatException e) {
	            importeMax = null;  // Si no es un número válido, dejamos importeMax como null
	        }
	    }
	    
	    // Convertimos cuotas a Integer si no es vacío
	    if (cuotasJsp != null && !cuotasJsp.isEmpty()) {
	        try {
	            cuotas = Integer.parseInt(cuotasJsp);
	        } catch (NumberFormatException e) {
	            cuotas = null;  // Si no es un número válido, dejamos cuotas como null
	        }
	    }

	    // Obtenemos el ID del cliente desde el request
	    int idCliente = Integer.parseInt(request.getParameter("id"));
	    
	    try {
	        // Recuperamos todos los préstamos del cliente
	        listaPrestamo = pNeg.listarPrestamosXCliente(idCliente);
	        cliente = cNeg.buscarPorId(idCliente);
	        
	        // Creamos una copia de la lista para aplicar los filtros
	        ArrayList<Prestamo> prestamoFiltrado = new ArrayList<>(listaPrestamo);
	        
	        // Filtro por tipo de cuenta si el filtro no está vacío
	        if (tipoCuenta != null && !tipoCuenta.isEmpty()) {
	            prestamoFiltrado = (ArrayList<Prestamo>) prestamoFiltrado.stream()
	                .filter(p -> p.getCuenta().getTipoCuenta().getNombre().equals(tipoCuenta))
	                .collect(Collectors.toList());
	        }
	        
	        // Filtro por estado de préstamo si el filtro no está vacío
	        if (estadoPrestamo != null && !estadoPrestamo.isEmpty()) {
	            prestamoFiltrado = (ArrayList<Prestamo>) prestamoFiltrado.stream()
	                .filter(p -> p.getEstadoValidacion().getNombre().equals(estadoPrestamo))
	                .collect(Collectors.toList());
	        }
	        
	        // Filtro por cuotas si el filtro no está vacío
	        if (cuotas != null) {
	            final int cuotasFinal = cuotas; // Declaramos la variable final para usar en lambda
	            prestamoFiltrado = (ArrayList<Prestamo>) prestamoFiltrado.stream()
	                .filter(p -> p.getCuotas() == cuotasFinal)
	                .collect(Collectors.toList());
	        }
	        
	        // Filtro por importe máximo si el filtro no está vacío
	        if (importeMax != null) {
	            final BigDecimal importeMaxFinal = importeMax; // Declaramos la variable final para usar en lambda
	            prestamoFiltrado = (ArrayList<Prestamo>) prestamoFiltrado.stream()
	                .filter(p -> p.getImportePrestamo().compareTo(importeMaxFinal) <= 0)
	                .collect(Collectors.toList());
	        }
	        
	        // Pasamos la lista filtrada y el cliente al JSP
	        request.setAttribute("listaPrestamos", prestamoFiltrado);
	        request.setAttribute("cliente", cliente);
	        
	        // Redirigimos al JSP para mostrar los resultados
	        RequestDispatcher rd = request.getRequestDispatcher("/Prestamo.jsp");
	        rd.forward(request, response);
	        
	    } catch (SQLException e) {
	        // En caso de error, redirigimos con un mensaje
	        response.sendRedirect("Prestamo.jsp?mensaje=Error al Aplicar filtro a los Prestamos");
	    }
	}
}

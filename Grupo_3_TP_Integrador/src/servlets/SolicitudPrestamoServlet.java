package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Cuota;
import dominio.EstadoPrestamo;
import dominio.Prestamo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.EstadoPrestamoNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.EstadoPrestamoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class SolicitudPrestamoServlet
 */
@WebServlet("/SolicitudPrestamoServlet")
public class SolicitudPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClienteNegocio negoCliente;
	private Cliente cliente;
	private CuentaNegocio negoCuenta;
	private Cuenta cuenta;
	private ArrayList<Cuenta> cuentasCliente;
    private Cuota cuota;
    private Prestamo prestamo;
    private PrestamoNegocio negoPrestamo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitudPrestamoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String idString = request.getParameter("id"); // Cambiar a getParameter
	    int id = 0;
	    negoCliente = new ClienteNegocioImpl();
	    negoCuenta = new CuentaNegocioImpl();
	    
	    if (idString != null) {
	        try {
	            id = Integer.parseInt(idString); 
	            cliente = negoCliente.buscarPorId(id); 
	            System.out.println(cliente.toString());
	            request.setAttribute("cliente", cliente); 
	        } catch (NumberFormatException e) {
	            
	            request.setAttribute("error", "ID inválido");
	        }
	    } else {
	        request.setAttribute("error", "No se proporcionó un ID");
	    }
	    
	    try {
	    	cuentasCliente = (ArrayList<Cuenta>) negoCuenta.listarPorCliente(cliente.getIdCliente());
	    	request.setAttribute("listaCuentas", cuentasCliente);
	    	
	    }catch(Exception e) {
	    	response.sendRedirect("SolicitudPrestamo.jsp?mensaje=Error al Obtener Cuentas");
	    }
	    
	    if(request.getParameter("btnSolicitar") != null) {
	    	  	
	    	try {
	    		Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());
		    	prestamo = new Prestamo();
		    	negoCuenta = new CuentaNegocioImpl();
		    	
		    	EstadoPrestamoNegocio negoEstado = new EstadoPrestamoNegocioImpl();
		    	EstadoPrestamo estado = negoEstado.buscarPorId(1);
	    		cuenta = negoCuenta.obtenerCuentaPorId(Integer.parseInt(request.getParameter("cuenta")));
	    		negoPrestamo = new PrestamoNegocioImpl();
	    		prestamo.setCliente((Cliente)request.getAttribute("cliente"));
	    		
	    		prestamo.setCuenta(cuenta);
	    		prestamo.setFechaAltaPrestamo(fechaActual);
	    		BigDecimal importe = new BigDecimal(request.getParameter(""));
	    		prestamo.setImportePrestamo(importe);
	    		prestamo.setMesesPlazo( Integer.parseInt(request.getParameter("")) );
	    		BigDecimal importeMensual = new BigDecimal(request.getParameter(""));
	    		prestamo.setImporteMensual(importeMensual);
	    		prestamo.setCuotas( Integer.parseInt(request.getParameter("")));
	    		prestamo.setEstadoValidacion(estado);
	    		System.out.println(prestamo.toString());
	    		negoPrestamo.crearPrestamo(prestamo);
	    		
	    	}catch(Exception e) {
	    		response.sendRedirect("SolicitudPrestamo.jsp?mensaje=Error al otorgar prestamo");
	    	}
	    	
	    }
	    
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/SolicitudPrestamo.jsp");
		rd.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}

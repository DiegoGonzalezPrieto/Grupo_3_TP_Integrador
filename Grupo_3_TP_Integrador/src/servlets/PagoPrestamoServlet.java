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

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Cuota;
import dominio.Prestamo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;


@WebServlet("/PagoPrestamoServlet")
public class PagoPrestamoServlet extends HttpServlet {
	
	//ATRIBUTOS
	private static final long serialVersionUID = 1L;
	
	private ClienteNegocio clienteNeg;
	private Cliente cliente;
	
	private CuentaNegocio cuentaNeg;	
	private Cuenta cuenta;
	private ArrayList<Cuenta> cuentasCliente;
	
	private Prestamo prestamo;
	private PrestamoNegocio pNeg; 
	
	private CuotaNegocio cuotaNeg;
	private ArrayList<Cuota> cuotasPrestamo;
   
    public PagoPrestamoServlet() {
        super();
        clienteNeg = new ClienteNegocioImpl();
		cuentaNeg = new CuentaNegocioImpl();
		
		prestamo = new Prestamo();
		
		pNeg = new PrestamoNegocioImpl();
		cuotaNeg = new CuotaNegocioImpl();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id") != null) {
			
			int idPrestamo = Integer.parseInt(request.getParameter("id"));
			int idCliente;
						
			//CAPTURO CLIENTE
			try
			{				
				prestamo = pNeg.obtenerPrestamoPorId(idPrestamo);
				idCliente = prestamo.getCliente().getIdCliente();
							
				cliente = clienteNeg.buscarPorId(idCliente);
				request.setAttribute("cliente", cliente);
				request.setAttribute("prestamo", prestamo);
			}
			catch (SQLException e){
				request.setAttribute("error","No se pudo Obtener El Cliente");
			}
			catch (NumberFormatException e){
				request.setAttribute("error", "ID invalido");
			}	
			
			
			//CAPTURO LAS CUOTAS POR ID PRESTAMO
			try {
				//capturar lista de cuotas por IdPrestamo
				cuotasPrestamo = cuotaNeg.listarCuotasPorPrestamo(idPrestamo);
				request.setAttribute("listaCuotas", cuotasPrestamo);							
			}
			catch (SQLException e){
				request.setAttribute("error","No se pudo Obtener las cuotas");
			}
			catch (NumberFormatException e){
				request.setAttribute("error", "ID PRESTAMO invalido");
			}	
			
			
			//TRAER CUENTAS PARA PAGAR			
			try 
			{			
				//captura la cuenta del cliente
				cuentasCliente = (ArrayList<Cuenta>) cuentaNeg.listarPorCliente(cliente.getIdCliente());
				request.setAttribute("listaCuentas", cuentasCliente);						
			}
			catch (Exception e){
				response.sendRedirect("PagoPrestamo.jsp?mensaje=Error al ObtenerCuentas");
			}				
		
		}else {
			request.setAttribute("error", "No se proporciono un ID");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/PagoPrestamo.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String idPrestamo = request.getParameter("idPrestamo");
		String cuentaString = request.getParameter("cuentas");
		Cuota cuota = new Cuota();
		
		if (cuentaString == null || cuentaString.isEmpty()) {
		    System.out.println("Error: no se seleccionó ninguna cuenta");
		    request.setAttribute("error", "Debe seleccionar una cuenta");
		    RequestDispatcher rd = request.getRequestDispatcher("/PagoPrestamo.jsp");
		    rd.forward(request, response);
		    return;
		}	
			
		//OBTENGO ID CLIENTE
		try {
			
			prestamo = pNeg.obtenerPrestamoPorId(Integer.parseInt(idPrestamo));
			//Cuenta cuenta = cuNeg.obtenerCuentaPorId(Integer.parseInt(cuentaString));
			
			//idCuenta = cuenta.getId();
						
		} catch (SQLException e1) {			
			request.setAttribute("error","No se pudo obtener idCuenta");
		}
		
		
		if("pagarCuotaSeleccionada".equals(accion)) {			
			String cuotaSeleccionada = request.getParameter("cuotas");
			
			cuenta = cuentaNeg.obtenerCuentaPorId(Integer.parseInt(request.getParameter("cuentas")));
						
			if(cuotaSeleccionada != null && !cuotaSeleccionada.isEmpty()) 
			{
				
				try {					
					cuota= cuotaNeg.obtenerCuotaPorId(Integer.parseInt(cuotaSeleccionada));
					cuotaNeg.registrarPago(cuenta.getId(), cuota);
					
					request.setAttribute("mensaje de exito", "Pago Realizado con Exito");
					response.sendRedirect("PagoPrestamoServlet?id=" + idPrestamo);
					return;
					
				}
				catch (SQLException e){
					request.setAttribute("error","No se pudo actualizar la cuotas");
				}catch (Exception e) {
					request.setAttribute("error","Fallo Cuenta CuotaSeleccionada");
				}
				
			}
			
		}
		
		if("pagarTodas".equals(accion)) {
			
			cuenta = cuentaNeg.obtenerCuentaPorId(Integer.parseInt(request.getParameter("cuentas")));
			
			try {
				
				cuotasPrestamo = cuotaNeg.listarCuotasPorPrestamo(Integer.parseInt(idPrestamo));
				for(Cuota cuota1 : cuotasPrestamo) {					
									
					if(!cuota1.getEstadoPago()) {
						cuotaNeg.registrarPago(cuenta.getId(), cuota1);							
					}
				}
				request.setAttribute("mensaje de exito", "Pago Realizado con Exito");
				response.sendRedirect("PagoPrestamoServlet?id=" + idPrestamo);
	            return;
			}
			catch (SQLException e) {
				request.setAttribute("error","No se pudo actualizar la cuotas");
			} catch (Exception e) {
				request.setAttribute("error","Fallo Cuenta PagarTodas");
			}
			
		}
		
		
		
	}	
		
}

package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.TipoCuenta;
import dominio.TipoMovimiento;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.TipoCuentaNegocioImpl;
import negocioImpl.TipoMovimientoNegocioImpl;

/**
 * Servlet implementation class AgregarCuentaServlet
 */
@WebServlet("/AgregarCuentaServlet")
public class AgregarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio negocioCuentas;
    
    public AgregarCuentaServlet() {
        super();
        negocioCuentas = new CuentaNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocioImpl clientes = new ClienteNegocioImpl();
        TipoCuentaNegocioImpl tiposCuenta = new TipoCuentaNegocioImpl();
        
        List<Cliente> listaClientes = clientes.listarTodos();
        List<TipoCuenta> listaTiposCuenta = tiposCuenta.buscarTodos();
        
        Long ultimoNumeroCuenta = negocioCuentas.obtenerUltimoNumeroCuenta();
        Long nuevoNumeroCuenta = ultimoNumeroCuenta + 1;
        
        String ultimoCBU = negocioCuentas.obtenerUltimoCBU();
        BigInteger cbuActual = new BigInteger(ultimoCBU);
        BigInteger nuevoCBUNumero = cbuActual.add(BigInteger.ONE);
        String nuevoCBU = String.format("%022d", nuevoCBUNumero);
        

        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaTiposCuenta", listaTiposCuenta);
        request.setAttribute("nuevaCuenta", nuevoNumeroCuenta);
        request.setAttribute("nuevoCBU", nuevoCBU);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCuenta.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		Cuenta aRegistrar = null;
		try {
			
	        int idCliente = Integer.parseInt(request.getParameter("cliente"));
	        int idTipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
	        Long numeroCuenta = Long.parseLong(request.getParameter("numeroCuenta"));
	        String cbu = request.getParameter("cbu");
	        
	        Cuenta cuenta = new Cuenta();
	        
	        Cliente cliente = new Cliente();
	        cliente.setIdCliente(idCliente);
	        cuenta.setCliente(cliente);
	        
	        TipoCuenta tipoCuenta = new TipoCuenta();
	        tipoCuenta.setId(idTipoCuenta);
	        cuenta.setTipoCuenta(tipoCuenta);
	        
	        cuenta.setNumeroCuenta(numeroCuenta);
	        cuenta.setCbu(cbu);
	        
	        if(negocioCuentas.crearCuenta(cuenta)) {
	        	//Agrego el registro del movimiento
	        	listaCuentas = negocioCuentas.listarActivasPorCliente(cliente.getIdCliente());
	        	aRegistrar = listaCuentas.get(listaCuentas.size() - 1);
	        	generarMovimiento(aRegistrar);
	        			
	        	//----------------------------------
	            request.setAttribute("mensaje", "Cuenta creada exitosamente");
	            request.setAttribute("tipoMensaje", "success");
	        } else {
	            request.setAttribute("mensaje", "Error al crear la cuenta, el cliente no puede tener m√°s de 3 cuentas.");
	            request.setAttribute("tipoMensaje", "danger");
	        }
	    } catch (Exception e) {
	        request.setAttribute("mensaje", "Error al procesar la solicitud: " + e.getMessage());
	        request.setAttribute("tipoMensaje", "danger");
	        e.printStackTrace();
	    }
		
		
			
		
		
          
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionCuentasServlet");
        dispatcher.forward(request, response);
    }
	
	private void generarMovimiento(Cuenta cuenta) {
		
		
		MovimientoNegocioImpl negoMovimiento = new MovimientoNegocioImpl();
		TipoMovimientoNegocioImpl negoTipo = new TipoMovimientoNegocioImpl();
		TipoMovimiento tipo = negoTipo.buscarPorId(1);
		Movimiento movimiento = new Movimiento(
												0,
												cuenta,
												tipo,
												Date.valueOf(LocalDate.now()),
												"Alta de cuentas",
												new BigDecimal(10000)
												//Si la cuenta es nueva solo se va a crear con 10000 que 
												//tiene en el constructor de la clase por lo que si eso cambia ac· tambien.
												//cuenta.getSaldo()
												
				);
		negoMovimiento.insert(movimiento);
		System.out.println(movimiento.toString());
		
		
	}
}

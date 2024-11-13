package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import dao.LocalidadDao;
import dao.NacionalidadDao;
import dao.ProvinciaDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.LocalidadDaoImpl;
import daoImpl.NacionalidadDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import dominio.Cliente;
import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class GestionDatos
 */
@WebServlet("/GestionDatos")
public class GestionDatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NacionalidadNegocio NegocioNacion = new NacionalidadNegocioImpl();
	private ArrayList<Nacionalidad> listaNacionalidades;
	
	private LocalidadNegocio NegocioLocalidad = new LocalidadNegocioImpl();
	private ArrayList<Localidad> listaLocalidades;
	
	private ProvinciaNegocio daoProvincia = new ProvinciaNegocioImpl();
	private ArrayList<Provincia> listaProvincias;
	
    public GestionDatosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Cliente cliente;
		
		if(request.getAttribute("cliente") != null) {
			cliente = (Cliente)request.getAttribute("cliente");
		}
			
		
		String action = request.getParameter("action");
		int clienteId = Integer.parseInt(request.getParameter("clienteId"));
		ClienteNegocio negoCliente = new ClienteNegocioImpl();
		
		cliente = negoCliente.buscarPorId(clienteId);
		request.setAttribute("cliente", cliente);
		request.setAttribute("action", action);

		RequestDispatcher rd = request.getRequestDispatcher("/GestionDatos.jsp");
		rd.forward(request, response);
		if(request.getAttribute("btnEditar") != null) {
			gestionCliente(request, response, true);
		}else if(request.getAttribute("btnInsertar") != null) {
			gestionCliente(request, response, false);
		}
		   
	}
	
	
	private void gestionCliente(HttpServletRequest request, HttpServletResponse response, boolean accion) {
		ClienteNegocio negoCliente = new ClienteNegocioImpl();
    	
    	Cliente cliente = (Cliente)request.getAttribute("cliente");
    	
    	Provincia provi = daoProvincia.buscarPorId(cliente.getProvincia().getId());
    	Nacionalidad nacio = NegocioNacion.buscarPorId(cliente.getNacionalidad().getId());
    	Localidad loca = NegocioLocalidad.buscarPorId(cliente.getLocalidad().getId());
    	int id = cliente.getIdCliente();
    	
    	cliente.setApellido((String)request.getAttribute("apellido"));
    	cliente.setNombre((String)request.getAttribute("nombre"));
    	cliente.setCorreoElectronico((String)request.getAttribute("email"));
    	cliente.setDni((String)request.getAttribute("dni"));
    	cliente.setCuil((String)request.getAttribute("cuil"));
    	cliente.setDireccion((String)request.getAttribute("direccion"));
    	cliente.setFechaNacimiento((Date)request.getAttribute("fechaNacimiento"));
    	cliente.setGenero((String)request.getAttribute("genero"));
    	cliente.setTelefono((String)request.getAttribute("telefono"));
    	cliente.setProvincia(provi);
    	cliente.setLocalidad(loca);
    	cliente.setNacionalidad(nacio);
    	//La idea es que en el post o get se llame a este metodo con el boolean, si es true edita, sino inserta.
    	if(accion) {
    		negoCliente.update(cliente);    		
    	}else {
    		negoCliente.insert(cliente);
    	}
    	
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/GestionDatos.jsp");
    	
	}

}

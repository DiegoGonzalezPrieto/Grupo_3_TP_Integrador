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
import dominio.TipoUsuario;
import dominio.Usuario;
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocio.ProvinciaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class GestionDatos
 */
@WebServlet("/GestionDatosServlet")
public class GestionDatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NacionalidadNegocio NegocioNacion = new NacionalidadNegocioImpl();
	private ArrayList<Nacionalidad> listaNacionalidades;

	private LocalidadNegocio NegocioLocalidad = new LocalidadNegocioImpl();
	private ArrayList<Localidad> listaLocalidades;

	private ProvinciaNegocio daoProvincia = new ProvinciaNegocioImpl();
	private ArrayList<Provincia> listaProvincias;

	ClienteNegocio negoCliente;
	UsuarioNegocio negoUsuario;

	public GestionDatosServlet() {
		super();
		negoCliente = new ClienteNegocioImpl();
		negoUsuario = new UsuarioNegocioImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			if (request.getParameter("nuevo") != null) {
				// obtener datos select y redirigir a GestionDatos.jsp
				request.setAttribute("provincias", daoProvincia.buscarTodos());
				request.setAttribute("localidades", NegocioLocalidad.buscarTodos());
				request.setAttribute("naciones", NegocioNacion.buscarTodos());
				request.setAttribute("nuevo", true);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionDatos.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if(request.getParameter("id") != null) {
				int idCliente = Integer.parseInt(request.getParameter("id"));
				
				Cliente cliente = negoCliente.buscarPorId(idCliente);

				if (cliente != null) {
	
					request.setAttribute("cliente", cliente);
	
					RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosCliente.jsp");
					dispatcher.forward(request, response);
	
				} else {
	
					request.setAttribute("mensaje", "No se encontró el cliente");
					response.sendRedirect("AdministracionClientesServlet");
				}
				
			}else if(request.getParameter("delete") != null) {
				
				int idCliente = Integer.parseInt(request.getParameter("delete"));
					if (idCliente != 0) {
						
						negoCliente.delete(idCliente);
		
						RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionClientes.jsp");
						dispatcher.forward(request, response);
		
					} else {
		
						request.setAttribute("mensaje", "No se pudo eliminar el cliente");
						response.sendRedirect("AdministracionClientesServlet");
					}
					
				}

		} catch (Exception e) {
			request.setAttribute("mensaje", "Error al cargar el cliente: " + e.getMessage());
			response.sendRedirect("AdministracionClientesServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		Cliente cliente;

		crearCliente(request, response, false);
		
		RequestDispatcher rd = request.getRequestDispatcher("/AdministracionClientes.jsp");
		rd.forward(request, response);
		// COMENTO AC� PORQUE NO SE QUE HACE - USO EL POST PARA CREAR/EDITAR USUARIO
		// (Diego)

		// if(request.getAttribute("cliente") != null) {
		// cliente = (Cliente)request.getAttribute("cliente");
		// }
		//
		//
		// String action = request.getParameter("action");
		// int clienteId = Integer.parseInt(request.getParameter("clienteId"));
		// ClienteNegocio negoCliente = new ClienteNegocioImpl();
		//
		// cliente = negoCliente.buscarPorId(clienteId);
		// request.setAttribute("cliente", cliente);
		// request.setAttribute("action", action);
		//
		// RequestDispatcher rd = request.getRequestDispatcher("/GestionDatos.jsp");
		// rd.forward(request, response);
		// if(request.getAttribute("btnEditar") != null) {
		// gestionCliente(request, response, true);
		// }else if(request.getAttribute("btnInsertar") != null) {
		// gestionCliente(request, response, false);
		// }

	}

	private void gestionCliente(HttpServletRequest request, HttpServletResponse response, boolean accion) {
		ClienteNegocio negoCliente = new ClienteNegocioImpl();

		Cliente cliente = (Cliente) request.getAttribute("cliente");

		Provincia provi = daoProvincia.buscarPorId(cliente.getProvincia().getId());
		Nacionalidad nacio = NegocioNacion.buscarPorId(cliente.getNacionalidad().getId());
		Localidad loca = NegocioLocalidad.buscarPorId(cliente.getLocalidad().getId());
		int id = cliente.getIdCliente();

		cliente.setApellido((String) request.getAttribute("apellido"));
		cliente.setNombre((String) request.getAttribute("nombre"));
		cliente.setCorreoElectronico((String) request.getAttribute("email"));
		cliente.setDni((String) request.getAttribute("dni"));
		cliente.setCuil((String) request.getAttribute("cuil"));
		cliente.setDireccion((String) request.getAttribute("direccion"));
		cliente.setFechaNacimiento((Date) request.getAttribute("fechaNacimiento"));
		cliente.setGenero((String) request.getAttribute("genero"));
		cliente.setTelefono((String) request.getAttribute("telefono"));
		cliente.setProvincia(provi);
		cliente.setLocalidad(loca);
		cliente.setNacionalidad(nacio);
		// La idea es que en el post o get se llame a este metodo con el boolean, si es
		// true edita, sino inserta.
		if (accion) {
			negoCliente.update(cliente);
		} else {
			negoCliente.insert(cliente);
		}

	}

	/*
	 * Crea un cliente levantando los datos de la request.
	 * Luego podr�a editar
	 */
	private void crearCliente(HttpServletRequest request, HttpServletResponse response, boolean editar) {
		ClienteNegocio negoCliente = new ClienteNegocioImpl();

		Cliente cliente = new Cliente();
		cliente.setEstadoUsuario(true);
		cliente.setNombreUsuario(request.getParameter("usuario"));
		cliente.setPass(request.getParameter("pass"));
		cliente.setTipoUsuario(new TipoUsuario(1, "cliente"));
		Usuario u = new Usuario(0,request.getParameter("usuario"),request.getParameter("pass"),new TipoUsuario(1, "cliente"), true);
		negoUsuario.agregarUsuario(u);
		cliente.setId(negoUsuario.buscarPorNombre(request.getParameter("usuario")).getId());

		Provincia provi = daoProvincia.buscarPorId(Integer.parseInt(request.getParameter("provincia")));
		Nacionalidad nacio = NegocioNacion.buscarPorId(Integer.parseInt(request.getParameter("nacionalidad")));
		Localidad loca = NegocioLocalidad.buscarPorId(Integer.parseInt(request.getParameter("localidad")));
		int id = cliente.getIdCliente();

		String[] fragmentosFecha = request.getParameter("fechaNacimiento").split("-");
		Date fechaNacimiento = new Date(Integer.parseInt(fragmentosFecha[0]) - 1900,
				Integer.parseInt(fragmentosFecha[1]) - 1, Integer.parseInt(fragmentosFecha[2]));

		cliente.setApellido((String) request.getParameter("apellido"));
		cliente.setNombre((String) request.getParameter("nombre"));
		cliente.setCorreoElectronico((String) request.getParameter("email"));
		cliente.setDni((String) request.getParameter("dni"));
		cliente.setCuil((String) request.getParameter("cuil"));
		cliente.setDireccion((String) request.getParameter("direccion"));
		cliente.setFechaNacimiento(fechaNacimiento);
		cliente.setGenero((String) request.getParameter("genero"));
		cliente.setTelefono((String) request.getParameter("telefono"));
		cliente.setProvincia(provi);
		cliente.setLocalidad(loca);
		cliente.setNacionalidad(nacio);
		
		if (editar) {
			negoCliente.update(cliente);
		} else {
			negoCliente.insert(cliente);
		}

		

	}

}

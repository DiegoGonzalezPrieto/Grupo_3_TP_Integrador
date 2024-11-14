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
import dao.UsuarioDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.LocalidadDaoImpl;
import daoImpl.NacionalidadDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.UsuarioDaoImpl;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String dni = request.getParameter("dni");
	        String cuil = request.getParameter("cuil");
	        String user = request.getParameter("usuario");
	        
	        if(negoCliente.existeDNI(dni)) {
	            request.getSession().setAttribute("mensaje", "El DNI ya existe en la base de datos");
	            request.getSession().setAttribute("tipoMensaje", "danger");
	            response.sendRedirect("GestionDatosServlet?nuevo=true");
	            return;
	        }
	        
	        if(negoCliente.existeCUIL(cuil)) {
	            request.getSession().setAttribute("mensaje", "El CUIL ya existe en la base de datos, verifique que sea correcto.");
	            request.getSession().setAttribute("tipoMensaje", "danger");
	            response.sendRedirect("GestionDatosServlet?nuevo=true");
	            return;
	        }
	        
	        if(negoUsuario.existeUsuario(user)) {
	            request.getSession().setAttribute("mensaje", "El Usuario ya existe en la base de datos, intente con otro usuario.");
	            request.getSession().setAttribute("tipoMensaje", "danger");
	            response.sendRedirect("GestionDatosServlet?nuevo=true");
	            return;
	        }
	        
	        
	        crearCliente(request, response, false);
	    } catch (Exception e) {
	        request.setAttribute("mensaje", "Error: " + e.getMessage());
	        request.setAttribute("tipoMensaje", "danger");
	        e.printStackTrace();
	    }
	        
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
	private void crearCliente(HttpServletRequest request, HttpServletResponse response, boolean editar) throws ServletException, IOException  {
		try {
			Cliente cliente = new Cliente();
			
	        cliente.setApellido(request.getParameter("apellido"));
	        cliente.setNombre(request.getParameter("nombre"));
	        cliente.setCorreoElectronico(request.getParameter("email"));
	        cliente.setDni(request.getParameter("dni"));
	        cliente.setCuil(request.getParameter("cuil"));
	        cliente.setDireccion(request.getParameter("direccion"));
	        
	        String[] fragmentosFecha = request.getParameter("fechaNacimiento").split("-");
	        Date fechaNacimiento = new Date(Integer.parseInt(fragmentosFecha[0]) - 1900,
	                Integer.parseInt(fragmentosFecha[1]) - 1, Integer.parseInt(fragmentosFecha[2]));
	        cliente.setFechaNacimiento(fechaNacimiento);
	        
	        cliente.setGenero(request.getParameter("genero"));
	        cliente.setTelefono(request.getParameter("telefono"));
	        
	
	        cliente.setProvincia(daoProvincia.buscarPorId(Integer.parseInt(request.getParameter("provincia"))));
	        cliente.setNacionalidad(NegocioNacion.buscarPorId(Integer.parseInt(request.getParameter("nacionalidad"))));
	        cliente.setLocalidad(NegocioLocalidad.buscarPorId(Integer.parseInt(request.getParameter("localidad"))));

	        Usuario usuario = new Usuario();
	        usuario.setNombreUsuario(request.getParameter("usuario"));
	        usuario.setPass(request.getParameter("pass"));
	        usuario.setTipoUsuario(new TipoUsuario(1, "cliente"));
	        usuario.setEstadoUsuario(true);
	        
	        try {
	            negoUsuario.agregarUsuario(usuario);
	
	            Usuario usuarioCreado = negoUsuario.buscarPorNombre(request.getParameter("usuario"));
	            if(usuarioCreado != null) {
	                cliente.setId(usuarioCreado.getId());
	                cliente.setEstadoUsuario(true);
	                
	                negoCliente.insert(cliente);
	            	request.getSession().setAttribute("mensaje", "Cliente creado exitosamente");
	                request.getSession().setAttribute("tipoMensaje", "success");
	            } else {
	                request.setAttribute("mensaje", "Error al crear el usuario");
	                request.setAttribute("tipoMensaje", "danger");
	            }
	        } catch(Exception e) {
	        	request.getSession().setAttribute("mensaje", "Error: " + e.getMessage());
	            request.getSession().setAttribute("tipoMensaje", "danger");
	            e.printStackTrace();
	        }
        
	    } catch (Exception e) {
	        request.setAttribute("mensaje", "Error: " + e.getMessage());
	        request.setAttribute("tipoMensaje", "danger");
	        e.printStackTrace();
	    }
	    
	    response.sendRedirect("AdministracionClientesServlet");

	}

}

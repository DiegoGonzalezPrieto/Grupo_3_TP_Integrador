package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
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
import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Prestamo;
import dominio.Provincia;
import dominio.TipoUsuario;
import dominio.Usuario;
import exceptions.CuilInvalidoException;
import exceptions.DNIInvalidoException;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocio.PrestamoNegocio;
import negocio.ProvinciaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
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

	private static final int RECHAZADO = 3;

	ClienteNegocio negoCliente;
	UsuarioNegocio negoUsuario;
	CuentaNegocio negocioCuentas;
	PrestamoNegocio negoPrestamo;

	public GestionDatosServlet() {
		super();
		negoCliente = new ClienteNegocioImpl();
		negoUsuario = new UsuarioNegocioImpl();
		negocioCuentas = new CuentaNegocioImpl();
		negoPrestamo = new PrestamoNegocioImpl();
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

			if (request.getParameter("id") != null) {
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

			} else if (request.getParameter("delete") != null) {

				int idCliente = Integer.parseInt(request.getParameter("delete"));

				if (idCliente != 0) {
					List<Cuenta> listCuentasCliente = negocioCuentas.listarPorCliente(idCliente);

					for (Cuenta cuenta : listCuentasCliente) {
						if (!negoCliente.cantCoutasImpagas(idCliente)) {
							request.getSession().setAttribute("mensaje",
									"El cliente tiene coutas de prestamo impagas, primero debe saldar toda las cuotas.");
							request.getSession().setAttribute("tipoMensaje", "warning");
							response.sendRedirect("AdministracionClientesServlet");
							return;
						}
						if (cuenta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
							request.getSession().setAttribute("mensaje",
									"No se puede eliminar el cliente porque la cuenta " + cuenta.getNumeroCuenta()
											+ " debe tener saldo $0 (saldo actual: $"
											+ String.format("%,.2f", cuenta.getSaldo()) + ")");
							request.getSession().setAttribute("tipoMensaje", "warning");

							response.sendRedirect("AdministracionClientesServlet");
							return;
						}
					}

					List<Prestamo> prestamosPendientes = negoPrestamo.listarPrestamosPendientesXCliente(idCliente);
					for (Prestamo prestamo : prestamosPendientes) {
						negoPrestamo.actualizarEstadoSolicitud(prestamo.getId(), RECHAZADO);
					}

					for (Cuenta cuenta : listCuentasCliente) {
						negocioCuentas.eliminarCuenta(cuenta.getId());
					}

					negoCliente.delete(idCliente);

					request.getSession().setAttribute("mensaje", "Cliente eliminado exitosamente");
					request.getSession().setAttribute("tipoMensaje", "success");

					RequestDispatcher dispatcher = request.getRequestDispatcher("/AdministracionClientes.jsp");
					dispatcher.forward(request, response);

				} else {

					request.setAttribute("mensaje", "No se pudo eliminar el cliente");
					response.sendRedirect("AdministracionClientesServlet");
				}

			} else if (request.getParameter("editar") != null) {

				int idCliente = Integer.parseInt(request.getParameter("editar"));
				Cliente cliente = negoCliente.buscarPorId(idCliente);

				request.setAttribute("clienteEditar", cliente);
				request.setAttribute("nuevo", false);
				request.setAttribute("provincias", daoProvincia.buscarTodos());
				request.setAttribute("localidades", NegocioLocalidad.buscarTodos());
				request.setAttribute("naciones", NegocioNacion.buscarTodos());

				RequestDispatcher dispatcher = request.getRequestDispatcher("GestionDatos.jsp");
				dispatcher.forward(request, response);

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

		if (request.getParameter("crear") != null) {

			validarYCrearCliente(request, response);
		} else if (request.getParameter("editar") != null) {
			validarYEditarCliente(request, response);
		}

	}

	private void validarYCrearCliente(HttpServletRequest request, HttpServletResponse response) {
		try {

			// Agregado para rellenar el formulario en caso de error de validaci�n al crear
			Cliente clienteParcial = obtenerCliente(request);
			request.setAttribute("clienteParcial", clienteParcial);

			String dni = request.getParameter("dni");
			String cuil = request.getParameter("cuil");
			String user = request.getParameter("usuario");

			if (negoCliente.existeDNI(dni)) {
				request.getSession().setAttribute("mensaje", "El DNI ya existe en la base de datos");
				request.getSession().setAttribute("tipoMensaje", "danger");

				datosFormulario(request, true);
				// response.sendRedirect("GestionDatosServlet?nuevo=true");
				// return;
				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionDatos.jsp");
				dispatcher.forward(request, response);
				return;
			}

			if (negoCliente.existeCUIL(cuil)) {
				request.getSession().setAttribute("mensaje",
						"El CUIL ya existe en la base de datos, verifique que sea correcto.");
				request.getSession().setAttribute("tipoMensaje", "danger");

				datosFormulario(request, true);
				// response.sendRedirect("GestionDatosServlet?nuevo=true");
				// return;
				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionDatos.jsp");
				dispatcher.forward(request, response);
				return;
			}

			if (negoUsuario.existeUsuario(user)) {
				request.getSession().setAttribute("mensaje",
						"El Usuario ya existe en la base de datos, intente con otro usuario.");
				request.getSession().setAttribute("tipoMensaje", "danger");

				datosFormulario(request, true);
				// response.sendRedirect("GestionDatosServlet?nuevo=true");
				// return;
				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionDatos.jsp");
				dispatcher.forward(request, response);
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
	 * Crea un cliente levantando los datos de la request. Luego podr�a editar
	 */
	private void crearCliente(HttpServletRequest request, HttpServletResponse response, boolean editar)
			throws ServletException, IOException {
		try {

			String dni = request.getParameter("dni");
			String cuil = request.getParameter("cuil");
			String nombreUsuario = request.getParameter("usuario");
			String pass = request.getParameter("pass");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");

			if (!dni.matches("[0-9]{8}")) {
				throw new DNIInvalidoException();
			}

			if (!cuil.matches("[0-9]{11}")) {
				throw new CuilInvalidoException();
			}

			Cliente cliente = new Cliente();
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellido"));
			cliente.setDni(dni);
			cliente.setCuil(cuil);
			cliente.setCorreoElectronico(email);
			cliente.setTelefono(telefono);
			cliente.setDireccion(request.getParameter("direccion"));
			cliente.setGenero(request.getParameter("genero"));

			String[] fragmentosFecha = request.getParameter("fechaNacimiento").split("-");
			Date fechaNacimiento = new Date(Integer.parseInt(fragmentosFecha[0]) - 1900,
					Integer.parseInt(fragmentosFecha[1]) - 1, Integer.parseInt(fragmentosFecha[2]));
			cliente.setFechaNacimiento(fechaNacimiento);

			cliente.setProvincia(daoProvincia.buscarPorId(Integer.parseInt(request.getParameter("provincia"))));
			cliente.setNacionalidad(NegocioNacion.buscarPorId(Integer.parseInt(request.getParameter("nacionalidad"))));
			cliente.setLocalidad(NegocioLocalidad.buscarPorId(Integer.parseInt(request.getParameter("localidad"))));

			Usuario usuario = new Usuario();
			usuario.setNombreUsuario(nombreUsuario);
			usuario.setPass(pass);
			usuario.setTipoUsuario(new TipoUsuario(1, "cliente"));
			usuario.setEstadoUsuario(true);

			try {
				negoUsuario.agregarUsuario(usuario);
				Usuario usuarioCreado = negoUsuario.buscarPorNombre(nombreUsuario);

				if (usuarioCreado != null) {
					cliente.setId(usuarioCreado.getId());
					cliente.setEstadoUsuario(true);

					negoCliente.insert(cliente);
					request.getSession().setAttribute("mensaje", "Cliente creado exitosamente");
					request.getSession().setAttribute("tipoMensaje", "success");
					response.sendRedirect("AdministracionClientesServlet");

				} else {
					throw new Exception("Error al crear el usuario");
				}
			} catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error: " + e.getMessage());
				request.getSession().setAttribute("tipoMensaje", "danger");
				datosFormulario(request, true);
				request.getRequestDispatcher("/GestionDatos.jsp").forward(request, response);
				return;
			}

		} catch (DNIInvalidoException | CuilInvalidoException e) {
			request.getSession().setAttribute("mensaje", e.getMessage());
			request.getSession().setAttribute("tipoMensaje", "danger");
			datosFormulario(request, true);
			request.getRequestDispatcher("/GestionDatos.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error inesperado: " + e.getMessage());
			request.getSession().setAttribute("tipoMensaje", "danger");
			e.printStackTrace();
			datosFormulario(request, true);
			request.getRequestDispatcher("/GestionDatos.jsp").forward(request, response);
			return;
		}
	}

	private void datosFormulario(HttpServletRequest request, boolean nuevo) {
		try {

			request.setAttribute("provincias", daoProvincia.buscarTodos());
			request.setAttribute("localidades", NegocioLocalidad.buscarTodos());
			request.setAttribute("naciones", NegocioNacion.buscarTodos());

			Cliente cliente = new Cliente();
			cliente.setNombreUsuario(request.getParameter("usuario"));
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellido"));
			cliente.setDni(request.getParameter("dni"));
			cliente.setCuil(request.getParameter("cuil"));
			cliente.setCorreoElectronico(request.getParameter("email"));
			cliente.setTelefono(request.getParameter("telefono"));
			cliente.setDireccion(request.getParameter("direccion"));
			cliente.setGenero(request.getParameter("genero"));
			cliente.setPass(request.getParameter("pass"));

			String idCliente = request.getParameter("idCliente");
			if (!nuevo && idCliente != null && !idCliente.isEmpty()) {
				int id = Integer.parseInt(idCliente);
				cliente.setId(id);
				cliente.setIdCliente(id);
				request.setAttribute("idCliente", id);
			}

			String[] fecha = request.getParameter("fechaNacimiento").split("-");
			if (fecha.length == 3) {
				Date fechaNacimiento = new Date(Integer.parseInt(fecha[0]) - 1900, Integer.parseInt(fecha[1]) - 1,
						Integer.parseInt(fecha[2]));
				cliente.setFechaNacimiento(fechaNacimiento);
			}

			String provinciaId = request.getParameter("provincia");
			if (provinciaId != null && !provinciaId.isEmpty()) {
				Provincia provincia = daoProvincia.buscarPorId(Integer.parseInt(provinciaId));
				cliente.setProvincia(provincia);
			}

			String localidadId = request.getParameter("localidad");
			if (localidadId != null && !localidadId.isEmpty()) {
				Localidad localidad = NegocioLocalidad.buscarPorId(Integer.parseInt(localidadId));
				cliente.setLocalidad(localidad);
			}

			String nacionalidadId = request.getParameter("nacionalidad");
			if (nacionalidadId != null && !nacionalidadId.isEmpty()) {
				Nacionalidad nacionalidad = NegocioNacion.buscarPorId(Integer.parseInt(nacionalidadId));
				cliente.setNacionalidad(nacionalidad);
			}

			if (nuevo) {
				request.setAttribute("clienteParcial", cliente);
			} else {
				request.setAttribute("clienteEditar", cliente);
			}

			request.setAttribute("nuevo", nuevo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validarYEditarCliente(HttpServletRequest request, HttpServletResponse response) {

		String dni = request.getParameter("dni");
		String cuil = request.getParameter("cuil");
		String user = request.getParameter("usuario");

		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		Cliente clienteEditar = negoCliente.buscarPorId(idCliente);

		try {

			if (negoCliente.existeDNI(dni) && !dni.equals(clienteEditar.getDni())) {
				request.getSession().setAttribute("mensaje", "El DNI ya existe en la base de datos");
				request.getSession().setAttribute("tipoMensaje", "danger");

				request.setAttribute("idCliente", idCliente);
				datosFormulario(request, false);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionDatos.jsp");
				dispatcher.forward(request, response);
				return;
			}

			if (negoCliente.existeCUIL(cuil) && !cuil.equals(clienteEditar.getCuil())) {
				request.getSession().setAttribute("mensaje",
						"El CUIL ya existe en la base de datos, verifique que sea correcto.");
				request.getSession().setAttribute("tipoMensaje", "danger");

				datosFormulario(request, false);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionDatos.jsp");
				dispatcher.forward(request, response);
				return;
			}

			if (negoUsuario.existeUsuario(user) && !user.equals(clienteEditar.getNombreUsuario())) {
				request.getSession().setAttribute("mensaje",
						"El Usuario ya existe en la base de datos, intente con otro usuario.");
				request.getSession().setAttribute("tipoMensaje", "danger");

				datosFormulario(request, false);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestionDatos.jsp");
				dispatcher.forward(request, response);
				return;
			}

			editarCliente(request, response, clienteEditar);

		} catch (Exception e) {
			request.setAttribute("mensaje", "Error: " + e.getMessage());
			request.setAttribute("tipoMensaje", "danger");
			try {
				datosFormulario(request, false);
				request.getRequestDispatcher("/GestionDatos.jsp").forward(request, response);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private void editarCliente(HttpServletRequest request, HttpServletResponse response, Cliente clienteAEditar)
			throws ServletException, IOException {

		try {
			String dni = request.getParameter("dni");
			String cuil = request.getParameter("cuil");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");

			if (!dni.matches("[0-9]{8}")) {
				throw new DNIInvalidoException();
			}

			if (!cuil.matches("[0-9]{11}")) {
				throw new CuilInvalidoException();
			}

			clienteAEditar.setNombre(request.getParameter("nombre"));
			clienteAEditar.setApellido(request.getParameter("apellido"));
			clienteAEditar.setDni(dni);
			clienteAEditar.setCuil(cuil);
			clienteAEditar.setCorreoElectronico(email);
			clienteAEditar.setTelefono(telefono);
			clienteAEditar.setDireccion(request.getParameter("direccion"));
			clienteAEditar.setGenero(request.getParameter("genero"));

			String[] fragmentosFecha = request.getParameter("fechaNacimiento").split("-");
			Date fechaNacimiento = new Date(Integer.parseInt(fragmentosFecha[0]) - 1900,
					Integer.parseInt(fragmentosFecha[1]) - 1, Integer.parseInt(fragmentosFecha[2]));
			clienteAEditar.setFechaNacimiento(fechaNacimiento);

			clienteAEditar.setProvincia(daoProvincia.buscarPorId(Integer.parseInt(request.getParameter("provincia"))));
			clienteAEditar
					.setNacionalidad(NegocioNacion.buscarPorId(Integer.parseInt(request.getParameter("nacionalidad"))));
			clienteAEditar
					.setLocalidad(NegocioLocalidad.buscarPorId(Integer.parseInt(request.getParameter("localidad"))));

			negoCliente.update(clienteAEditar);

			request.getSession().setAttribute("mensaje", "Cliente editado exitosamente");
			request.getSession().setAttribute("tipoMensaje", "success");
			response.sendRedirect("AdministracionClientesServlet");
			return;

		} catch (DNIInvalidoException | CuilInvalidoException e) {
			request.getSession().setAttribute("mensaje", e.getMessage());
			request.getSession().setAttribute("tipoMensaje", "danger");
			request.setAttribute("idCliente", clienteAEditar.getIdCliente());
			datosFormulario(request, false);
			request.getRequestDispatcher("/GestionDatos.jsp").forward(request, response);

		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error inesperado: " + e.getMessage());
			request.getSession().setAttribute("tipoMensaje", "danger");
			e.printStackTrace();
			datosFormulario(request, false);
			request.getRequestDispatcher("/GestionDatos.jsp").forward(request, response);
		}

	}

	private Cliente obtenerCliente(HttpServletRequest request) {
		request.setAttribute("provincias", daoProvincia.buscarTodos());
		request.setAttribute("localidades", NegocioLocalidad.buscarTodos());
		request.setAttribute("naciones", NegocioNacion.buscarTodos());

		Cliente cliente = new Cliente();
		cliente.setNombreUsuario(request.getParameter("usuario"));
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido(request.getParameter("apellido"));
		cliente.setDni(request.getParameter("dni"));
		cliente.setCuil(request.getParameter("cuil"));
		cliente.setCorreoElectronico(request.getParameter("email"));
		cliente.setTelefono(request.getParameter("telefono"));
		cliente.setDireccion(request.getParameter("direccion"));
		cliente.setGenero(request.getParameter("genero"));
		cliente.setPass(request.getParameter("pass"));
		String[] fragmentosFecha = request.getParameter("fechaNacimiento").split("-");
		Date fechaNacimiento = new Date(Integer.parseInt(fragmentosFecha[0]) - 1900,
				Integer.parseInt(fragmentosFecha[1]) - 1, Integer.parseInt(fragmentosFecha[2]));
		cliente.setFechaNacimiento(fechaNacimiento);

		String provinciaId = request.getParameter("provincia");
		if (provinciaId != null && !provinciaId.isEmpty()) {
			Provincia provincia = daoProvincia.buscarPorId(Integer.parseInt(provinciaId));
			cliente.setProvincia(provincia);
		}

		String localidadId = request.getParameter("localidad");
		if (localidadId != null && !localidadId.isEmpty()) {
			Localidad localidad = NegocioLocalidad.buscarPorId(Integer.parseInt(localidadId));
			cliente.setLocalidad(localidad);
		}

		String nacionalidadId = request.getParameter("nacionalidad");
		if (nacionalidadId != null && !nacionalidadId.isEmpty()) {
			Nacionalidad nacionalidad = NegocioNacion.buscarPorId(Integer.parseInt(nacionalidadId));
			cliente.setNacionalidad(nacionalidad);
		}

		return cliente;

	}

}

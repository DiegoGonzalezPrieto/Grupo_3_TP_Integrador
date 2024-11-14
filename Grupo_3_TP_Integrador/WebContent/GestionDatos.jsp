<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="dominio.Cliente"%>
<%@page import="dominio.Nacionalidad"%>
<%@page import="dominio.Localidad"%>
<%@page import="dominio.Provincia"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Administración de Clientes</title>
<!-- JQuery + Datatables -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
<link
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css"
	rel="stylesheet"></link>
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-5">

		<%
			Cliente cliente = new Cliente();
			cliente = (Cliente) request.getAttribute("cliente");

			ArrayList<Nacionalidad> listaNaciones = null;
			ArrayList<Localidad> listaLocalidades = null;
			ArrayList<Provincia> listaProvincias = null;
			if (request.getAttribute("localidades") != null) {
				listaLocalidades = (ArrayList<Localidad>) request.getAttribute("localidades");
			}
			if (request.getAttribute("provincias") != null) {
				listaProvincias = (ArrayList<Provincia>) request.getAttribute("provincias");
			}

			if (request.getAttribute("naciones") != null) {
				listaNaciones = (ArrayList<Nacionalidad>) request.getAttribute("naciones");
			}
		%>

		<%
			String encabezado = "Datos Personales";
			String accion = "editar";
			if (request.getAttribute("nuevo") != null) {
				encabezado = "Nuevo Cliente";
				accion = "crear";
			}
		%>
		
		<%
		    String mensaje = (String) session.getAttribute("mensaje");
		    String tipoMensaje = (String) session.getAttribute("tipoMensaje");
		    if(mensaje != null && tipoMensaje != null) {
		        session.removeAttribute("mensaje");
		        session.removeAttribute("tipoMensaje");
		    %>
		        <div class="alert alert-<%=tipoMensaje%> alert-dismissible fade show" role="alert">
		            <%=mensaje%>
		            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		        </div>
		    <%
		    }
	    %>
		<h2><%=encabezado%></h2>
		<!-- En el JSP: -->
		<form action="GestionDatosServlet" method="POST">

			<div class="form-group">
				<label for="usuario">Usuario:</label> <input type="text"
					class="form-control" id="usuario" name="usuario"
					value="<%=cliente == null ? "" : cliente.getNombreUsuario()%>"
					pattern="[A-Za-z0-9]{4,20}"
					title="El usuario debe tener entre 4 y 20 caracteres alfanuméricos"
					required>
			</div>

			<div class="form-group">
				<label for="pass">Contraseña:</label> <input type="password"
					class="form-control" id="pass" name="pass"
					value="<%=cliente == null ? "" : cliente.getPass()%>"
					pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
					title="La contraseña debe tener al menos 8 caracteres, incluyendo letras y números"
					required>
			</div>

			<div class="form-group">
				<label for="nombre">Nombre:</label> <input type="text"
					class="form-control" id="nombre" name="nombre"
					value="<%=cliente == null ? "" : cliente.getNombre()%>"
					pattern="[A-Za-zÀ-ÿ\s]{2,50}"
					title="Ingrese un nombre válido (solo letras)" required>
			</div>

			<div class="form-group">
				<label for="apellido">Apellido:</label> <input type="text"
					class="form-control" id="apellido" name="apellido"
					value="<%=cliente == null ? "" : cliente.getApellido()%>"
					pattern="[A-Za-zÀ-ÿ\s]{2,50}"
					title="Ingrese un apellido válido (solo letras)" required>
			</div>

			<div class="form-group">
				<label for="dni">DNI:</label> <input type="text"
					class="form-control" id="dni" name="dni"
					value="<%=cliente == null ? "" : cliente.getDni()%>"
					pattern="[0-9]{8}" title="El DNI debe tener exactamente 8 dígitos"
					required>
			</div>

			<div class="form-group">
				<label for="cuil">CUIL:</label> <input type="text"
					class="form-control" id="cuil" name="cuil"
					value="<%=cliente == null ? "" : cliente.getCuil()%>"
					pattern="[0-9]{11}"
					title="El CUIL debe tener exactamente 11 dígitos" required>
			</div>
			<div class="form-group">
				<label for="nombre">Genero:</label> <input type="text"
					class="form-control" id="genero" name="genero"
					value="<%=cliente == null ? "" : cliente.getGenero()%>">
			</div>

			<div class="form-group">
				<label for="email">E-mail:</label> <input type="email"
					class="form-control" id="email" name="email"
					value="<%=cliente == null ? "" : cliente.getCorreoElectronico()%>"
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
					title="Ingrese un email válido" required>
			</div>

			<div class="form-group">
				<label for="telefono">Teléfono:</label> <input type="text"
					class="form-control" id="telefono" name="telefono"
					value="<%=cliente == null ? "" : cliente.getTelefono()%>"
					pattern="[0-9]{10}" title="El teléfono debe tener 10 dígitos"
					required>
			</div>

			<div class="form-group">
				<label for="nombre">Fecha de nacimiento:</label> <input type="date"
					class="form-control" id="fechaNacimiento" name="fechaNacimiento"
					value="<%=cliente == null ? "" : cliente.getFechaNacimiento()%>"
					max="<%=LocalDate.now().minusYears(18)%>"
					title="Debe ser mayor de 18 años" required>
			</div>

			<div class="form-group">
				<label for="direccion">Dirección:</label> <input type="text"
					class="form-control" id="direccion" name="direccion"
					value="<%=cliente == null ? "" : cliente.getDireccion()%>"
					minlength="5" maxlength="100"
					title="La dirección debe tener entre 5 y 100 caracteres" required>
			</div>

			<div class="form-group">
				<label for="provincia">Provincia:</label> <select
					class="form-select form-select-sm" name="provincia" id="provincia"
					onchange="filtrarLocalidades(this.value)">
					<%
						if (cliente != null) {
					%>
					<option selected value="<%=cliente.getProvincia().getId()%>"><%=cliente.getProvincia().getNombre()%></option>

					<%
						}
					%>

					<%
						if (listaProvincias != null) {
							for (Provincia pro : listaProvincias) {
					%>
					<option value="<%=pro.getId()%>"><%=pro.getNombre()%></option>
					<%
						}
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="localidad">Localidad:</label> <select
					class="form-select form-select-sm" name="localidad" id="localidad">
					<%
						if (cliente != null) {
					%>
					<option value="<%=cliente.getLocalidad().getId()%>" selected><%=cliente.getLocalidad().getNombre()%></option>

					<%
						}
					%>
					<%
						if (listaLocalidades != null) {
							for (Localidad lo : listaLocalidades) {
					%>
					<option value="<%=lo.getId()%>"><%=lo.getNombre()%></option>
					<%
						}
						}
					%>
				</select>
			</div>

			<div class="form-group">
				<label for="nacionalidad">Nacionalidad:</label> <select
					name="nacionalidad" id="nacionalidad"
					class="form-select form-select-sm"
					aria-label="Small select example">
					<%
						if (cliente != null) {
					%>
					<option selected value="<%=cliente.getNacionalidad().getId()%>"><%=cliente.getNacionalidad().getNombre()%></option>
					<%
						}
					%>

					<%
						if (listaNaciones != null) {
							for (Nacionalidad nac : listaNaciones) {
					%>
					<option value="<%=nac.getId()%>"><%=nac.getNombre()%></option>
					<%
						}
						}
					%>
				</select>
			</div>

			<%
				String accionBoton = "Crear";
				if (accion == "editar") {
					accionBoton = "Editar";
				}
			%>
			<input type="submit" value="<%=accionBoton%>" name="btnEditar"
				class="btn btn-primary"> <a
				href="AdministracionClientes.jsp" class="btn btn-primary">Volver</a>
		</form>
	</div>
	
<%@ include file="Footer.jsp"%>
	<script type="text/javascript">
		// Filtro de Localidades reactivo
		const localidades = localidad.options;

		const filtrarLocalidades = async (idProvincia) => {
			const resp = await
			fetch('LocalidadesServlet?id=' + idProvincia);
			const localidadesDisponibles = await
			resp.json();

			localidad.options.length = 0;
			let opciones = '';
			for ( const nombre in localidadesDisponibles) {
				opciones += '<option value="' + localidadesDisponibles[nombre]+ '">'
						+ nombre + '</option>';
			}
			localidad.innerHTML = opciones;
		}

		filtrarLocalidades(provincia.value);
	</script>
</body>
</html>
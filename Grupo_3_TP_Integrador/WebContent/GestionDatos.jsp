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
		<h2><%=encabezado%></h2>
		<form action="GestionDatosServlet" method="POST">

			<div class="form-group">
				<label for="usuario">Usuario:</label> <input type="text"
					class="form-control" id="usuario" name="usuario"
					value="<%=cliente == null ? "" : cliente.getNombreUsuario()%>">
			</div>
			<div class="form-group">
				<label for="pass">Contraseña:</label> <input type="text"
					class="form-control" id="pass" name="pass"
					value="<%=cliente == null ? "" : cliente.getPass()%>">
			</div>
			<div class="form-group">
				<label for="nombre">Nombre:</label> <input type="text"
					class="form-control" id="nombre" name="nombre"
					value="<%=cliente == null ? "" : cliente.getNombre()%>">
			</div>
			<div class="form-group">
				<label for="nombre">Apellido:</label> <input type="text"
					class="form-control" id="apellido" name="apellido"
					value="<%=cliente == null ? "" : cliente.getApellido()%>">
			</div>
			<div class="form-group">
				<label for="nombre">DNI:</label> <input type="text"
					class="form-control" id="dni" name="dni"
					value="<%=cliente == null ? "" : cliente.getDni()%>">
			</div>
			<div class="form-group">
				<label for="nombre">CUIL:</label> <input type="text"
					class="form-control" id="cuil" name="cuil"
					value="<%=cliente == null ? "" : cliente.getCuil()%>">
			</div>
			<div class="form-group">
				<label for="nombre">Genero:</label> <input type="text"
					class="form-control" id="genero" name="genero"
					value="<%=cliente == null ? "" : cliente.getGenero()%>">
			</div>
			<div class="form-group">
				<label for="nombre">E-mail:</label> <input type="text"
					class="form-control" id="email" name="email"
					value="<%=cliente == null ? "" : cliente.getCorreoElectronico()%>">
			</div>
			<div class="form-group">
				<label for="nombre">Telefono:</label> <input type="text"
					class="form-control" id="telefono" name="telefono"
					value="<%=cliente == null ? "" : cliente.getTelefono()%>">
			</div>
			<div class="form-group">
				<label for="nombre">Fecha de nacimiento:</label> <input type="date"
					class="form-control" id="fechaNacimiento" name="fechaNacimiento"
					value="<%=cliente == null ? "" : cliente.getFechaNacimiento()%>" d>
			</div>
			<div class="form-group">
				<label for="nombre">Dirección:</label> <input type="text"
					class="form-control" id="direccion" name="direccion"
					value="<%=cliente == null ? "" : cliente.getDireccion()%>">
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
					class="form-select form-select-sm" disabled name="localidad"
					id="localidad" onchange="filtrarProvincias(this.value)">
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

	<script type="text/javascript">
	
	const localidades = localidad.options;
		function filtrarLocalidades(idProvincia) {
			localidad.disabled = false;
			let opcionesPosibles = Array.from(localidades).filter(l => l.value === idProvincia);
			localidad.value = opcionesPosibles[0].value;

		}
	const provincias = provincia.options;
		function filtrarProvincias(idLocalidad) {
			let opcionesPosibles = Array.from(provincias).filter(p => p.value === idLocalidad);
			provincia.value = opcionesPosibles[0].value;

		}
	</script>
</body>
</html>
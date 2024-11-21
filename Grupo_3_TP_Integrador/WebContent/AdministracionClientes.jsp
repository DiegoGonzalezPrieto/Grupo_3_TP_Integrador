<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if (request.getAttribute("listaClientes") == null) {
		response.sendRedirect("AdministracionClientesServlet");
		return;
	}
%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.Cliente"%>
<!DOCTYPE html>
<html lang="es">
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
</head>
<body>
	<%@ include file="BarraMenu.jsp"%>

	<div class="container mt-5">
		<h1 class="text-center mb-4">Administración de Clientes</h1>
		<%
			String mensaje = (String) session.getAttribute("mensaje");
			String tipoMensaje = (String) session.getAttribute("tipoMensaje");
			if (mensaje != null && tipoMensaje != null) {
				session.removeAttribute("mensaje");
				session.removeAttribute("tipoMensaje");
		%>
		<div class="alert alert-<%=tipoMensaje%> alert-dismissible fade show"
			role="alert">
			<%=mensaje%>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
			}
		%>
		<!-- Tabs -->
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link active" id="clientes-tab"
					data-bs-toggle="tab" data-bs-target="#clientes" type="button"
					role="tab" aria-controls="clientes" aria-selected="true">Clientes</button>
			</li>
			<!-- 
			<li class="nav-item" role="presentation">
				<button class="nav-link" id="usuarios-tab" data-bs-toggle="tab"
					data-bs-target="#usuarios" type="button" role="tab"
					aria-controls="usuarios" aria-selected="false">Usuarios</button>
			</li>
			 -->
		</ul>


		<div class="tab-content mt-4" id="myTabContent">

			<div class="tab-pane fade show active" id="clientes" role="tabpanel"
				aria-labelledby="clientes-tab">
				<a href="GestionDatosServlet?nuevo"><input type="submit"
					name="btnNuevo" class="btn btn-success btn-sm"
					value="Nuevo cliente"></a>

				<div class="mb-3"></div>

				<table id="clientesTable" class="table table-striped table-bordered"
					style="text-align:center;">
					<thead class="table-dark">
						<tr>
							<!-- 
							<th>Nombre</th>
							<th>Apellido</th>
							 -->
							<th>ID</th>
							<th>Cliente</th>
							<th>DNI</th>
							<th>Correo Electrónico</th>
							<th>Teléfono</th>
							<th>Usuario</th>
							
							<!-- 
							<th>Género</th>
							<th>Dirección</th>
							<th>Localidad</th>
							<th>Provincia</th>
							<th>Nacionalidad</th>
							 -->
							<th>Detalles</th>
							<th>Editar</th>
							<th>Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<%
							ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("listaClientes");
							if (listaClientes != null) {
								for (Cliente cliente : listaClientes) {
						%>
						<tr>
							<td><%=cliente.getIdCliente()%></td>
							<td><%=cliente.getApellido() + ", " + cliente.getNombre()%></td>
							<td><%=cliente.getDni()%></td>
							<td><%=cliente.getCorreoElectronico()%></td>
							<td><%=cliente.getTelefono()%></td>
							<td><%=cliente.getNombreUsuario()%></td>
							<!-- 
							<td><%=cliente.getNombre()%></td>
							<td><%=cliente.getApellido()%></td>
							 -->
							<!-- 
							<td><%=cliente.getGeneroCompleto()%></td>
							<td><%=cliente.getDireccion()%></td>
							<td><%=cliente.getLocalidad().getNombre()%></td>
							<td><%=cliente.getProvincia().getNombre()%></td>
							<td><%=cliente.getNacionalidad().getNombre()%></td>
							-->

							<td><a class="btn btn-info btn-sm"
								href="GestionDatosServlet?id=<%=cliente.getIdCliente()%>">Ver</a></td>
							<td><a class="btn btn-warning btn-sm"
								href="GestionDatosServlet?editar=<%=cliente.getIdCliente()%>">Editar</a></td>
							<td><a class="btn btn-danger btn-sm"
								href="GestionDatosServlet?delete=<%=cliente.getIdCliente()%>"
								onclick="return confirm('¿Seguro que desea eliminar esta cuenta?')">Eliminar</a></td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>


			</div>

			<!-- 
			<div class="tab-pane fade" id="usuarios" role="tabpanel"
				aria-labelledby="usuarios-tab">
				<form action="AdministracionClientesServlet" method="POST"
					class="mb-4">
					<div class="d-flex gap-2 mb-4">
						<button type="submit" class="btn btn-success">Nuevo
							Usuario</button>
						<button type="submit" class="btn btn-warning text-dark">Modificar
							Usuario</button>
						<button type="submit" class="btn btn-danger">Eliminar
							Usuario</button>
					</div>
				</form>
			</div>
			 -->
		</div>
	</div>


	<%@ include file="Footer.jsp"%>


	<!-- <script>
		$(document).ready(function() {
			$('#clientesTable').DataTable({
				"
			});
		});
	</script> -->
		<script type="text/javascript">
	    document.addEventListener('DOMContentLoaded', function() {
	        let table = new DataTable('#clientesTable', {
	            language: {
	                url: 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
	            },
	            paging: true,
	            searching: true,
	            info: true,
	            columnDefs: [
	                {
	                    targets: [0],
	                    visible: false,
	                    searchable: false
	                }
	            ]
	        });
	    });
	</script>




	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
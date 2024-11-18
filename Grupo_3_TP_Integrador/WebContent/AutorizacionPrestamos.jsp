<%@page import="java.util.List"%>
<%@page import="dominio.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%
	if (request.getAttribute("listaPrestamos") == null) {
		response.sendRedirect("AutorizacionPrestamoServlet");
		return;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Autorización de Préstamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>

<!-- JQuery + Datatables -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
<link
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css"
	rel="stylesheet"></link>

</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container">
		<h1 class="display-3">Autorización de Préstamos</h1>

		<!-- MOSTRAR MENSAJE DE EXITO DESPUES DE LA OPERACION -->
		<%
			String mensajeExito = (String) request.getAttribute("mensajeExito");
			if (mensajeExito != null) {
		%>
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<%=mensajeExito%>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
			}
		%>

		<!--  TRAER LISTA DE PRESTAMOS -->
		<%
			List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("listaPrestamos");
			if (prestamos == null || prestamos.isEmpty()) {
		%>
		<p></p>

		<%
			} else {
		%>

		<p></p>

		<%
			}
		%>



		<div class="row align-items-center">
			<div class="col d-flex justify-content-end">
				<table class="inputs">
					<tbody>
						<tr>
							<td>Cantidad de cuotas mínima:</td>
							<td><input type="number" id="minCuotas" name="minCuotas"></td>
						</tr>
						<tr>
							<td>Cantidad de cuotas máxima:</td>
							<td><input type="number" id="maxCuotas" name="maxCuotas"></td>
						</tr>
					</tbody>
				</table>

			</div>

		</div>

		<table id="tabla-prestamos" class="table table-striped">
			<thead>
				<tr>
					<th scope="col" class="text-center">Nombre del Cliente</th>
					<th scope="col" class="text-center">Apellido del Cliente</th>
					<th scope="col" class="text-center">Cuenta a Depositar</th>
					<th scope="col" class="text-center">Monto Solicitado</th>
					<th scope="col" class="text-center">Fecha Solicitado</th>
					<th scope="col" class="text-center">Cantidad de Cuotas</th>
					<th scope="col" class="text-center">Estado</th>
					<th scope="col" class="text-center">Acción</th>
				</tr>
			</thead>
			<tbody>
				<!-- OBTENGO LA LISTA DEL SERVLET  -->
				<%
					if (prestamos != null) {
						for (Prestamo p : prestamos) {
				%>

				<tr>
					<td class="text-center"><%=p.getCliente().getNombre()%></td>
					<td class="text-center"><%=p.getCliente().getApellido()%></td>
					<td class="text-center"><%=p.getCuenta().getNumeroCuenta()%></td>
					<td class="text-center">$ <%=p.getImportePrestamo()%></td>
					<td class="text-center"><%=p.getFechaAltaPrestamo()%></td>
					<td class="text-center"><%=p.getCuotas()%></td>
					<td
						class="text-center <%if (p.getEstadoValidacion().getNombre().equals("Pendiente")) {%> text-bg-secondary 
					<%} else if (p.getEstadoValidacion().getNombre().equals("Autorizado")) {%>text-bg-success
					<%} else if (p.getEstadoValidacion().getNombre().equals("Rechazado")) {%>text-bg-danger<%}%>">

						<%=p.getEstadoValidacion().getNombre()%>

					</td>
					<td class="d-flex justify-content-center">
						<%
							if (p.getEstadoValidacion().getNombre().equals("Pendiente")) {
						%>

						<form action="AutorizacionPrestamoServlet" method="post"
							onsubmit="return confirm('¿Está seguro de que desea Autorizar el préstamo?')">
							<input type="hidden" name="id" value="<%=p.getId()%>" /> <input
								type="hidden" name="accion" value="Aprobar" />
							<button type="submit" class="btn btn-outline-success btn-sm me-2">Aprobar</button>
						</form> <!-- Formulario para rechazar el préstamo -->
						<form action="AutorizacionPrestamoServlet" method="post"
							onsubmit="return confirm('¿Está seguro de que desea Rechazar el préstamo?')">
							<input type="hidden" name="id" value="<%=p.getId()%>" /> <input
								type="hidden" name="accion" value="Rechazar" />
							<button type="submit" class="btn btn-outline-danger btn-sm me-2">Rechazar</button>
						</form> <%
 	} else {
 %> <span>-</span> <%
 	}
 %>
					</td>
				</tr>
				<%
					}
					}
				%>

			</tbody>
		</table>
	</div>
	<%@ include file="Footer.jsp"%>
	<script type="text/javascript">
		let table = new DataTable(
				'#tabla-prestamos',
				{
					language : {
						url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
					}
				});

		// Filtros por cantidad de cuotas mayor y menor
		table.search.fixed('saldo', function(searchStr, data, index) {
			var min = parseInt(minCuotas.value, 10);
			var max = parseInt(maxCuotas.value, 10);
			var cuotas = parseInt(data[5]) || 0;

			if ((isNaN(min) && isNaN(max)) || (isNaN(min) && cuotas <= max)
					|| (min <= cuotas && isNaN(max))
					|| (min <= cuotas && cuotas <= max)) {
				return true;
			}

			return false;
		});

		minCuotas.addEventListener('input', function() {
			table.draw();
		});
		maxCuotas.addEventListener('input', function() {
			table.draw();
		});
	</script>

</body>
</html>
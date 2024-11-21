<%@page import="dominio.ReporteGuardado"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reportes</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
	.btn {
		margin-top: 10px;
	}
	
	h1 {
		text-align: center;
		margin-top: 10px;
	}
	
	h2 {
		margin-top: 5px;
	}
	
	label {
		margin-top: 6px;
	}
	
	.container {
		margin-bottom: 40px;
	}
</style>

</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
	<h1 class="display-5" style="margin:50px;">Administración de Reportes</h1>
	<hr>
	<div class="container">

		<!-- Form para generar nuevos reportes -->
		<div class="container" style="margin-top:20px;">
			<h3>Generar Nuevo Reporte</h3>
			<form action="GenerarReporteServlet" method="POST">
				<div class="mb-3">
					<label for="tipoReporte">Tipo de Reporte:</label> <select
						id="tipoReporte" name="tipoReporte" class="form-select w-50"
						required onchange="reporteSeleccionado(this.value)">
						<option value="clientes">Reporte de Clientes</option>
						<option value="cuentas">Reporte de Cuentas</option>
						<option value="prestamos">Reporte de Préstamos</option>
					</select>
					<div id="fechasReporte">
						<label for="fechaInicio">Fecha de Inicio:</label> <input
							type="date" id="fechaInicio" name="fechaInicio"
							class="form-control  w-50" required
							onchange="fechaFin.min = fechaInicio.value"> <label
							for="fechaFin">Fecha de Fin:</label> <input type="date"
							id="fechaFin" name="fechaFin" class="form-control  w-50" required>
					</div>
					<input type="submit" value="Generar Reporte"
						class="btn btn-primary" onclick="recargarPagina()">
				</div>
			</form>
		</div>
		<hr>

		<!-- Lista de reportes -->
		<%
			ArrayList<ReporteGuardado> reportes = new ArrayList<ReporteGuardado>();
			if (session.getAttribute("reportes") != null) {
				reportes = (ArrayList<ReporteGuardado>) session.getAttribute("reportes");
			} else {
				session.setAttribute("reportes", reportes);
			}
		%>
		
		<div style="margin-top:10px;">
			<h3>Reportes de esta sesión</h3>
			<div style="display:flex; margin-top:15px; color:dark-gray">
				<i class="fas fa-download" style="max-width:30%; margin-right:10px;"></i>
				<p style="font-size:10px;">Descargar reporte</p>
			</div>
		</div>
		<table id="tablaReportes" class="table table-striped">
			<thead>
				<tr>
					<th scope="col">ID Reporte</th>
					<th scope="col">Nombre</th>
					<th scope="col">Fecha</th>
					<th scope="col">Tipo</th>
					<th scope="col" class="text-center">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (ReporteGuardado reporte : reportes) {
				%>


				<tr>
					<td><%=reporte.getId()%></td>
					<td><%=reporte.getNombre()%></td>
					<td><%=reporte.getFechas()%></td>
					<td><%=reporte.getTipo()%></td>
					<td><a href="DescargarReporteServlet?id=<%=reporte.getId()%>"
						class="btn btn-warning mb-2"> <i class="fas fa-download"></i>
					</a></td>
				</tr>
				<%
					}
				%>

			</tbody>
		</table>
	</div>



	<%@ include file="Footer.jsp"%>
	<script type="text/javascript"> 
	
	// init seleccion de fechas 
	if (tipoReporte.value === 'clientes') { 
		fechaInicio.disabled = true; 
		fechaFin.disabled = true; 
		fechasReporte.hidden = true; 
		} 
	fechaInicio.max = new Date().toISOString().split("T")[0]; 
	fechaFin.max = new Date().toISOString().split("T")[0]; 
	
	// Cambios en formulario según tipo de reporte: 
		function reporteSeleccionado(reporte) { 
		if (reporte === 'clientes') { 
			fechaInicio.disabled = true; 
			fechaFin.disabled = true; 
			fechasReporte.hidden = true; 
			} else { 
				fechaInicio.disabled = false; 
				fechaFin.disabled = false; 
				fechasReporte.hidden = false; 
				} 
		} 
	// recargar pagina para agtualizar tabla de reportes 
	function recargarPagina() { 
		setTimeout(function() { 
			location.reload(); }, 2000); 
		} 
	</script> 
	
	<script type="text/javascript"> 
	let table = new DataTable( '#tablaReportes', { 
		language : { 
			url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json' 
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
	</script>



</body>
</html>

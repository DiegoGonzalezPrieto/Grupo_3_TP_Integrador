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
	margin: 5px;
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
	<h1>Gestión de Reportes</h1>
	<div class="container">
		<!-- Listaod de reportes -->
		<h2>Reportes</h2>
		<table id="tablaReportes" class="table table-striped">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nombre</th>
					<th scope="col">Fecha</th>
					<th scope="col">Detalle</th>
					<th scope="col" class="text-center">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Reporte de Clientes</td>
					<td>2024-11-01</td>
					<td>Detalles del reporte de clientes...</td>
					<td>
						<div class="btn-group btn-group-sm" role="group">
							<a href="VerReporteServlet?id=1"
								class="btn btn-primary me-2 mb-2"> <i class="fas fa-eye"></i>
							</a> <a href="DescargarReporteServlet?id=1"
								class="btn btn-warning mb-2"
								onclick="return confirm('¿Está seguro que desea descargar?')">
								<i class="fas fa-download"></i>
							</a>
						</div>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Reporte de Cuentas</td>
					<td>2024-11-01</td>
					<td>Detalles del reporte de cuentas...</td>
					<td>
						<div class="btn-group btn-group-sm" role="group">
							<a href="VerReporteServlet?id=1"
								class="btn btn-primary me-2 mb-2"> <i class="fas fa-eye"></i>
							</a> <a href="DescargarReporteServlet?id=1"
								class="btn btn-warning mb-2"
								onclick="return confirm('¿Está seguro que desea descargar?')">
								<i class="fas fa-download"></i>
							</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- Form para generar nuevos reportes -->
	<div class="container">
		<h2>Generar Nuevo Reporte</h2>
		<form action="GenerarReporteServlet" method="POST">
			<div class="mb-3">
				<label for="tipoReporte">Tipo de Reporte:</label> <select
					id="tipoReporte" name="tipoReporte" class="form-select">
					<option value="clientes">Reporte de Clientes</option>
					<option value="cuentas">Reporte de Cuentas</option>
					<option value="prestamos">Reporte de Préstamos</option>
				</select> <label for="fechaInicio">Fecha de Inicio:</label> <input
					type="date" id="fechaInicio" name="fechaInicio"
					class="form-control" required> <label for="fechaFin">Fecha
					de Fin:</label> <input type="date" id="fechaFin" name="fechaFin"
					class="form-control" required> <input type="submit"
					value="Generar Reporte" class="btn btn-primary"
					onclick="return confirm('¿Generar reporte?')">
			</div>
		</form>
	</div>

	<!-- Form para programar unn reporte -->
	<div class="container">
		<h2>Programar Reporte</h2>
		<form action="ProgramarReporteServlet" method="POST">
			<div class="mb-3">
				<label for="tipoReporteProgramado">Tipo de Reporte:</label> <select
					id="tipoReporteProgramado" name="tipoReporteProgramado"
					class="form-select">
					<option value="clientes">Reporte de Clientes</option>
					<option value="cuentas">Reporte de Cuentas</option>
					<option value="prestamos">Reporte de Préstamos</option>
				</select> <label for="frecuencia">Frecuencia:</label> <select id="frecuencia"
					name="frecuencia" class="form-select" required>
					<option value="semanal">Semanal</option>
					<option value="mensual">Mensual</option>
					<option value="anual">Anual</option>
				</select> <label for="diasSemana">Días de la semana:</label> <select
					id="diasSemana" name="diasSemana" class="form-select" required>
					<option value="lunes">Lunes</option>
					<option value="martes">Martes</option>
					<option value="miercoles">Miércoles</option>
					<option value="jueves">Jueves</option>
					<option value="viernes">Viernes</option>
					<option value="sabado">Sábado</option>
					<option value="domingo">Domingo</option>
				</select> <input type="submit" value="Programar Reporte"
					class="btn btn-secondary"
					onclick="return confirm('¿Programar reporte?')">
			</div>
		</form>
	</div>

	<script type="text/javascript">
		let table = new DataTable('#tablaReportes', {
			language : {
				url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
			}
		});
	</script>

</body>
</html>

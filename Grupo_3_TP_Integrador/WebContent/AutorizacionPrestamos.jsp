<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

		<!-- TODO: obtener préstamos y mostrarlos -->
		<!-- TODO: Si está pendiente, mostrar los botones de Acción, con enlace al Servlet correspondiente-->
		<table id="tabla-prestamos" class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Nombre del Cliente</th>
					<th scope="col">Cuenta a Depositar</th>
					<th scope="col">Monto Solicitado</th>
					<th scope="col">Cantidad de Cuotas</th>
					<th scope="col">Estado</th>
					<th scope="col" class="text-center">Acción</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Alba Martínez</td>
					<td>Caja de Ahorro ARS - CBU: 23234755095</td>
					<td>$ 20.000.000</td>
					<td>24</td>
					<td class="text-center text-bg-secondary">Pendiente</td>
					<td>
						<div class="btn-group btn-group-sm" role="group">
							<a class="btn btn-outline-success"
								href="AprobarPrestamoServlet?id=23"
								onclick="return confirm('¿Está seguro de que desea Autorizar el préstamo?')">Aprobar</a>
							<a class="btn btn-outline-danger"
								href="RechazarPrestamoServlet?id=23"
								onclick="return confirm('¿Está seguro de que desea Rechazar el préstamo?')">Rechazar</a>
						</div>
					</td>
				</tr>
				<tr>
					<td>Jorge Pérez</td>
					<td>Cuenta Corriente - CBU: 55589376482</td>
					<td>$ 5.000.000</td>
					<td>6</td>
					<td class="text-center text-bg-success">Aprobado</td>
					<td class="text-center">-</td>
				</tr>
				<tr>
					<td>Mauro Gómez</td>
					<td>Cuenta Corriente - CBU: 233684955</td>
					<td>$ 150.000.000</td>
					<td>36</td>
					<td class="text-center text-bg-danger">Rechazado</td>
					<td class="text-center">-</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		let table = new DataTable('#tabla-prestamos', {
			language : {
				url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
			}
		});
	</script>

</body>
</html>
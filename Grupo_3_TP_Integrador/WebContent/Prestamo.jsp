<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="esp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>

<!-- BOOSTRAP CSS -->
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
	<div class="container mt-5" style="font-size: 0.8em">
		<h1 class="text-center">Gestión de Prestamos</h1>

		<div class="d-flex justify-content-between align-items-center mb-4">
			<div class="p-2">
				<h4>Bienvenida MARIA LAURA</h4>
			</div>
			<div class="p-2">
				<a class="btn btn-primary"
					href="SolicitudPrestamo.jsp">+
					Nuevo Prestamo</a>
			</div>
		</div>

		<!-- BUSQUEDA POR FILTROS -->
		<div class="card mb-4">
			<div class="card-body">
				<h4>Filtros</h4>
				<form id="filterForm" action="ACA VA EL SERVLEt" method="post">
					<div class="form-row">
						<!--FILTRO POR TIPO DE CUENTA  -->
						<div class="form-group col-md-3">
							<label for="tipoCuenta">Tipo de Cuenta</label> <select
								id="tipoCuenta" name="tipoCuenta" class="form-control">
								<option value="">Todos</option>
								<option value="CTA CTE">CTA CTE</option>
								<option value="C.AHORRO">C.AHORRO</option>
							</select>
						</div>
						<!-- FILTRO POR ESTADO  -->
						<div class="form-group col-md-3">
							<label for="estado">Estado</label> <select id="estado"
								name="estado" class="form-control">
								<option value="">Todos</option>
								<option value="Aprobado">Aprobado</option>
								<option value="Rechazado">Rechazado</option>
								<option value="En Proceso">En Proceso</option>
							</select>
						</div>
						<!-- FILTRO POR IMPORTE MINIMO SOLICITADO -->
						<div class="form-group col-md-3">
							<label for="estado">Cuotas</label> <select id="estado"
								name="estado" class="form-control">
								<option value="">Todos</option>
								<option value="12 Cuotas">Aprobado</option>
								<option value="6 Cuotas">Rechazado</option>
							</select>
						</div>
						<!-- FILTRO POR IMPORTE MAXIMO SOLICITADO -->
						<div class="form-group col-md-3">
							<label for="importeMax">Importe Máximo Solicitado</label> <input
								type="number" id="importeMax" name="importeMax"
								class="form-control" placeholder="Max">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-12 text-center">
							<button type="submit" class="btn btn-secondary btn-block">Aplicar
								Filtros</button>
						</div>
					</div>
				</form>
			</div>
		</div>


		<h2 class="mt-4">Mis Prestamos Actuales</h2>

		<table id="tabla-prestamos" class="table table-striped table-bordered">
			<thead class="thead-dark">
				<tr>
					<th>Cuenta</th>
					<th>Tipo de Cuenta</th>
					<th>Fecha Solicitud</th>
					<th>Cuotas</th>
					<th>Importe Solicitado</th>
					<th>Total a Pagar</th>
					<th>Estado</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>CTA CTE</td>
					<td>2023-01-01</td>
					<td>12</td>
					<td>$10,000.00</td>
					<td>$12,000.00</td>
					<td><span class="badge badge-success">Aprobado</span></td>
					<td><a href="PagoPrestamo.jsp?id=1"
						class="btn btn-success btn-sm"
						style="font-size: 0.8em; padding: 0.25rem 0.5rem;">Pagar</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>C.AHORRO</td>
					<td>2023-02-01</td>
					<td>6</td>
					<td>$2,000.00</td>
					<td>$6,000.00</td>
					<td><span class="badge badge-danger">Rechazado</span></td>
					<td>-</td>
				</tr>
				<tr>
					<td>3</td>
					<td>CTA CTE</td>
					<td>2023-04-01</td>
					<td>12</td>
					<td>$27,000.00</td>
					<td>$45,000.00</td>
					<td><span class="badge badge-warning">En Proceso</span></td>
					<td>-</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@ include file="Footer.jsp"%>
	<!-- DATATABLE INICIO -->
	<script type="text/javascript">
		let table = new DataTable(
				'#tabla-prestamos',
				{
					language : {
						url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
					}
				});
	</script>
</body>
</html>
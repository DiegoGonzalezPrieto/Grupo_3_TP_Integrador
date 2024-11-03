<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="esp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Prestamos</title>
	
	<!-- BOOSTRAP CSS -->
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- JQUERY Y DATATABLES PAGINACION ORDENAMIENTO DE COL -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
	<link href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css" rel="stylesheet"></link>
	
</head>
<body>
	<div class="container mt-5">
		<h1 class="text-center">Gestión de Prestamos</h1>

		<div class="text-right mb-4">
			<button class="btn btn-primary" onclick="window.location.href='SolicitudPrestamo.jsp'">+ Nuevo Prestamo</button>
		</div>	

		<h2 class="mt-4">Mis Prestamos Actuales</h2>

			<table id="tabla-prestamos" class="table table-striped table-bordered">
				<thead class="thead-dark">
					<tr>	
						<th>Cuenta</th>
						<th>Fecha Solicitud</th>
						<th>Cuotas</th>
						<th>Importe a Pagar</th>
						<th>Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Cuenta 1</td>
			                <td>2023-01-01</td>
			                <td>12</td>
			                <td>$12,000.00</td>
			                <td><span class="badge badge-success">Aprobado</span></td>
			                <td><a href="PagoPrestamo.jsp?id=1" class="btn btn-success btn-sm">Pagar</a></td>
			            </tr>
			            <tr>
			                <td>Cuenta 2</td>
			                <td>2023-02-01</td>
			                <td>6</td>
			                <td>$6,000.00</td>
			                <td><span class="badge badge-danger">Rechazado</span></td>
			                <td>No disponible</td>
			            </tr>
			            <tr>
			                <td>Cuenta 2</td>
			                <td>2023-04-01</td>
			                <td>12</td>
			                <td>$45,000.00</td>
			                <td><span class="badge badge-warning">En Proceso</span></td>
			                <td>No disponible</td>
			            </tr>
				</tbody>
			</table>
		</div>	

	<!-- DATATABLE INICIO -->
    <script type="text/javascript">
        $(document).ready(function() {
            $('#tabla-prestamos').DataTable({
                language: {
                    url: '//cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
                }
            });
        });
    </script>

    <!-- BOOTSTRAP JS Y DEPENDENCIAS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
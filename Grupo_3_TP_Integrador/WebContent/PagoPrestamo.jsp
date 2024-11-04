<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pago de Préstamos</title>

<!-- BOOTSTRAP -->
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
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-5">
		<h1 class="text-center">Pago de Préstamos</h1>

		<!-- TRAEMOS DESDE EL SERVLET LOS PRESTAMOS Y LA CUOTAS DEL METODO DOGET -->
		<!-- 
					Prestamo prestamo = (Prestamo) request.getAttribute("prestamo") 
			    	List<Cuota> cuotas = (List<Cuota>) request.getAttribute("cuotas")	
				 -->

		<!-- NOMBRE DE RECEPCION DE CLIENTE -->
		<div class="alert alert-secondary mt-4" role="alert">
			<strong>CLIENTE:</strong> MARIA LAURA
		</div>
		<!-- INFORME ESTADO DE CUENTA QUE TIENE EL PRESTAMO -->
		<div class="alert alert-info mt-4" role="alert">
			<strong>Saldo Disponible en Cuenta:</strong> $<span
				id="saldoDisponible">12,000.00</span>
		</div>

		<h2 class="mt-4">Detalles del Prestamo</h2>
		<form action="ProcesarPagoServlet" method="post">
			<input type="hidden" name="idPrestamo"
				value="metodo para traer el id de Prestamo">
			<fieldset class="border p-3">
				<legend class="w-auto">Cuota a Pagar</legend>
				<table id="tabla-cuotas" class="table table-striped table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>Numero de Cuota</th>
							<th>Fecha Solicitud</th>
							<th>Monto</th>
							<th>Pagar</th>
						</tr>
					</thead>
					<tbody>
						<!-- ACA TRAEMOS CON UN FOR LA INFO DESDE EL DOGET PARA PONER EN LA TABLA
							EJ: 
							List<Cuota> cuotas = (List<Cuota>) request.getAttribute("cuotas"); // CAMBIO: Se obtiene la lista de cuotas
							for (Cuota cuota : cuotas) {
								String nroCuota = cuota.getNroCuota();
								String fechaVenc = cuota.getFechaVencimiento();
								double importeMensual = prestamo.getImporteMensual();						
						 -->

						<tr>

							<td>1</td>
							<td>2024-2-01</td>
							<td>$1,000.00</td>

							<td><input type="checkbox" name="cuotas" value="1">
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>2023-06-01</td>
							<td>$4,000.00</td>
							<td><input type="checkbox" name="cuotas" value="2">
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>2023-06-01</td>
							<td>$4,000.00</td>
							<td><input type="checkbox" name="cuotas" value="2">
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>2023-06-01</td>
							<td>$4,000.00</td>
							<td><input type="checkbox" name="cuotas" value="2">
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>2023-06-01</td>
							<td>$4,000.00</td>
							<td><input type="checkbox" name="cuotas" value="2">
							</td>
						</tr>
						<!-- } CERRAMOS EL FOR -->
					</tbody>
				</table>
			</fieldset>
			<div class="mt-3">
				<input class="btn btn-success" type="submit"
					value="Pagar Seleccionadas"> <input
					class="btn btn-secondary" type="button" value="Volver"
					onclick="window.location.href='Prestamo.jsp';">
			</div>
		</form>
	</div>

	<script type="text/javascript">
		let table = new DataTable(
				'#tabla-cuotas',
				{
					language : {
						url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
					}
				});
	</script>


</body>
</html>
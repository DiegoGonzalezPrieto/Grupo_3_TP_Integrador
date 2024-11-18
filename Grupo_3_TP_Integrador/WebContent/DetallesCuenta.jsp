<%@page import="dominio.Movimiento"%>
<%@page import="java.util.List"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Detalles de Cuenta</title>
<link
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
	.dataTables_wrapper .dataTables_length {
		float: left;
		margin-right: 20px;
	}
	
	.dataTables_wrapper .dataTables_filter {
		float: none;
		display: inline-block;
	}
	
	.filter-group {
		float: right;
		display: flex;
		align-items: center;
		gap: 20px;
	}
	
	.filter-group input {
		width: 120px;
	}
	
	.dataTables_wrapper .row:first-child {
		display: flex;
		align-items: center;
		margin-bottom: 1rem;
	}
	
	.filter-label {
		margin-bottom: 0;
		white-space: nowrap;
	}
</style>
</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-4">
		<div class="card">
			<div class="card-header bg-white">
				<div class="row mb-3">
					<h4 class="card-header bg-primary text-white">Movimientos de Cuenta</h4>
				</div>

				<div class="row mb-3" style="text-align:center">
					<div class="col-md-3">
						<label class="form-label text-muted">Tipo de Cuenta</label>
						<p class="fw-bold mb-2"><%= request.getAttribute("tipoCuenta")%></p>
					</div>
					<div class="col-md-3">
						<label class="form-label text-muted">Número de Cuenta</label>
						<p class="fw-bold mb-2"><%= request.getAttribute("numeroCuenta")%></p>
					</div>
					<div class="col-md-3">
						<label class="form-label text-muted">CBU</label>
						<p class="fw-bold mb-2"><%= request.getAttribute("cbu")%></p>
					</div>
					<div class="col-md-3">
						<label class="form-label text-muted">Saldo</label>
						<p class="fw-bold mb-2">$ <%= request.getAttribute("saldo")%></p>
					</div>
				</div>
			</div>

			<div class="card-body">
				<div class="mb-3 clearfix">
					<div class="filter-group">
						<label class="filter-label">Filtrar:</label>
						<input type="number" id="min" name="min" class="form-control" placeholder="Imp. mínimo">
						<input type="number" id="max" name="max" class="form-control" placeholder="Imp. máximo">
					</div>
				</div>
				<div class="table-responsive">
					<table id="tabla-movimientos" class="table table-striped table-hover">
						<thead class="table-light">
							<tr>
								<th class="text-center">Fecha</th>
								<th class="text-center">Detalle</th>
								<th class="text-end">Importe</th>
								<th class="text-center">Tipo de Movimiento</th>
							</tr>
						</thead>
						<tbody>
						<%
	                		List<Movimiento> movimientosPropios = (List<Movimiento>)request.getAttribute("movimientosPropios");
	                		if(movimientosPropios != null) {
	                    		for(Movimiento movimiento : movimientosPropios) {
	                    			String claseImporte = movimiento.getMonto().compareTo(BigDecimal.ZERO)<0 ? "text-danger" : "text-success";
	            		%>
							<tr>
								<td class="text-center"> <%=movimiento.getFecha()%></td>
								<td class="text-center"><%=movimiento.getTipo()%></td>
								<td class="text-end <%=claseImporte%>"><%=movimiento.getMonto()%></td>
								<td class="text-center"><%=movimiento.getConcepto()%></td>
							</tr>
							<% }
	                   		}%>
						</tbody>
					</table>
						
					<form action="HomeCliente.jsp" method="GET">
						<div class="text-center mt-4">
							<button type="submit" class="btn btn-primary">Volver a Home</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>

	<script type="text/javascript">
		$(document).ready(function() {
			let table = new DataTable('#tabla-movimientos',{
				language : {
					url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-ES.json'
				}
			});

		DataTable.ext.search.push(function(settings, data, dataIndex) {
			let min = parseFloat($('#min').val());
			let max = parseFloat($('#max').val());
			let importe = Math.abs(parseFloat(data[2].replace(/[^0-9.-]+/g, "")));

			if ((isNaN(min) && isNaN(max)) ||
				(isNaN(min) && importe <= max) ||
				(min <= importe && isNaN(max)) ||
				(min <= importe && importe <= max)) {
				
				return true;
			}
			return false;
		});

		$('#min, #max').on('input', function() {
			table.draw();
		});
	});
	</script>

</body>
</html>
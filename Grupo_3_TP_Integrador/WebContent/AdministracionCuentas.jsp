<%@page import="dominio.TipoCuenta"%>
<%@page import="dominio.Cliente"%>
<%@page import="dominio.Cuenta"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (request.getAttribute("listaCuentas") == null) {
		response.sendRedirect("AdministracionCuentasServlet");
		return;
	}
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Administración de Cuentas</title>
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
<!-- FontAwesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<style>
.dataTables_filter {
	float: left !important;
	text-align: left !important;
}

.dataTables_filter label {
	font-weight: bold;
}

.dataTables_filter input {
	margin-left: 0.5em;
	display: inline-block;
	width: auto;
}
	
.dataTables_wrapper .dataTables_scrollBody td {
    text-align: center;
}

</style>

</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-4">
		<%
			String mensaje = (String) request.getAttribute("mensaje");
			String tipoMensaje = (String) request.getAttribute("tipoMensaje");
			if (mensaje != null && tipoMensaje != null) {
		%>
		<div class="alert alert-<%=tipoMensaje%> alert-dismissible fade show"
			role="alert">
			<%=mensaje%>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			}
		%>
		<h1 class="display-5" style="margin:50px; text-align:center;">Administración de Cuentas</h1>
		<hr>

		<div class="mt-4">
			<!-- 
				<h2>Listado de Cuentas</h2>
			 -->
			<div class="container mt-4">
				   <div class="row">
				       <!-- Botón Nueva Cuenta -->
				       <div class="col-md-4">
				           
				       </div>
				
				       <!-- Filtro de Saldo -->
				       <div class="col-md-4">
				           <div class="card shadow-sm h-100">
				               <div class="card-header bg-light">
				                   <h6 class="mb-0">Filtro por Saldo</h6>
				               </div>
				               <div class="card-body">
				                   <div class="mb-3">
				                       <label class="form-label">Saldo mínimo:</label>
				                       <input type="number" class="form-control" id="minSaldo" name="minSaldo" 
				                              min="0" max="999999999" 
				                              oninput="if(this.value.length > 9) this.value=this.value.slice(0,9)">
				                   </div>
				                   <div class="mb-3">
				                       <label class="form-label">Saldo máximo:</label>
				                       <input type="number" class="form-control" id="maxSaldo" name="maxSaldo"
				                              min="0" max="999999999"
				                              oninput="if(this.value.length > 9) this.value=this.value.slice(0,9)">
				                   </div>
				               </div>
				           </div>
				       </div>
				
				       <!-- Filtro de Fecha -->
				       <div class="col-md-4">
				           <div class="card shadow-sm h-100">
				               <div class="card-header bg-light">
				                   <h6 class="mb-0">Filtro por Fecha</h6>
				               </div>
				               <div class="card-body">
				                   <div class="mb-3">
				                       <label class="form-label">Fecha desde:</label>
				                       <input type="date" class="form-control" id="minFecha" name="minFecha">
				                   </div>
				                   <div class="mb-3">
				                       <label class="form-label">Fecha hasta:</label>
				                       <input type="date" class="form-control" id="maxFecha" name="maxFecha">
				                   </div>
				               </div>
				           </div>
				       </div>
				   </div>
				
				   <!-- Botón Limpiar -->
				   <div class="row mt-3">
					   <div class="col-12 d-flex justify-content-between">
					       <a href="AgregarCuentaServlet" class="btn btn-outline-success">Nueva Cuenta</a>
					       <a href="#" id="limpiar-filtros" class="btn btn-outline-secondary">
					           <i class="bi bi-trash"></i> Limpiar filtros
					       </a>
					   </div>
					</div>
				</div>
			<hr>


			<table id="cuentasTable" class="table table-striped table-bordered" data-order='[[3, "asc"]]'>
				<thead>
					<tr>
						<th>ID</th>
						<th>Cliente</th>
						<th>Fecha de Creación</th>
						<th>Tipo de Cuenta</th>
						<th>Número de Cuenta</th>
						<th>CBU</th>
						<th>Saldo</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
						if (listaCuentas != null) {
							for (Cuenta cuenta : listaCuentas) {
					%>
					<tr>
						<td><%=cuenta.getId()%></td>
						<td><%=cuenta.getCliente().getApellido() + ", " + cuenta.getCliente().getNombre()%></td>
						<td><%=cuenta.getFechaCreacion()%></td>
						<td><%=cuenta.getTipoCuenta().getNombre()%></td>
						<td><%=cuenta.getNumeroCuenta()%></td>
						<td><%=cuenta.getCbu()%></td>
						<td
							class="<%=cuenta.getSaldo().doubleValue() >= 0 ? "text-success" : "text-danger"%>">
							$<%=String.format("%,.2f", cuenta.getSaldo())%>
						</td>
						<td><a href="ModificarCuentaServlet?id=<%=cuenta.getId()%>"
							class="btn btn-outline-primary" title="Editar cuenta"> <i class="fas fa-edit"></i>
						</a> <a href="EliminarCuentaServlet?id=<%=cuenta.getId()%>"
							class="btn btn-outline-danger"
							onclick="return confirm('¿Seguro que desea eliminar esta cuenta?')" title="Eliminar cuenta">
								<i class="fa-regular fa-trash-can"></i>
						</a></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
		</div>


	</div>

	<%@ include file="Footer.jsp"%>
	<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function() {
        let table = new DataTable('#cuentasTable', {
            language: {
                url: 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
            },
            paging: true,
            searching: true,
            info: true,
            columnDefs: [
                {
                    targets: [0], // Índice de la columna que deseas ocultar (ID)
                    visible: false,
                    searchable: false
                }
            ]
        });

        // Filtros por saldo mayor y menor
        table.search.fixed('saldo', function(searchStr, data, index) {
		    var min = parseFloat(minSaldo.value) || 0;
		    var max = parseFloat(maxSaldo.value) || Infinity;
		    
		    var saldo  = parseFloat(data[6].replace('$', '').replace(/\./g, '').trim()) || 0;
		    
		    return (isNaN(min) || saldo >= min) && (isNaN(max) || saldo <= max);
		});

        minSaldo.addEventListener('input', function() {
            table.draw();
        });
        maxSaldo.addEventListener('input', function() {
            table.draw();
        });

        // Filtros por fecha mayor y menor
        table.search.fixed('fecha', function(searchStr, data, index) {
            var min = minFecha.value ? new Date(minFecha.value) : false;
            var max = maxFecha.value ? new Date(maxFecha.value) : false;
            var fecha = new Date(data[2]);

            if ((min <= fecha || !min) && (max >= fecha || !max)) {
                return true;
            }

            return false;
        });

        minFecha.addEventListener('input', function() {
            table.draw();
        });
        maxFecha.addEventListener('input', function() {
            table.draw();
        });
        
     // Limpiar filtros
        document.getElementById('limpiar-filtros').addEventListener('click', function(e) {
            e.preventDefault();
            minSaldo.value = '';
            maxSaldo.value = '';
            minFecha.value = '';
            maxFecha.value = '';

            table.search('').columns().search('').draw();
        });
    });
</script>

</body>
</html>



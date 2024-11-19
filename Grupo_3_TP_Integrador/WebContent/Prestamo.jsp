<%@page import="java.util.List"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
   if(request.getAttribute("listaPrestamos") == null) {
       response.sendRedirect("PrestamosServlet");
       return;
   }
%>
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
		
		<!--  TRAER LISTA DE PRESTAMOS -->
		<%
            List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("listaPrestamos");
            if (prestamos == null || prestamos.isEmpty()) {
        %>
            <p></p>
        <% }else{ %>     
        
            <p></p>
            
        <% } %>    
		<!--  TRAIGO CLIENTE -->
		<%
            Cliente cliente= (Cliente) request.getAttribute("cliente");
            if (cliente == null) {
        %>
            <p></p>
            
        <% }else{ %>     
        
            <p></p>
            
        <% } %>    	
		

		<div class="d-flex justify-content-between align-items-center mb-4">
			<div class="p-2">
				<h2 class="my-3"><%= cliente.getApellido() +" "+ cliente.getNombre() %></h2>
			</div>
			<div class="p-2">
				<a class="btn btn-primary"
					href="SolicitudPrestamoServlet?id=<%=cliente.getIdCliente()%>">+
					Nuevo Prestamo</a>
			</div>
		</div>
		
		<div class="card mb-4">
			<div class="card-body">
				<h4>Filtros</h4>
				<form id="filterForm" action="PrestamosServlet" method="post">
					<input type="hidden" name="id" value="<%= cliente.getIdCliente() %>"/>
					<div class="form-row">
						<!--FILTRO POR TIPO DE CUENTA  -->
						<div class="form-group col-md-3">
							<label for="tipoCuenta">Tipo de Cuenta</label> <select
								id="tipoCuenta" name="tipoCuenta" class="form-control">
								<option value="">Todos</option>
								<option value="Cuenta Corriente">Cuenta Corriente</option>
								<option value="Caja de Ahorro">Caja de Ahorro</option>
							</select>
						</div>
						<!-- FILTRO POR ESTADO  -->
						<div class="form-group col-md-3">
							<label for="estadoPrestamo">Estado</label> <select id="estado"
								name="estadoPrestamo" class="form-control">
								<option value="">Todos</option>
								<option value="Autorizado">Autorizado</option>
								<option value="Rechazado">Rechazado</option>
								<option value="Pendiente">Pendiente</option>
							</select>
						</div>
						<!-- FILTRO POR IMPORTE MINIMO SOLICITADO -->
						<div class="form-group col-md-3">
							<label for="cuotas">Cuotas</label> <select id="estado"
								name="cuotas" class="form-control">
								<option value="">Todos</option>
								<option value="6">6</option>
								<option value="12">12</option>
								<option value="18">18</option>
								<option value="24">24</option>
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
					<th scope="col" class="text-center">Cuenta</th>
					<th scope="col" class="text-center">Tipo de Cuenta</th>
					<th scope="col" class="text-center">Fecha Solicitud</th>
					<th scope="col" class="text-center">Cuotas</th>
					<th scope="col" class="text-center">Importe Solicitado</th>
					<th scope="col" class="text-center">Total a Pagar</th>
					<th scope="col" class="text-center">Estado</th>
					<th scope="col" class="text-center">Acciones</th>
				</tr>
			</thead>
			<tbody>
					<!-- OBTENGO LA LISTA DEL SERVLET  -->	
					<%
						
						if(prestamos != null){
							for(Prestamo p : prestamos) {	
								
					%>
			
				<tr>
					<td class="text-center"> <%= p.getCuenta().getId() %></td>
					<td class="text-center"> <%= p.getCuenta().getTipoCuenta().getNombre() %></td>
					<td class="text-center"> <%= p.getFechaAltaPrestamo() %></td>
					<td class="text-center"> <%= p.getCuotas() %></td>
					<td class="text-center"> <%= p.getImportePrestamo() %></td>
					<td class="text-center"> <%= p.totalAPagar() %></td>					
					<td class="text-center <%if (p.getEstadoValidacion().getNombre().equals("Pendiente")) { %> text-bg-secondary 
					<% } else if (p.getEstadoValidacion().getNombre().equals("Autorizado")){ %>text-bg-success
					<% } else if (p.getEstadoValidacion().getNombre().equals("Rechazado")){ %>text-bg-danger<% } %>">
					
					<%= p.getEstadoValidacion().getNombre() %>
					
					</td>
					<td class ="d-flex justify-content-center">
						
						<% if (p.getEstadoValidacion().getNombre().equals("Autorizado")){ %>
						
						<form action="PagoPrestamoServlet" method="get" onsubmit="return confirm('¿Está seguro de que desea Pagar el préstamo?')">
							<input type="hidden" name="id" value="<%= p.getId() %>" />
							<input type="hidden" name="accion" value="Pagar" />
							<button type="submit" class="btn btn-outline-success btn-sm me-2">Pagar</button>
						</form>												
						
						<%} else { %>
							<span>-</span>	
						<%} %>								
					</td>
					
				</tr>
				<% }
							}%>
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
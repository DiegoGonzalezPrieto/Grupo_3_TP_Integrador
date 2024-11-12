<%@page import="java.util.List"%>
<%@page import="dominio.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%
   if(request.getAttribute("listaPrestamos") == null) {
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
		
		<%
            List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("listaPrestamos");
            if (prestamos == null || prestamos.isEmpty()) {
        %>
            <p>No hay préstamos disponibles.</p>
            
        <% }else{ %>     
        
            <p>Se encontraron <%= prestamos.size() %> préstamos.</p>
            
        <% } %>    
       
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
					<!-- OBTENGO LA LISTA DEL SERVLET  -->	
					<%
						
						if(prestamos != null){
							for(Prestamo p : prestamos) {	
								
					%>
					
				<tr>	
					<td><%=p.getCliente().getNombre() %></td>
					<td><%=p.getCuenta().getNumeroCuenta() %></td>
					<td><%=p.getImportePrestamo() %></td>
					<td><%=p.getCuotas() %></td>
					<td class="text-center <%if (p.getEstadoValidacion().getNombre().equals("Pendiente")) { %> text-bg-secondary 
					<% } else if (p.getEstadoValidacion().getNombre().equals("Autorizado")){ %>text-bg-success
					<% } else if (p.getEstadoValidacion().getNombre().equals("Rechazado")){ %>text-bg-danger<% } %>">
					
					<%= p.getEstadoValidacion().getNombre() %>
					
					</td>
					<td class ="text-center">
						
						<% if (p.getEstadoValidacion().getNombre().equals("Pendiente")){ %>
						<div class="btn-group btn-group-sm" role="group">
							<a class="btn btn-outline-success"
								href="AprobarPrestamoServlet?id=<%= p.getId() %>&accion=Aprobar"
								onclick="return confirm('¿Está seguro de que desea Autorizar el préstamo?')">Aprobar</a>
							<a class="btn btn-outline-danger"
								href="RechazarPrestamoServlet?id=<%= p.getId() %>&accion=Rechazar"
								onclick="return confirm('¿Está seguro de que desea Rechazar el préstamo?')">Rechazar</a>
						</div>
						<%} else { %>
							<span>-</span>	
						<%} %>								
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
	</script>

</body>
</html>
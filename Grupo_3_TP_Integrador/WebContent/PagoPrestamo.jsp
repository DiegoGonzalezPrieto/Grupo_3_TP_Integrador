<%@page import="java.util.List"%>
<%@page import="dominio.Cuota"%>
<%@page import="dominio.Cliente"%>
<%@page import="dominio.Cuenta"%>
<%@page import="dominio.Prestamo"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
   if(request.getAttribute("listaCuotas") == null) {
       response.sendRedirect("PagoPrestamoServlet");
       return;
   }
%>
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
		<%
   			 String mensajeExito = (String) request.getAttribute("mensajeExito");
    		if (mensajeExito != null && !mensajeExito.isEmpty()) {
		%>
        	<div class="alert alert-success mt-4" role="alert">
            	<strong>¡Éxito!</strong> <%= mensajeExito %>
        	</div>
		<%
    		}
		%>
		<!--  TRAER LISTA DE CUOTAS -->
		<%
            List<Cuota> cuota = (List<Cuota>) request.getAttribute("listaCuotas");
			
            if (cuota == null || cuota.isEmpty()) {
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
        <!--  TRAIGO CUENTA -->
        <%
            List<Cuenta> listaCuenta = (List<Cuenta>) request.getAttribute("listaCuentas");
            if (listaCuenta == null || listaCuenta.isEmpty()) {
        %>
            <p></p>
        <% }else{ %>     
        
            <p></p>
            
        <% } %>    
        <% 
        	Prestamo prestamo = (Prestamo) request.getAttribute("prestamo"); 
        %>
		

		<!-- NOMBRE DE RECEPCION DE CLIENTE -->
		<div class="alert alert-secondary mt-4" role="alert">
			<strong>CLIENTE:</strong> <%= cliente.getApellido() +" "+ cliente.getNombre() %>
		</div>
		
		<h2 class="mt-4">Detalles del Prestamo</h2>
		
		<form action="PagoPrestamoServlet" method="post">
				
				<div class="form-group mt-4">
		    		<label for="cuentas">Cuenta a Debitar:</label>
		    			<select class="form-control" id="Cuentas" name="cuentas" onchange="actualizarSaldo()">
		        			<option value="" disabled selected>Selecciona una Cuenta</option>
		        		
		        		<% 
		            		if (listaCuenta != null && !listaCuenta.isEmpty()) {
		              			 for (Cuenta cuenta : listaCuenta) {
		       			 %>
		         			   <option value="<%= cuenta.getId() %>" data-saldo="<%= cuenta.getSaldo() %>">
		                			<%= cuenta.getTipoCuenta().getNombre() %> - 
		                			<%= cuenta.getNumeroCuenta() %>                			 
		            		   </option>
		        		<% 
		               		 	}
		            		} 
		       			 %>
		   				 </select>
				</div>
				
				<!-- INFORME ESTADO DE CUENTA QUE TIENE EL PRESTAMO -->
				<div class="alert alert-info mt-4" role="alert">
					<strong>Saldo Disponible en Cuenta:</strong> <span id="saldoDisponible"></span>
				</div>
			
			<fieldset class="border p-3">
				<legend class="w-auto">Cuota a Pagar</legend>
				<table id="tabla-cuotas" class="table table-striped table-bordered">
					<thead class="thead-dark">
						<tr>
							<th scope="col" class="text-center" width="100">Numero de Cuota</th>
							<th scope="col" class="text-center" width="100">Fecha Vencimiento</th>
							<th scope="col" class="text-center" width="100">Monto</th>
							<th scope="col" class="text-center" width="100">Estado</th>
							<th scope="col" class="text-center" width="100">Pagar</th>
						</tr>
					</thead>
					<tbody>
						<%
						
							//LISTAS DE CUOTAS QUE SE RECIBEN
							if(cuota != null){
							for(Cuota c : cuota) {
						
						
						%>

						<tr>

							<td class="text-center"><%= c.getNumeroCuota() %></td>
							<td class="text-center"><%= c.getFechaPago() %></td>
							<td class="text-center">$ <%= c.getMontoPagado() %></td>
							<td class="text-center 
   								 <% if (c.getEstadoPago()) { %> text-bg-secondary 
    							 <% } else { %> text-bg-danger 
                                 <% } %>">
                                 <%= c.getEstadoPago() ? "Pagado" : "Sin pagar" %>
							</td>
							<td class="text-center">
								<input type="radio" name="cuotas" value="<%= c.getId() %>"
								<% if (c.getEstadoPago()) { %> disabled <% } %>>
							</td>
						</tr>
						<%
							}
            			}
						
						%>
						
					</tbody>
				</table>
			</fieldset>

            <div class="mt-3 row">
    			<div class="col-auto">
       				 <!-- BOTON PARA PAGAR TODAS -->
       				<input type="hidden" name="idPrestamo" value="<%= prestamo.getId() %>">
        			<button class="btn btn-primary" type="submit" name="accion" value="pagarTodas">Pagar Todas</button>
    			</div>
    			
    			<div class="col-auto">
        			<!-- BOTON PARA PAGAR CUOTA SELECCIONADA -->
        			<button class="btn btn-success" type="submit" name="accion" value="pagarCuotaSeleccionada">Pagar Cuota Seleccionada</button>
   			    </div>
   			    
					<!-- BOTON PARA PAGAR VOLVER -->            
   			    <div class="col-auto">
   			    	<a href="PrestamosServlet?id=<%=prestamo.getId()%>" class="btn btn-secondary">Volver</a>
           		</div>
			</div>
              
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
	
	
	<script type="text/javascript">
		let table = new DataTable(
				'#tabla-cuotas',
				{
					language : {
						url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
					}
				});
	</script>
	<!--  SCRIP PARA CALCULAR EL SALDO EN CUENTA DE MANERA DINAMICA  -->
	<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        
        function actualizarSaldo() {
            var cuentaSeleccionada = document.getElementById('Cuentas').value;

            /// SINO SELECCIONA CUENTA, SETEA EL SALDO EN 0
            if (!cuentaSeleccionada) {
                document.getElementById('saldoDisponible').textContent = '$0';
                return;
            }

            // OBTIENE SELECCION Y SALDO.
            var option = document.querySelector('#Cuentas option[value="' + cuentaSeleccionada + '"]');
            var saldo = option ? option.getAttribute('data-saldo') : 0;

            // ACTUALIZA EL TEXXTO
            document.getElementById('saldoDisponible').textContent = '$' + saldo;
        }

        // Asignar el evento para actualizar el saldo cuando se cambie la cuenta seleccionada
        document.getElementById('Cuentas').addEventListener('change', actualizarSaldo);
    });
</script>
</body>
</html>
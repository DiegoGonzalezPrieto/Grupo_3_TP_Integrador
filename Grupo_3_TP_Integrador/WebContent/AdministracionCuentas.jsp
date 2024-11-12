<%@page import="dominio.TipoCuenta"%>
<%@page import="dominio.Cliente"%>
<%@page import="dominio.Cuenta"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
   if(request.getAttribute("listaCuentas") == null) {
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

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"
	rel="stylesheet">


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
</style>

</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
<div class="container mt-4">
	<%
	   String mensaje = (String) request.getAttribute("mensaje");
	   String tipoMensaje = (String) request.getAttribute("tipoMensaje");
	   if(mensaje != null && tipoMensaje != null) {
	%>
	   <div class="alert alert-<%=tipoMensaje%> alert-dismissible fade show" role="alert">
	       <%=mensaje%>
	       <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	           <span aria-hidden="true">&times;</span>
	       </button>
	   </div>
	<%
	   }
	%>
	<h1>Administración de Cuentas</h1>

	<div class="mt-4">
		<h2>Listado de Cuentas</h2>
		<table id="cuentasTable" class="table table-striped table-bordered">
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
	                List<Cuenta> listaCuentas = (List<Cuenta>)request.getAttribute("listaCuentas");
	                if(listaCuentas != null) {
	                    for(Cuenta cuenta : listaCuentas) {
	            %>
	                <tr>
	                    <td><%=cuenta.getId()%></td>
	                    <td><%=cuenta.getCliente().getNombre() + " " + cuenta.getCliente().getApellido()%></td>
	                    <td><%=cuenta.getFechaCreacion()%></td>
	                    <td><%=cuenta.getTipoCuenta().getNombre()%></td>
	                    <td><%=cuenta.getNumeroCuenta()%></td>
	                    <td><%=cuenta.getCbu()%></td>
	                    <td class="<%=cuenta.getSaldo().doubleValue() >= 0 ? "text-success" : "text-danger"%>">
	                        $<%=String.format("%,.2f", cuenta.getSaldo())%>
	                    </td>
	                    <td>
	                        <a href="ModificarCuentaServlet?id=<%=cuenta.getId()%>" class="btn btn-warning btn-sm">
	                            Modificar
	                        </a>
	                        <a href="EliminarCuentaServlet?id=<%=cuenta.getId()%>" 
	                           class="btn btn-danger btn-sm"
	                           onclick="return confirm('¿Seguro que desea eliminar esta cuenta?')">
	                            <i class="fa-regular fa-trash-can"></i>
	                        </a>
	                    </td>
	                </tr>
	            <%
	                    }
	                }
	            %>
	        </tbody>
		</table>
	</div>

	<div class="mt-4">
    	<h2>Agregar Nueva Cuenta</h2>
		    <form action="AgregarCuentaServlet" method="post">
		        <div class="form-group">
		            <label for="cliente">Cliente:</label>
		            <select id="cliente" name="cliente" class="form-control" required>
		                <option value="">Seleccione un cliente</option>
		                <%
		                    List<Cliente> listaClientes = (List<Cliente>)request.getAttribute("listaClientes");
		                    if(listaClientes != null) {
		                        for(Cliente cliente : listaClientes) {
		                %>
		                    <option value="<%=cliente.getIdCliente()%>">
		                        <%=cliente.getApellido() + ", " + cliente.getNombre()%>
		                    </option>
		                <%
		                        }
		                    }
		                %>
		            </select>
		        </div>
		
		        <div class="form-group">
				    <label for="tipoCuenta">Tipo de Cuenta:</label>
				    <select id="tipoCuenta" name="tipoCuenta" class="form-control" required>
				        <option value="">Seleccione un tipo de cuenta</option>
				        <%
				            List<TipoCuenta> listaTiposCuenta = (List<TipoCuenta>)request.getAttribute("listaTiposCuenta");
				            
				            if(listaTiposCuenta != null) {
				                for(TipoCuenta tipo : listaTiposCuenta){
				        %>
				            <option value="<%=tipo.getId()%>"><%=tipo.getNombre()%></option>
				        <%
				                }
				            }
				        %>
				    </select>
				</div>
				
		        <div class="form-group">
		            <label for="numeroCuenta">Número de Cuenta:</label>
		            <input type="text" id="numeroCuenta" name="numeroCuenta" class="form-control" pattern="[0-9]{6,20}" title="El número de cuenta debe tener entre 6 y 20 dígitos numéricos" required>
		        </div>
		
		        <div class="form-group">
		            <label for="cbu">CBU:</label>
		            <input type="text" id="cbu" name="cbu" class="form-control" pattern="[0-9]{22}" minlength="22" maxlength="22" title="El CBU debe tener exactamente 22 dígitos numéricos" required>
		        </div>
		
		        <div class="form-group">
		            <label for="saldo">Saldo Inicial:</label>
		            <input type="number" id="saldo" name="saldo" class="form-control" 
		                   value="10000" readonly>
		            <small class="form-text text-muted">El saldo inicial es fijo de $10,000</small>
		        </div>
		
		        <button type="submit" class="btn btn-primary">Agregar Cuenta</button>
		    </form>
	</div>
</div>

	<%@ include file="Footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							$('#cuentasTable')
									.DataTable(
											{
												"dom" : '<"top"f>rt<"bottom"lp><"clear">',
												language : {
													url : 'https://cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
												}
											});
						});
	</script>
</body>
</html>



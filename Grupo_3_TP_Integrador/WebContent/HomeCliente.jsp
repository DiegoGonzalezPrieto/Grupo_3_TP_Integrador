<%@page import="dominio.Cliente"%>
<%@page import="dominio.Cuenta"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	if (request.getAttribute("cuentasCliente") == null) {
		response.sendRedirect("HomeClienteServlet");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home - Cliente</title>
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
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container">
		<!-- TODO: mostrar nombre del Cliente. Enlaces con ids del cliente -->
		<% 
           Cliente cliente = (Cliente)request.getAttribute("cliente");
           if(cliente != null) {
		%>
		<h1 class="display-3 my-3">Bienvenido, <%=cliente.getApellido() + " " + cliente.getNombre()%></h1>
		<%} %>
		<ul class="nav nav-pills nav-fill m-3">
			<li class="nav-item mx-2"><a class="nav-link active"
				href="Transferencia.jsp">Transferencias</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="PrestamosServlet?id=<%=cliente.getIdCliente()%>">Préstamos</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="PagoPrestamoServlet?id=<%=cliente.getIdCliente()%>">Pago de Cuotas</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="GestionDatosServlet?id=<%=cliente.getIdCliente()%>">Mis Datos</a></li>
		</ul>


		<h2 class="my-3">Mis Cuentas</h2>
		<ul class="nav flex-column">
		    <%
		        List<Cuenta> cuentasCliente = (List<Cuenta>)request.getAttribute("cuentasCliente");
		        if(cuentasCliente != null && !cuentasCliente.isEmpty()) {
		            for(Cuenta cuenta : cuentasCliente) {
		    %>
		            <li class="nav-item my-1">
		                <a class="border nav-link" href="DetallesCuenta.jsp?id=<%=cuenta.getId()%>">
		                    <span class="text-black">Cuenta <%=cuenta.getNumeroCuenta()%></span><br>
		                    <span class="text-black">CBU: <%=cuenta.getCbu()%></span><br>
		                    <span class="text-black"><%=cuenta.getTipoCuenta().getNombre()%></span>
		                </a>
		            </li>
		    <%
		            }
		        } else {
		    %>
		            <li class="nav-item my-1">
		                <div class="border nav-link">
		                    <span class="text-black">No hay cuentas disponibles</span>
		                </div>
		            </li>
		    <%
		        }
		    %>
		</ul>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
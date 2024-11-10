<%@ page import = "dominio.Cliente" %>
<!-- <%@ /page import = "dominio.Cuenta" %> -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home - Banco</title>
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
		<!-- MOSTRAMOS EL ENLACE A LAS OPCIONES QUE TENEMOS DISEÑADAS -->
		<h1 class="display-3 my-3">Bienvenido, Lucas Cervantez!</h1>
		<h1>Bienvenido, 
			<%= ((Cliente) request.getAttribute("cliente")).getNombre() %>
		 	<%= ((Cliente) request.getAttribute("cliente")).getApellido() %>
		 </h1>

		<ul class="nav nav-pills nav-fill m-3">
			<li class="nav-item mx-2"><a class="nav-link active"
				href="AutorizacionPrestamos.jsp">Autorización de Préstamos</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="AdministracionCuentas.jsp">Administración de Cuentas</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="AdministracionClientes.jsp">Administración de Clientes</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="Reportes.jsp">Reportes</a></li>
		</ul>

		<!-- LA IDEA ES QUE SEA UN RESUMEN CON CONTADORES DE VARIAS COSAS -->
		<h2 class="my-3 text-center">Resumen de Actividad</h2>

		<div class="border p-4 rounded-3">

			<div class="row text-center">
				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Clientes Activos</h5>
							<p class="card-text">
								<%= request.getAttribute("clientesActivos") %>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Cuentas Abiertas</h5>
							<p class="card-text">
								<%= request.getAttribute("cuentasAbiertas") %>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Préstamos Autorizados</h5>
							<p class="card-text">
								<%= request.getAttribute("prestamosAutorizados") %>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Préstamos Pendientes</h5>
							<p class="card-text">
								<%= request.getAttribute("prestamosPendientes") %>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- LA IDEA ES QUE MUESTRE LAS ULTIMAS CUENTAS CREADAS (TRAER CUENTAS ULTIMO ID) -->
		<h2 class="my-3">Cuentas Recientes</h2>
		<ul class="nav flex-column">
			<%
			List<Cuenta> cuentasRecientes = (List<Cuenta>) request.getAttribute("cuentasRecientes");
			for (Cuenta cuenta : cuentasRecientes) {
            %>
            
            <li class="nav-item my-1">
            	<a class="border nav-link" href="DetallesCuenta.jsp">
            	<span class="text-black">Cuenta: <%= cuenta.getId() %></span><br>
            	<span class="text-black"><%= cuenta.getTipo().getNombre() %></span><br>
            	<span class="text-black">Cliente: <%= cuenta.getCliente().getNombre() %></span><br>
            	<span class="text-black">Saldo: <%= cuenta.getSaldo() %></span></a></li>
		</ul>
		
		<!-- 
		<ul class="nav flex-column">
			ACA PODEMOS ITERAR LAS CUENTAS
			<li class="nav-item my-1"><a class="border nav-link"
				href="DetallesCuenta.jsp"><span class="text-black">Cuenta
						12345</span><br> <span class="text-black">CBU: 31289756287259</span><br>
					<span class="text-black">Caja de Ahorro ARS</span></a></li>
			<li class="nav-item my-1"><a class="border nav-link"
				href="DetallesCuenta.jsp"><span class="text-black">Cuenta
						67890</span><br> <span class="text-black">CBU: 5588884393214</span><br>
					<span class="text-black">Cuenta Corriente</span></a></li>
			<li class="nav-item my-1"><a class="border nav-link"
				href="DetallesCuenta.jsp"><span class="text-black">Cuenta
						98765</span><br> <span class="text-black">CBU: 4577773892535</span><br>
					<span class="text-black">Caja de Ahorro USD</span></a></li>
		</ul>
		 -->
		 	<%
                }
            %>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>

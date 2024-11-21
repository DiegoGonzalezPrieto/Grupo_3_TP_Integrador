<%@ page import="dominio.Cliente"%>
<%@ page import="dominio.Cuenta"%>
<%@ page import="java.util.List"%>
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
<style>
li {
	list-style-type: none;
}

.card {
	transition: transform 0.2s, box-shadow 0.2s;
}

.card a, .card a:hover {
	color: inherit;
	text-decoration: none;
}

.card:hover {
	transform: scale(1.05);
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.nav-link {
	text-align: center;
}

.container-Admin {
	margin-bottom: 2%;
	padding: 3%
}
</style>
</head>
<body>
	<%@ include file="BarraMenu.jsp"%>

	<div class="container-Admin">
		<h1 class="display-3 my-3">
			Bienvenido/a,
			<%=((Usuario) request.getSession().getAttribute("usuario")).getNombreUsuario()%>
		</h1>

		<ul class="nav nav-pills nav-fill m-4">
			<li class="nav-item mx-2"><a class="nav-link active"
				href="AdministracionClientes.jsp">Administración de clientes</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="AdministracionCuentas.jsp">Administración de cuentas</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="AutorizacionPrestamos.jsp">Autorización de préstamos</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="Reportes.jsp">Reportes</a></li>
		</ul>
	</div>

	<div class="container-Admin">
		<h1 class="display-6" style="margin-top:50px; text-align:center">Resumen de Actividad</h1><hr>
		<div class="border p-4 rounded-3">
			<div class="row text-center">
				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Clientes activos</h5>
							<p class="card-text">
								<%=request.getAttribute("clientesActivos")%>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Cuentas abiertas</h5>
							<p class="card-text">
								<%=request.getAttribute("cuentasAbiertas")%>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Préstamos autorizados</h5>
							<p class="card-text">
								<%=request.getAttribute("prestamosAutorizados")%>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">Préstamos pendientes</h5>
							<p class="card-text">
								<%=request.getAttribute("prestamosPendientes")%>
							</p>
						</div>
					</div>
				</div>

			</div>
			<div class="nav-link">
				<a href="Reportes.jsp"> Ver reportes </a>
			</div>
		</div>
	</div>

	<div class="container-Admin">
		<h1 class="display-6" style="margin-top:50px; text-align:center">Cuentas Recientes</h1><hr>
		<ul class="flex-column">
			<%
				List<Cuenta> cuentasRecientes = (List<Cuenta>) request.getAttribute("cuentasRecientes");
				for (Cuenta cuenta : cuentasRecientes) {
					String numero = (String) request.getAttribute("numeroCuenta_" + cuenta.getNumeroCuenta());
					String tipo = (String) request.getAttribute("tipoCuenta_" + cuenta.getNumeroCuenta());
					String nombre = (String) request.getAttribute("nombreCliente_" + cuenta.getNumeroCuenta());
					String apellido = (String) request.getAttribute("apellidoCliente_" + cuenta.getNumeroCuenta());
					String saldo = (String) request.getAttribute("saldo_" + cuenta.getNumeroCuenta());
					int id = (int) request.getAttribute("id_" + cuenta.getNumeroCuenta());
			%>

			<li class="nav-item my-1 ">
				<div class="card">
					<a class="border nav-link" href="ModificarCuentaServlet?id=<%=id%>">
						<h4><%=apellido%>,
							<%=nombre%></h4>
						<h6 class="card-title">
							CUENTA:
							<%=numero%></h6>
						<h6 class="card-subtitle mb-2 text-muted"><%=tipo%></h6>
						<h6 class="card-text">
							SALDO: $<%=saldo%></h6>
					</a>
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

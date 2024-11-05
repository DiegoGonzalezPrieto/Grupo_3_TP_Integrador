<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

		<h1 class="display-3 my-3">Bienvenida, Alba Martínez!</h1>

		<ul class="nav nav-pills nav-fill m-3">
			<li class="nav-item mx-2"><a class="nav-link active"
				href="Transferencia.jsp">Transferencias</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="SolicitudPrestamo.jsp">Préstamos</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="PagoPrestamo.jsp">Pago de Cuotas</a></li>
			<li class="nav-item mx-2"><a class="nav-link active"
				href="DatosCliente.jsp">Mis Datos</a></li>
		</ul>


		<h2 class="my-3">Mis Cuentas</h2>
		<ul class="nav flex-column">
			<!-- TODO: For con cuentas del cliente y enlaces a los detalles -->
			<li class="nav-item my-1"><a class="border nav-link"
				href="DetallesCuenta.jsp"><span class="text-black">Cuenta
						1</span><br> <span class="text-black">CBU: 31289756287259</span> <br>
					<span class="text-black">Caja de Ahorro ARS</span></a></li>
			<li class="nav-item my-1"><a class="border nav-link"
				href="DetallesCuenta.jsp"><span class="text-black">Cuenta
						1</span><br> <span class="text-black">CBU: 5588884393214</span> <br>
					<span class="text-black">Caja de Ahorro USD</span></a></li>
			<li class="nav-item my-1"><a class="border nav-link"
				href="DetallesCuenta.jsp"><span class="text-black">Cuenta
						1</span><br> <span class="text-black">CBU: 4577773892535</span> <br>
					<span class="text-black">Cuenta Corriente</span></a></li>
		</ul>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Navbar</title>
		<!-- CSS -->
		<!-- <link rel="stylesheet" href="./css/styles.css"> -->
		<!-- JS -->
		<!-- <link rel="stylesheet" href="./js/javascript.js"> -->
	</head>
	<body>
		<nav class="navbar" style="overflow:hidden; padding:2px">
			<div class="navbar-container" style="display:flex; justify-content:space-between; align-items:center" >
			    <a href="#" class="navbar-logo">Banco XYZ</a>
			    <ul class="navbar-menu" style="list-style-type: none; text-decoration:none; display:flex">
			    	<li style="margin:5px">Usuario Tal</li>
			    	<li style="margin:5px"><a href="HomeCliente.jsp">Inicio</a></li>
			    	<li style="margin:5px"><a href="DetallesCuenta.jsp">Mis cuentas</a></li>
			    	<li style="margin:5px"><a href="Prestamos.jsp">Préstamos</a></li>
			    	<li style="margin:5px"><a href="Perfil.jsp">Perfil</a></li>
			    	<li style="margin:5px"><a href="Login.jsp">Salir</a></li>
			    </ul>
			</div>
		</nav>
	</body>
</html>

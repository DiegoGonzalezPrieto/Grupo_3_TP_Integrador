<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Clientes</title>
</head>
<body>
	<h1>Administración de Clientes</h1>
	<form action="AdministracionClientesServlet" method="POST">
		<label for="nombre"></label>
		<input id="nombre" name="nombre">
		<button type="submit">Crear Cliente</button>
		<label for="apellido"></label>
	</form>
</body>
</html>
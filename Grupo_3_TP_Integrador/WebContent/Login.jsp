<%@page import="dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous">
</script>
<style>
	body, html {
		height: 100%;
		margin: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: #f8f9fa;
	}
</style>
</head>
<body>
		<!-- Agregando "flex-wrap: wrap" o "flex-direction: column" al style de body, 
		hmtl se ve titulo y abajo form, en vez de un elemento al lado del otro-->
<div>

	<h1 class="display-3 my-3" style="text-align: center;">- BANCO G3L4 -</h1>
</div>
	<div class="container">
		<h2 class="my-3">Iniciar sesión</h2>

		<form action="LoginServlet" method="POST" class="border border-2 rounded p-4">
			<label for="usuario" class="form-label">Usuario</label> 
			<input type="text" placeholder="Usuario" id="usuario" name="usuario" required class="form-control"><br>
			<label for="password" class="form-label">Contraseña</label>
			<input type="password" placeholder="Contraseña" id="password" name="password" required class="form-control"><br>
			<div class="text-center">
				<button type="submit" class="btn btn-dark">Ingresar</button>
			</div>
		</form>
	</div>
</body>
</html>
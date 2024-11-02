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
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">

		<h1 class="display-3 my-3">Login</h1>

		<form action="" method="POST" class="w-50 border border-2 rounded p-4">
			<label for="usuario" class="form-label">Usuario</label> <input
				type="text" placeholder="Usuario" id="usuario" name="usuario"
				required class="form-control"> <br> <label
				for="password" class="form-label">Contraseña</label> <input
				type="password" placeholder="Contraseña" id="password"
				name="password" required class="form-control"> <br>
			<div class="text-center">
				<button type="submit" class="btn btn-dark">Ingresar</button>
			</div>
		</form>
	</div>
</body>
</html>
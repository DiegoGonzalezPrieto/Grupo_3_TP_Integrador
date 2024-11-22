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
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous">
</script>
<style>
	body, html {
	    height: 100%;
	    margin: 0;
	    display: grid;
	    place-items: center;
	    background-color: #f8f9fa;
	}

	.container-login {
	    display: grid;
	    grid-template-columns: auto auto;
	    gap: 20px;
	    align-items: center;
	}
	
	.mensajes {
	    grid-column: span 3;
	    text-align: center;
	    margin-top: 20px;
	}
	
	.ocultar { 
		visibility: hidden;
	}

</style>
</head>
<body>
	<div class="container-login">
		<div>
			<h1 class="display-3 my-3" style="text-align: center; width:300px;">- BANCO L4B G3 -</h1>
		</div>
		<div class="container" style="width:500px">
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
		<div class="row container mensajes">
			<%
			String error = (String) request.getAttribute("error");
			%>
			<div id="mensaje-error" class="alert alert-danger mt-3 ocultar" role="alert">
				<%= error != null && !error.isEmpty() ? error : "&nbsp;" %>
			</div>
		</div>
	</div>
	<script>
	//script para ocultar el mensaje o que se vaya en fade tras aparecer
	document.addEventListener('DOMContentLoaded', function() {
	    const errorMessage = $('#mensaje-error');
	    if (errorMessage.text().trim() !== "") {
	        errorMessage.removeClass('ocultar');
	        setTimeout(function() {
	            errorMessage.fadeOut('slow', function() {
	                errorMessage.addClass('ocultar').show();
	            });
	        }, 2500); // se puede cambiar la duración (ms)
	    }
	});
	</script>

</body>
</html>
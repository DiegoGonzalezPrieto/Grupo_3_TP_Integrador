<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Banco G3-L4</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" 
        aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <span class="nav-link">
                    
                    NombreUsuario
                    <% 
                    	//String username = (String) session.getAttribute("username");
                        //out.print(username);
                    %>
                    
                    </span>
                </li>
                <% 
                    //String role = (String) session.getAttribute("role");
                    //if ("admin".equals(role)) {
                %>
                	<li class="nav-item">
                    	<a class="nav-link" href="HomeAdministrador.jsp">Inicio</a>
                	</li>
                    <li class="nav-item">
                        <a class="nav-link" href="AdministracionClientes.jsp">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AdministracionCuentas.jsp">Cuentas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AutorizacionPrestamos.jsp">Préstamos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Reportes.jsp">Reportes</a>
                    </li>
                <% 
                    //} else if ("cliente".equals(role)) {
                %>
                <!-- 
                	<li class="nav-item">
                        <a class="nav-link" href="HomeCliente.jsp">Mis cuentas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Prestamos.jsp">Préstamos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Perfil.jsp">Perfil</a>
                    </li>
                 -->
                <% 
                    //}
                %>
                <li class="nav-item">
    				<a class="nav-link" href="Login.jsp"><i class="fas fa-sign-out-alt"></i></a>
				</li>
            </ul>
        </div>
    </nav>

    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>


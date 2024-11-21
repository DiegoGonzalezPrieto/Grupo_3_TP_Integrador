<%@page import="dominio.Cliente"%>
<%@page import="dominio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	Cliente clienteMenu = (Cliente) session.getAttribute("cliente");
	if (usuario == null) {

		response.sendRedirect("Login.jsp");
	}

	String urlActual = request.getRequestURL().toString();
%>

<style>
.navbar {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: #f8f9fa;
    z-index: 1000;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    box-sizing: border-box;
}

.navbar-brand {
    margin: 0 30px;
}

.navbar-nav {
    margin-left: auto;
}

.navbar-nav .nav-item {
    margin-left: 20px;
}

</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="<%= usuario.esAdmin() ? "HomeAdministradorServlet" : "HomeClienteServlet"%>" 
	style="margin: 30px">Banco L4B - G3</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav ml-auto">
			<%
				if (usuario != null) {
			%>
			<li class="nav-item"><a
				class="nav-link btn btn-info rounded-5 text-white"
				href="<%=usuario.esAdmin() ? "HomeAdministradorServlet"
						: "GestionDatosServlet?id=" + clienteMenu.getIdCliente()%>">
					<%=clienteMenu != null ? clienteMenu.getNombre() + " " + clienteMenu.getApellido()
						: usuario.getNombreUsuario()%></a></li>


			<%
				if (usuario != null && usuario.esAdmin()) {
			%>

			<li class="nav-item" title="Inicio"><a
				class="nav-link <%=urlActual.toLowerCase().contains("home") ? "active" : ""%>"
				href="HomeAdministradorServlet">Inicio</a></li>
			<li class="nav-item" title="Administración de Clientes"><a
				class="nav-link <%=urlActual.toLowerCase().contains("cliente") ? "active" : ""%>"
				href="AdministracionClientes.jsp">Clientes</a></li>
			<li class="nav-item" title="Administración de Cuentas"><a
				class="nav-link <%=urlActual.toLowerCase().contains("cuenta") ? "active" : ""%>"
				href="AdministracionCuentas.jsp">Cuentas</a></li>
			<li class="nav-item" title="Autorización de Préstamos"><a
				class="nav-link <%=urlActual.toLowerCase().contains("autorizacion") ? "active" : ""%>"
				href="AutorizacionPrestamos.jsp">Préstamos</a></li>
			<li class="nav-item" title="Reportes"><a
				class="nav-link <%=urlActual.toLowerCase().contains("reporte") ? "active" : ""%>"
				href="Reportes.jsp">Reportes</a></li>
			<%
				} else {
			%>
			<li class="nav-item" title="Inicio"><a
				class="nav-link <%=urlActual.toLowerCase().contains("home") ? "active" : ""%>"
				href="HomeClienteServlet">Inicio</a></li>
			<li class="nav-item" title="Transferencia"><a class="nav-link <%= urlActual.toLowerCase().contains("transferencia") ? "active" : "" %>"
				href="TransferenciaServlet?cliente=<%=clienteMenu.getIdCliente()%>">Transferencia</a></li>			
			<li class="nav-item" title="Préstamos"><a class="nav-link <%= urlActual.toLowerCase().contains("prestamo") ? "active" : "" %>"
				href="PrestamosServlet?id=<%=clienteMenu.getIdCliente()%>">Préstamos</a></li>
			<li class="nav-item" title="Mis Datos"><a class="nav-link <%= urlActual.toLowerCase().contains("datos") ? "active" : "" %>"
				href="GestionDatosServlet?id=<%=clienteMenu.getIdCliente()%>">Mis
					Datos</a></li>
			<%
				}
			%>
			<%
				}
			%>
      
			<li class="nav-item">
				<a class="nav-link" href="LoginServlet" id="logout" title="Salir">
					<i class="fas fa-sign-out-alt"></i>
				</a></li>
		</ul>
	</div>
</nav>

<script type="text/javascript">
logout.addEventListener('click', (event) => {
	const confirma = confirm('¿Estás seguro/a que deseas cerrar sesión?');
	if (!confirma)
		event.preventDefault();
})
</script>
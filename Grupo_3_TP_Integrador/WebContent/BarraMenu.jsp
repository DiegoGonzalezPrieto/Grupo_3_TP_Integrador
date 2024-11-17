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
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#!" style="margin: 30px">Banco G3-L4</a>
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
				href="<%=usuario.esAdmin() ? "HomeAdministradorServlet" : "GestionDatosServlet?id="+ clienteMenu.getIdCliente()%>">
					<%=clienteMenu != null ? clienteMenu.getNombre() + " " + clienteMenu.getApellido()
						: usuario.getNombreUsuario()%></a></li>


			<%
				if (usuario != null && usuario.esAdmin()) {
			%>

			<li class="nav-item"><a class="nav-link"
				href="HomeAdministradorServlet">Inicio</a></li>
			<li class="nav-item"><a class="nav-link"
				href="AdministracionClientes.jsp">Adm. de Clientes</a></li>
			<li class="nav-item"><a class="nav-link"
				href="AdministracionCuentas.jsp">Adm. de Cuentas</a></li>
			<li class="nav-item"><a class="nav-link"
				href="AutorizacionPrestamos.jsp">Autorización de Préstamos</a></li>
			<li class="nav-item"><a class="nav-link" href="Reportes.jsp">Reportes</a>
			</li>
			<%
				} else {
			%>
			<li class="nav-item"><a class="nav-link"
				href="HomeClienteServlet">Inicio</a></li>
			<li class="nav-item"><a class="nav-link"
				href="PrestamosServlet?id=<%=clienteMenu.getIdCliente()%>">Préstamos</a></li>
			<li class="nav-item"><a class="nav-link"
				href="GestionDatosServlet?id=<%=clienteMenu.getIdCliente()%>">Mis
					Datos</a></li>
			<li class="nav-item"><a class="nav-link"
				href="TransferenciaServlet?cliente=<%=clienteMenu.getIdCliente()%>">Transferencia</a></li>
			<%
				}
			%>
			<%
				}
			%>
			<li class="nav-item"><a class="nav-link" href="LoginServlet"><i
					class="fas fa-sign-out-alt"></i></a></li>
		</ul>
	</div>
</nav>


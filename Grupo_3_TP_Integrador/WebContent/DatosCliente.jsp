<%@page import="dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Datos Personales</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-4 col-md-8 mx-auto">
		<div class="card bg-light">
			<div class="card-header bg-primary text-white">
				<h4 class="mb-0">Datos Personales</h4>
			</div>
			 <% 
			 	Cliente cliente = (Cliente)request.getAttribute("cliente");
                    if(cliente != null) {
             %>
			<div class="card-body">
				<form action="HomeCliente.jsp" method="GET">
					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Nombre:</label> <input type="text"
									class="form-control w-50" value="<%=cliente.getNombre() %>" readonly>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Apellido:</label> <input type="text"
									class="form-control w-50" value="<%= cliente.getApellido() %>" readonly>
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Nro. Documento o CUIL:</label>
								<div class="input-group w-50">
									<select class="form-select w-25">
										<option value="<%=cliente.getDni() %>" selected>DNI</option>
										<option value="<%=cliente.getCuil() %>">CUIL</option>
									</select> <input type="text" class="form-control" value="35035199"
										readonly>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Fecha Nac.:</label> <input type="text"
									class="form-control w-50" value="<%= cliente.getFechaNacimiento() %>" readonly>
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Sexo:</label> <input type="text"
									class="form-control w-50" value="<%=cliente.getGenero() %>" readonly>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Nacionalidad:</label> <input
									type="text" class="form-control w-50" value="<%=cliente.getNacionalidad() %>"
									readonly>
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Dirección:</label> <input type="text"
									class="form-control w-50" value="<%=cliente.getDireccion() %>" readonly>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Localidad:</label> <input type="text"
									class="form-control w-50" value="<%= cliente.getDireccion() %>" readonly>
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Provincia:</label> <input type="text"
									class="form-control w-50" value="<%=cliente.getProvincia() %>" readonly>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="form-label">Email:</label> <input type="email"
									class="form-control w-50" value="<%=cliente.getCorreoElectronico() %>" readonly>
							</div>
						</div>
					</div>

					<div class="text-center mt-4">
						<a href="AdministracionClientes.jsp" class="btn btn-primary">Volver</a>
					</div>
				</form>
				 <% }  %>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
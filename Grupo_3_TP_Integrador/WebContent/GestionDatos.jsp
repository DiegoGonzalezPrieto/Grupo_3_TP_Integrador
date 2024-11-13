<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@page import="dominio.Cliente" %>
<%@page import="dominio.Nacionalidad" %>
<%@page import="dominio.Localidad" %>
<%@page import="dominio.Provincia" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Administración de Clientes</title>
<!-- JQuery + Datatables -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
<link
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css"
	rel="stylesheet"></link>
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-5">
    <h2>Datos personales</h2>
   		<form action="GestionDatosServlet" method="POST">
  	<%
  		
   		Cliente cliente = new Cliente();
   		cliente = (Cliente) request.getAttribute("cliente");
   		
   		ArrayList<Nacionalidad> listaNaciones = null;
   		ArrayList<Localidad> listaLocalidades = null;
   		ArrayList<Provincia> listaProvincias = null;
   		if(request.getAttribute("localidades") != null){
   			listaLocalidades = (ArrayList<Localidad>) request.getAttribute("localidades");
   		}
   		if(request.getAttribute("provincias") != null){
   			listaProvincias = (ArrayList<Provincia>) request.getAttribute("naciones");
   		}
   		
   		if(request.getAttribute("naciones") != null){
   			listaNaciones = (ArrayList<Nacionalidad>) request.getAttribute("naciones");
   		}
   		
    %>
         		
        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= cliente.getNombre() == null ? "" : cliente.getNombre() %>" >
        </div>
        <div class="form-group">
            <label for="nombre">Apellido:</label>
            <input type="text" class="form-control" id="apellido" name="apellido" value="<%= cliente.getApellido() == null ? "" : cliente.getApellido() %>" >
        </div>
        <div class="form-group">
            <label for="nombre">DNI:</label>
            <input type="text" class="form-control" id="dni" name="dni" value="<%= cliente.getDni() == null ? "" : cliente.getDni() %>">
        </div>
        <div class="form-group">
            <label for="nombre">CUIL:</label>
            <input type="text" class="form-control" id="cuil" name="cuil" value="<%= cliente.getCuil() == null ? "" : cliente.getCuil() %>">
        </div>
        <div class="form-group">
            <label for="nombre">Genero:</label>
            <input type="text" class="form-control" id="genero" name="genero" value="<%= cliente.getGenero() == null ? "" : cliente.getGenero() %>">
        </div>
        <div class="form-group">
            <label for="nombre">E-mail:</label>
            <input type="text" class="form-control" id="email" name="email" value="<%= cliente.getCorreoElectronico() == null ? "" : cliente.getCorreoElectronico() %>">
        </div>
        <div class="form-group">
            <label for="nombre">Telefono:</label>
            <input type="text" class="form-control" id="telefono" name="telefono" value="<%= cliente.getTelefono() == null ? "" : cliente.getTelefono() %>">
        </div>
        <div class="form-group">
            <label for="nombre">Fecha de nacimiento:</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="<%= cliente.getFechaNacimiento() == null ? "" : cliente.getFechaNacimiento()%>"d>
        </div>
        <div class="form-group">
            <label for="nombre">Dirección:</label>
            <input type="text" class="form-control" id="direccion" name="direccion" value="<%= cliente.getDireccion() == null ? "" : cliente.getDireccion() %>">
        </div>
        <div class="form-group">
           <label for="nombre">Localidad:</label>
    		<select class="form-select form-select-sm" aria-label="Small select example" >
    		<option selected><%= cliente.getLocalidad().getNombre() %></option>
		        <% 
		        
		        if (listaLocalidades != null) {
		        	for (Localidad lo : listaLocalidades) { %>
		            <option value="<%= lo.getId() %>"><%= lo.getNombre() %></option>
		        <% } }%>  
   			 </select>
		</div>
        <div class="form-group">
            <label for="nombre">Provincia:</label>
            <select class="form-select form-select-sm" aria-label="Small select example" >
			    <option selected><%= cliente.getProvincia().getNombre() %></option>
			    <% 
		        
		        if (listaProvincias != null) {
		        	for (Provincia pro : listaProvincias) { %>
		            <option  value="<%= pro.getId() %>"><%= pro.getNombre() %></option>
		        <% } }%>  
		    </select>
        </div>
        <div class="form-group">
            <label for="nombre">Nacionalidad:</label>
            <select class="form-select form-select-sm" aria-label="Small select example">
			    <option selected><%= cliente.getNacionalidad().getNombre() %></option>
			    <% 
		        
		        if (listaNaciones != null) {
		        	for (Nacionalidad nac : listaNaciones) { %>
		            <option value="<%= nac.getId() %>"><%= nac.getNombre() %></option>
		        <% } }%> 
		    </select>
        </div>

		 <input type="submit" value="Editar" name="btnEditar" class="btn btn-primary">
		 <input type="submit" value="Volver" name="btnVolver" class="btn btn-primary">
        
       
    

    </form>
</div>
</body>
</html>
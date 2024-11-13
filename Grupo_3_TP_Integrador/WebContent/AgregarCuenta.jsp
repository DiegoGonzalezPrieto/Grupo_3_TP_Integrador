<%@page import="dominio.TipoCuenta"%>
<%@page import="dominio.Cliente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Cuenta</title>
</head>
<body>
<%@ include file="BarraMenu.jsp"%>
<div class="container mt-4">
	<div class="mt-4">
		<div class="card-header bg-success text-white">
                <h2 class="mb-0">Agregar Nueva Cuenta</h2>
        </div>
	    	
			    <form action="AgregarCuentaServlet" method="post">
			        <div class="form-group">
			            <label for="cliente">Cliente:</label>
			            <select id="cliente" name="cliente" class="form-control" required>
			                <option value="">Seleccione un cliente</option>
			                <%
			                    List<Cliente> listaClientes = (List<Cliente>)request.getAttribute("listaClientes");
			                    if(listaClientes != null) {
			                        for(Cliente cliente : listaClientes) {
			                %>
			                    <option value="<%=cliente.getIdCliente()%>">
			                        <%=cliente.getApellido() + ", " + cliente.getNombre()%>
			                    </option>
			                <%
			                        }
			                    }
			                %>
			            </select>
			        </div>
			
			        <div class="form-group">
					    <label for="tipoCuenta">Tipo de Cuenta:</label>
					    <select id="tipoCuenta" name="tipoCuenta" class="form-control" required>
					        <option value="">Seleccione un tipo de cuenta</option>
					        <%
					            List<TipoCuenta> listaTiposCuenta = (List<TipoCuenta>)request.getAttribute("listaTiposCuenta");
					            
					            if(listaTiposCuenta != null) {
					                for(TipoCuenta tipo : listaTiposCuenta){
					        %>
					            <option value="<%=tipo.getId()%>"><%=tipo.getNombre()%></option>
					        <%
					                }
					            }
					        %>
					    </select>
					</div>
					<% 
	                    Long nuevaCuenta = (Long)request.getAttribute("nuevaCuenta");
	                    if(nuevaCuenta != null) {
                	%>
			        <div class="form-group">
			            <label for="numeroCuenta">Número de Cuenta:</label>
			            <input type="text" id="numeroCuenta" name="numeroCuenta" class="form-control" value="<%= nuevaCuenta%>" readonly>
			        </div>
			        <% } %>
			        
					<% 
	                    String nuevoCBU = (String)request.getAttribute("nuevoCBU");
	                    if(nuevoCBU != null) {
                	%>
			        <div class="form-group">
			            <label for="cbu">CBU:</label>
			            <input type="text" id="cbu" name="cbu" class="form-control" value="<%= nuevoCBU %>" readonly>
			        </div>
					<%} %>
			        <div class="form-group">
			            <label for="saldo">Saldo Inicial:</label>
			            <input type="number" id="saldo" name="saldo" class="form-control" 
			                   value="10000" readonly>
			            <small class="form-text text-muted">El saldo inicial es fijo de $10,000</small>
			        </div>
					<div class="text-center mb-3">
					    <a type="button" class="btn btn-outline-secondary me-2" href="AdministracionCuentasServlet">Cancelar</a>
					    <button type="submit" class="btn btn-outline-success">Agregar Cuenta</button>
					</div>

	
			    </form>
		</div>
</div>
</body>
</html>
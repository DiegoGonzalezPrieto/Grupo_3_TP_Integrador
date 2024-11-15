<%@page import="dominio.Cuenta"%>
<%@page import="dominio.TipoCuenta"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Cuenta</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="BarraMenu.jsp"%>
    <div class="container mt-4">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">Modificar Cuenta</h4>
            </div>
            <div class="card-body">
                <% 
                    Cuenta cuenta = (Cuenta)request.getAttribute("cuenta");
                    if(cuenta != null) {
                %>
                <form action="ModificarCuentaServlet" method="post">
                    <input type="hidden" name="idCuenta" value="<%=cuenta.getId()%>">
                   
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Cliente:</label>
                        <div class="col-sm-9">
                            <p class="form-control-plaintext">
                                <%=cuenta.getCliente().getApellido() + ", " + cuenta.getCliente().getNombre()%>
                            </p>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Número de Cuenta:</label>
                        <div class="col-sm-9">
                            <p class="form-control-plaintext"><%=cuenta.getNumeroCuenta()%></p>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">CBU:</label>
                        <div class="col-sm-9">
                            <p class="form-control-plaintext"><%=cuenta.getCbu()%></p>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Tipo de Cuenta:</label>
                        <div class="col-sm-9">
                            <select name="tipoCuenta" class="form-control" required>
                                <%
                                    List<TipoCuenta> tiposCuenta = (List<TipoCuenta>)request.getAttribute("listaTiposCuenta");
                                    if(tiposCuenta != null) {
                                        for(TipoCuenta tipo : tiposCuenta) {
                                %>
                                    <option value="<%=tipo.getId()%>" 
                                            <%=tipo.getId() == cuenta.getTipoCuenta().getId() ? "selected" : ""%>>
                                        <%=tipo.getNombre()%>
                                    </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Saldo:</label>
                        <div class="col-sm-9">
                            <input type="number" name="saldo" class="form-control" 
                                   value="<%=cuenta.getSaldo()%>" step="0.01" required>
                        </div>
                    </div>
                    <div class="text-right">
                        <a type="button" class="btn btn-secondary" href="AdministracionCuentasServlet">Cancelar</a>
                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    </div>
                </form>
                <% }  %>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
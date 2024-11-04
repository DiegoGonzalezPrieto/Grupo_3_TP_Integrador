<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="BarraMenu.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administración de Cuentas</title>
    
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet">
    
    
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    
    <style>
        .dataTables_filter {
            float: left !important;
            text-align: left !important;
        }
        .dataTables_filter label {
            font-weight: bold;
        }
        .dataTables_filter input {
            margin-left: 0.5em;
            display: inline-block;
            width: auto;
        }
    </style>
    
</head>
<body>
    <div class="container mt-4">
        <h1>Administración de Cuentas</h1>

        <div class="mt-4">
            <h2>Listado de Cuentas</h2>
            <table id="cuentasTable" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Fecha de Creación</th>
                        <th>Tipo de Cuenta</th>
                        <th>Número de Cuenta</th>
                        <th>CBU</th>
                        <th>Saldo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Cliente 1</td>
                        <td>2024-11-01</td>
                        <td>Caja de Ahorro</td>
                        <td>123456789</td>
                        <td>12345678901234567890</td>
                        <td>$10,000</td>
                        <td>
                            <a href="ModificarCuentaServlet?id=1" class="btn btn-warning btn-sm">Modificar</a>
                            <a href="EliminarCuentaServlet?id=1" class="btn btn-danger btn-sm" 
						    	onclick="return confirm('¿Seguro que desea eliminar esta cuenta?')">
						        <i class="fa-regular fa-trash-can"></i>
						    </a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="mt-4">
            <h2>Agregar Nueva Cuenta</h2>
            <form action="AgregarCuentaServlet" method="post">
                <div class="form-group">
                    <label for="cliente">Cliente:</label>
                    <select id="cliente" name="cliente" class="form-control">
                        <option value="1">Cliente 1</option>
                        <option value="2">Cliente 2</option>
                    </select>
                    <a href="AdministracionClientes.jsp">Agregar cliente</a>
                </div>
                <div class="form-group">
                    <label for="tipoCuenta">Tipo de Cuenta:</label>
                    <select id="tipoCuenta" name="tipoCuenta" class="form-control">
                        <option value="cajaAhorro">Caja de Ahorro</option>
                        <option value="cuentaCorriente">Cuenta Corriente</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="numeroCuenta">Número de Cuenta:</label>
                    <input type="text" id="numeroCuenta" name="numeroCuenta" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="cbu">CBU:</label>
                    <input type="text" id="cbu" name="cbu" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="saldo">Saldo Inicial:</label>
                    <input type="number" id="saldo" name="saldo" class="form-control" min="10000" step="0.01" placeholder="Ingrese el saldo inicial">
                </div>
                <button type="submit" class="btn btn-primary">Agregar Cuenta</button>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#cuentasTable').DataTable({
                "dom": '<"top"f>rt<"bottom"lp><"clear">'
            });
        });
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="BarraMenu.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Administración de Cuentas</title>
	<!-- CSS -->
	<!-- <link rel="stylesheet" href="./css/styles.css"> -->
</head>
<body>
	<h1>Administración de Cuentas</h1>
	<div class="container">
        <h2>Agregar Nueva Cuenta</h2>
        <form action="AgregarCuentaServlet" method="post">
            <label for="cliente">Cliente:</label>
            <select id="cliente" name="cliente">
                <option value="1">Cliente 1</option>
                <option value="2">Cliente 2</option>
            </select><br>
            <label for="tipoCuenta">Tipo de Cuenta:</label>
            <select id="tipoCuenta" name="tipoCuenta">
                <option value="cajaAhorro">Caja de Ahorro</option>
                <option value="cuentaCorriente">Cuenta Corriente</option>
            </select><br>
            <label for="numeroCuenta">Número de Cuenta:</label>
            <input type="text" id="numeroCuenta" name="numeroCuenta" required>
            <label for="cbu">CBU:</label>
            <input type="text" id="cbu" name="cbu" required><br>
            <label for="saldo">Saldo Inicial:</label>
            <input type="number" id="saldo" name="saldo" value="10000" readonly>
            <input type="submit" value="Agregar Cuenta">
        </form>

        <h2>Listado de Cuentas</h2>
        <form action="BuscarCuentasServlet" method="get">
            <label for="buscar">Buscar:</label>
            <input type="text" id="buscar" name="buscar" placeholder="Buscar por cliente, tipo de cuenta, número de cuenta o CBU" style="width:30%">
            <input type="submit" value="Buscar">
        </form>
        <table>
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
                        <a href="ModificarCuentaServlet?id=1">Modificar</a>
                        <a href="EliminarCuentaServlet?id=1">Eliminar</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
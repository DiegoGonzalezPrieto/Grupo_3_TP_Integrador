<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="esp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>
</head>
<body>
<h1>Gestión de Prestamos</h1>

<button onclick="window.location.href='SolicitudPrestamo.jsp'">Nuevo Prestamo</button>

<h2>Mis Prestamos Actuales</h2>

<table border="1">
	<thead>
		<tr>	
			<th>Cuenta</th>
			<th>Fecha Solicitud</th>
			<th>Cuotas</th>
			<th>Importe a Pagar</th>
			<th>Estado</th>
			<th>Acciones</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Cuenta 1</td>
                <td>2023-01-01</td>
                <td>12</td>
                <td>$12,000.00</td>
                <td>Aprobado</td>
                <td><a href="PagoPrestamo.jsp?id=1">Pagar</a></td>
            </tr>
            <tr>
                <td>Cuenta 2</td>
                <td>2023-02-01</td>
                <td>6</td>
                <td>$6,000.00</td>
                <td>Rechazado</td>
                <td>No disponible</td>
            </tr>
            <tr>
                <td>Cuenta 2</td>
                <td>2023-04-01</td>
                <td>12</td>
                <td>$45,000.00</td>
                <td>En Proceso</td>
                <td>No disponible</td>
            </tr>
	</tbody>
</table>

</body>
</html>
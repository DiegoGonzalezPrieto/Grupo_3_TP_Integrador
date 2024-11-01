<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administraci�n de Clientes</title>
</head>
<body>
	<h1>Administraci�n de Clientes/Usuarios</h1>
	
	<form action="AdministracionClientesServlet" method="POST">
		<input id="filtro" name="filtro">
		<button type="submit">Buscar</button>
		<button type="submit" style="background-color: green; color: white">Nuevo cliente</button>
		<button type="submit" style="background-color: green; color: white">Nuevo Usuario</button>
		<button type="submit" style="background-color: yellow; color: black; ">Modificar Usuario</button>
		<button type="submit" style="background-color: red; color: white">Eliminar Usuario</button>
		<table border="1">
    <tr>
        <th>DNI</th>
        <th>CUIL</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Sexo</th>
        <th>Nacionalidad</th>
        <th>Fecha de Nacimiento</th>
        <th>Direcci�n</th>
        <th>Localidad</th>
        <th>Provincia</th>
        <th>Correo Electr�nico</th>
        <th>Tel�fono</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    <tr>
        <td>30567890</td>
        <td>20-30567890-5</td>
        <td>Luc�a</td>
        <td>P�rez</td>
        <td>F</td>
        <td>Argentina</td>
        <td>1990-04-12</td>
        <td>Calle Falsa 123</td>
        <td>Buenos Aires</td>
        <td>Buenos Aires</td>
        <td>lucia.perez@example.com</td>
        <td>+54 11 1234-5678</td>
        <td><button style="background-color: yellow; color: black">Editar</button></td>
        <td><button style="background-color: red; color: white">Eliminar</button></td>
    </tr>
    <tr>
        <td>28456789</td>
        <td>20-28456789-3</td>
        <td>Mart�n</td>
        <td>Gonz�lez</td>
        <td>M</td>
        <td>Argentina</td>
        <td>1985-08-21</td>
        <td>Avenida Siempreviva 742</td>
        <td>C�rdoba</td>
        <td>C�rdoba</td>
        <td>martin.gonzalez@example.com</td>
        <td>+54 351 4321-9876</td>
        <td><button style="background-color: yellow; color: black">Editar</button></td>
        <td><button style="background-color: red; color: white">Eliminar</button></td>
    </tr>
    <tr>
        <td>32789456</td>
        <td>27-32789456-1</td>
        <td>Ana</td>
        <td>Ram�rez</td>
        <td>F</td>
        <td>Argentina</td>
        <td>1992-11-05</td>
        <td>Los �lamos 456</td>
        <td>Rosario</td>
        <td>Santa Fe</td>
        <td>ana.ramirez@example.com</td>
        <td>+54 341 6789-1234</td>
       <td><button style="background-color: yellow; color: black">Editar</button></td>
        <td><button style="background-color: red; color: white">Eliminar</button></td>
    </tr>
</table>
	</form>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Administración de Clientes</title>
    <!-- JQuery + Datatables -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
	<link  href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css" rel="stylesheet"></link>
	<!-- Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center mb-4">Administración de Clientes/Usuarios</h1>
    
    <form action="AdministracionClientesServlet" method="POST" class="mb-4">
        <div class="input-group mb-3">
            <input type="text" id="filtro" name="filtro" class="form-control" placeholder="Buscar cliente o usuario">
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
        <div class="d-flex gap-2 mb-4">
            <button type="submit" class="btn btn-success">Nuevo cliente</button>
            <button type="submit" class="btn btn-success">Nuevo Usuario</button>
            <button type="submit" class="btn btn-warning text-dark">Modificar Usuario</button>
            <button type="submit" class="btn btn-danger">Eliminar Usuario</button>
        </div>
    </form>

    <table id="clientesTable" class="table table-striped table-bordered" style="width:80%">
        <thead class="table-dark">
            <tr>
                <th>DNI</th>
                <th>CUIL</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Sexo</th>
                <th>Nacionalidad</th>
                <th>Fecha de Nacimiento</th>
                <th>Dirección</th>
                <th>Localidad</th>
                <th>Provincia</th>
                <th>Correo Electrónico</th>
                <th>Teléfono</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>30567890</td>
                <td>20-30567890-5</td>
                <td>Lucía</td>
                <td>Pérez</td>
                <td>F</td>
                <td>Argentina</td>
                <td>1990-04-12</td>
                <td>Calle Falsa 123</td>
                <td>Buenos Aires</td>
                <td>Buenos Aires</td>
                <td>lucia.perez@example.com</td>
                <td>+54 11 1234-5678</td>
                <td><button class="btn btn-warning btn-sm">Editar</button></td>
                <td><button class="btn btn-danger btn-sm">Eliminar</button></td>
            </tr>
            <tr>
                <td>28456789</td>
                <td>20-28456789-3</td>
                <td>Martín</td>
                <td>González</td>
                <td>M</td>
                <td>Argentina</td>
                <td>1985-08-21</td>
                <td>Avenida Siempreviva 742</td>
                <td>Córdoba</td>
                <td>Córdoba</td>
                <td>martin.gonzalez@example.com</td>
                <td>+54 351 4321-9876</td>
                <td><button class="btn btn-warning btn-sm">Editar</button></td>
                <td><button class="btn btn-danger btn-sm">Eliminar</button></td>
            </tr>
            <tr>
                <td>32789456</td>
                <td>27-32789456-1</td>
                <td>Ana</td>
                <td>Ramírez</td>
                <td>F</td>
                <td>Argentina</td>
                <td>1992-11-05</td>
                <td>Los Álamos 456</td>
                <td>Rosario</td>
                <td>Santa Fe</td>
                <td>ana.ramirez@example.com</td>
                <td>+54 341 6789-1234</td>
                <td><button class="btn btn-warning btn-sm">Editar</button></td>
                <td><button class="btn btn-danger btn-sm">Eliminar</button></td>
            </tr>
        </tbody>
    </table>
</div>
<script type="text/javascript">
  let table = new DataTable('#clientesTable', {
   language : {
    url : '//cdn.datatables.net/plug-ins/2.1.8/i18n/es-AR.json'
   }
  });
</script>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
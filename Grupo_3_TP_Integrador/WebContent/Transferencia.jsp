<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferencia</title>
</head>
<body>
<h1>Transferencia</h1>

<form>
   
    <label for="cuentaOrigen">Cuenta Origen:</label>
    <select id="cuentaOrigen" name="cuentaOrigen" required>
    	<option value="" disabled selected>Seleccione</option>
        <option value="cuenta1">Cuenta 1 - 12345678</option>
        <option value="cuenta2">Cuenta 2 - 87654321</option>
        <option value="cuenta3">Cuenta 3 - 11223344</option>
    </select>
    <br><br>

    <label for="cbuDestino">Cuenta Destino (CBU):</label>
    <input type="number" id="cbuDestino" name="cbuDestino" placeholder="Ingrese CBU de destino" required>
    <br><br>

    <label for="monto">Monto a Transferir:</label>
    <input type="number" id="monto" name="monto" placeholder="Ingrese monto" step="0.01" required>
    <br><br>

    <button type="submit" style="background-color: green; color: white;">Transferir</button>
    <button type="reset"style="background-color: red; color: white;">Borrar campos</button>
    <button type="submit" style="background-color: red; color: white;">Volver</button>
</form>

</body>
</html>
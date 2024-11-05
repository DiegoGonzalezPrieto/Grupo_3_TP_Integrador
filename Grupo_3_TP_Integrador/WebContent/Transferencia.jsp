<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencia</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-5">
		<h1 class="text-center mb-4">Transferencia</h1>
		<div class="row justify-content-center">
			<div class="col-md-4">
				<form class="p-4 border rounded bg-light">
					<div class="mb-3">
						<label for="cuentaOrigen" class="form-label">Cuenta
							Origen:</label> <select id="cuentaOrigen" name="cuentaOrigen"
							class="form-select" required>
							<option value="" disabled selected>Seleccione</option>
							<option value="cuenta1">Cuenta 1 - 12345678</option>
							<option value="cuenta2">Cuenta 2 - 87654321</option>
							<option value="cuenta3">Cuenta 3 - 11223344</option>
						</select>
					</div>

					<div class="mb-3">
						<label for="cbuDestino" class="form-label">Cuenta Destino
							(CBU):</label> <input type="number" id="cbuDestino" name="cbuDestino"
							class="form-control" placeholder="Ingrese CBU de destino"
							required>
					</div>

					<div class="mb-3">
						<label for="monto" class="form-label">Monto a Transferir:</label>
						<input type="number" id="monto" name="monto" class="form-control"
							placeholder="Ingrese monto" step="0.01" required>
					</div>

					<div class="d-flex justify-content-between">
						<button type="submit" class="btn btn-success"
							onclick="return confirm('¿Confirma la transferencia?')">Transferir</button>
						<button type="reset" class="btn btn-warning">Borrar
							campos</button>
						<button type="button" onclick="window.history.back()"
							class="btn btn-danger">Volver</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitud de Préstamos</title>

<!-- BOOTSTRAP -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>


<script>
	///LA FUNCION SIRVE PARA CALCULAR EL DIV DE LOS MONTOS TOTALES Y CUOTAS DESDE EL FRONT
	function calcularMontos() {
		const importe = parseFloat(document.getElementById("ImporteaSolicitar").value) || 0;
		const plazo = parseFloat(document.getElementById("PlazoPago").value) || 0;
		const cantidadCuotas = parseFloat(document
				.getElementById("CantidadDeCuotas").value) || 0;

		let interes = 0;

		//CALCULA EL INTERES
		if (plazo === 1) {
			if (cantidadCuotas === 6) {
				interes = 0.15;
			} else if (cantidadCuotas === 12) {
				interes = 0.30;
			}
		} else if (plazo === 2) {
			if (cantidadCuotas === 6) {
				interes = 0.50;
			} else if (cantidadCuotas === 12) {
				interes = 0.70;
			}
		}

		//CALCULA SEGUN EL INTERES EL MONTO FINAL Y LA CUOTA MENSUAL
		const montoTotal = importe * (1 + interes);
		const cuotaMensual = cantidadCuotas > 0 ? montoTotal / cantidadCuotas
				: 0;

		//MUESTRA LOS RESULTADOS SOBRE EL FRONT
		document.getElementById('montoTotal').textContent = 'Monto Total a Retornar: $'
				+ montoTotal.toFixed(2);
		document.getElementById('montoPorCuota').textContent = 'Monto por Cuota: $'
				+ cuotaMensual.toFixed(2);
	}
</script>
</head>
<body class="bg-light">
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-5">
		<h1 class="text-center">Solicitud de Préstamos</h1>

		<div class="text-center mb-4">
			<h2 class="bg-success text-white p-3 rounded">Cliente: Maria
				Laura</h2>
		</div>


		<div class="row justify-content-center">
			<div class="col-md-6">
				<form action="AgregarSeguroServlet" method="post"
					class="bg-white p-4 rounded shadow">
					<fieldset>
						<legend>Datos de la Solicitud</legend>

						<div class="form-group">
							<label for="Cuenta">Cuenta</label> <select id="Cuenta"
								name="cuenta" class="form-control">
								<option value="" disabled selected>Seleccione una
									cuenta</option>
								<option value="Cuenta 1">Cuenta 1</option>
								<option value="Cuenta 2">Cuenta 2</option>
								<option value="Cuenta 3">Cuenta 3</option>
								<!-- ACA DEBERIA IR EL FOR PARA LAS CUENTAS QUE TENGA EL CLIENTE -->
							</select>
						</div>

						<div class="form-group">
							<label for="ImporteaSolicitar">Importe a Solicitar</label> <input
								id="ImporteaSolicitar" type="number" class="form-control"
								placeholder="Ingrese el importe a solicitar (máx. $1.000.000)"
								min="0" max="1000000" oninput="calcularMontos()" required
								name="txtDescripcion">
						</div>

						<div class="form-group">
							<label for="PlazoPago">Elige Plazo de Pago en Años</label> <select
								id="PlazoPago" name="PlazoPago" class="form-control" required
								onchange="calcularMontos()">
								<option value="" disabled selected>Seleccione una
									opción</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<!--ESTAS OPCIONES SE PUEDEN CAMBIAR -->
							</select>
						</div>

						<div class="form-group">
							<label for="CantidadDeCuotas">Cantidad de cuotas</label> <select
								id="CantidadDeCuotas" name="CantidadDeCuotas"
								class="form-control" required onchange="calcularMontos()">
								<option value="" disabled selected>Seleccione una
									opción</option>
								<option value="6">6</option>
								<option value="12">12</option>
								<!--ACA VAN A IR LAS CUOTAS QUE DAMOS PARA PAGAR -->
							</select>
						</div>

						<div class="form-group">
							<p id="montoTotal" class="font-weight-bold">Monto Total a
								Retornar: $0.00</p>
							<p id="montoPorCuota" class="font-weight-bold">Monto Por
								Cuota: $0.00</p>
						</div>
					</fieldset>

					<div class="form-group text-center mt-4">
						<input id="btnSolicitar" type="submit" class="btn btn-success"
							value="Solicitar" required name="btnSolicitar"> <input
							id="btnCancelar" type="button" class="btn btn-danger"
							value="Cancelar">
						<a href="Prestamo.jsp" class="btn btn-secondary">Volver</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
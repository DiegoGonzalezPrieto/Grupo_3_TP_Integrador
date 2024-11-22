<%@page import="negocioImpl.CuentaNegocioImpl"%>
<%@page import="negocio.CuentaNegocio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
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
	<%
		/* Configuración inicial */

		ArrayList<Cuenta> cuentasPropias = (ArrayList<Cuenta>) request.getAttribute("cuentasPropias");
		int idUsuarioActual = ((Usuario) session.getAttribute("usuario")).getId();
		if (cuentasPropias == null) {
			cuentasPropias = new ArrayList<Cuenta>();

			response.sendRedirect("TransferenciaServlet");
		}

		int idUsuarioCuentas = (int) request.getAttribute("idUsuarioCuentas");
		if (idUsuarioActual != idUsuarioCuentas) {
			// no coincide el cliente logueado con el id recibido en servlet
			response.sendRedirect("HomeCliente.jsp");
		}

		// Mensajes
		String mensaje = "";
		String claseMensaje = "";
		if (request.getAttribute("mensaje") != null) {
			mensaje = (String) request.getAttribute("mensaje");
		}
		if (request.getAttribute("claseMensaje") != null) {
			claseMensaje = (String) request.getAttribute("claseMensaje");
		}
	%>
	<%@ include file="BarraMenu.jsp"%>
	<div class="container mt-5">
		<%
			if (!mensaje.isEmpty()) {
		%>
		<div
			class="alert alert-<%=claseMensaje.isEmpty() ? "info" : claseMensaje%> alert-dismissible fade show"
			role="alert"><%=mensaje%>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
			}
		%>
		<h1 class="display-5" style="margin:50px; text-align:center;">Transferencia</h1><hr>
		<div class="row justify-content-center">
			<div class="col-md-4">
				<form action="" method="POST" class="p-4 border rounded bg-light">
					<div class="mb-3">
						<label for="cuentaOrigen" class="form-label">Cuenta
							Origen:</label> <select id="cuentaOrigen" name="cuentaOrigen"
							class="form-select" required onchange="filtrarCuentasPropias()">

							<option value="" disabled selected>Seleccione</option>
							<%
								for (Cuenta cuenta : cuentasPropias) {
							%>

							<option value="<%=cuenta.getId()%>" data-saldo="<%=cuenta.getSaldo()%>"><%=cuenta.getNumeroCuenta() + " - " + cuenta.getTipoCuenta().getNombre() + " - $"
						+ cuenta.getSaldo()%></option>
							<%
								}
							%>
						</select>
					</div>

					<h3 class="text-center mb-3">Destino</h3>

					<div class="form-check form-switch ml-4 mb-3">
						<input class="form-check-input" type="checkbox"
							id="esCuentaPropia" name="esCuentaPropia"
							onchange="alternarCuentaPropia(this.checked)"> <label
							class="form-check-label" for="esCuentaPropia">A cuenta
							propia</label>
					</div>
					<div class="mb-3" id="bloqueCbuDestino">
						<label for="cbuDestino" class="form-label">Cuenta Destino
							(CBU):</label> <input id="cbuDestino" name="cbuDestino"
							class="form-control" placeholder="Ingrese CBU de destino"
							pattern="\d{22}" title="Código de 22 dígitos" required>
					</div>

					<div class="mb-3" id="bloqueCuentaPropia">
						<label for="cuentaPropia" class="form-label">Cuenta
							Propia:</label> <select id="cuentaPropia" name="cuentaPropia"
							class="form-select" required>
							<option value="" disabled selected>Seleccione</option>
							<%
								for (Cuenta cuenta : cuentasPropias) {
							%>

							<option value="<%=cuenta.getId()%>"><%=cuenta.getNumeroCuenta() + " - " + cuenta.getTipoCuenta().getNombre() + " - $"
						+ cuenta.getSaldo()%></option>
							<%
								}
							%>
						</select>
					</div>

					<div class="mb-3">
						<label for="monto" class="form-label">Monto a Transferir:</label>
						<input type="number" id="monto" name="monto" class="form-control"
							placeholder="Ingrese monto" step="0.01" required min=0.01
							oninput="validarMontoEnTiempoReal()">
					</div>

					<div class="d-flex justify-content-between">
						<button type="submit" class="btn btn-success"
							onclick="return confirm('¿Confirma la transferencia?')">Transferir</button>
						<button type="reset" class="btn btn-warning">Borrar
							campos</button>
						<a href="HomeCliente.jsp" class="btn btn-danger">Volver</a>
					</div>
				</form>
			</div>
		</div>
		<div style="text-align:center; margin:10px">
			<a href="HomeClienteServlet">Volver al Home</a>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>

	<script type="text/javascript">
		
		
		
		/**
		 * Filtra las cuentas propias destino en base a la cuenta de origen seleccionada.
		 */
		function filtrarCuentasPropias() {
			for ( e of cuentaPropia.options) {
				if (!e.value)
					continue;
				if (e.value == cuentaOrigen.value)
					e.disabled = true;
				else
					e.disabled = false;
				}
		}
		
		/**
		 * Recibe el valor de checked del toggle de cuenta propia.
		 * Muestra u oculta los controles de selección de destino (CBU o cuenta propia).
		 */
		function alternarCuentaPropia(esACuentaPropia) {

			bloqueCbuDestino.hidden = esACuentaPropia;
			cbuDestino.disabled = esACuentaPropia;

			bloqueCuentaPropia.hidden = !esACuentaPropia;
			cuentaPropia.disabled = !esACuentaPropia;
			
			if (esACuentaPropia)
				filtrarCuentasPropias();
		}
		
		function validarMontoEnTiempoReal() {
            const cuentaOrigen = document.getElementById('cuentaOrigen');
            const montoInput = document.getElementById('monto');
            
            if (cuentaOrigen.selectedIndex === 0) return;
            
            const saldo = parseFloat(cuentaOrigen.options[cuentaOrigen.selectedIndex].getAttribute('data-saldo'));
            
            let monto = parseFloat(montoInput.value);

            
            if (monto > saldo) {
                montoInput.value = saldo.toFixed(2); 
            }
        }
		alternarCuentaPropia(false);
	</script>
</body>
</html>


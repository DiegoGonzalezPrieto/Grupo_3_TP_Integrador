<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>pago de Préstamos</title>
</head>
<body>
<h1>Pago de Préstamos</h1>

	<!-- TRAEMOS DESDE EL SERVLET LOS PRESTAMOS Y LA CUOTAS DEL METODO DOGET -->
	<!-- 
		Prestamo prestamo = (Prestamo) request.getAttribute("prestamo") 
    	List<Cuota> cuotas = (List<Cuota>) request.getAttribute("cuotas")	
	 -->

<h2>Detalles del Prestamo</h2>
	<form action="ProcesarPagoServlet" method="post">
		<input type="hidden" name="idPrestamo" value="metodo para traer el id de Prestamo">
			<fieldset>
				<legend>Cuota a Pagar</legend>
				<table border="1">
					<thead>
						<tr>
							<th>Numero de Cuota</th>
							<th>Fecha Vencimiento</th>
							<th>Monto</th>
							<th>Pagar</th>
						</tr>
					</thead>
					<tbody>
						<!-- ACA TRAEMOS CON UN FOR LA INFO DESDE EL DOGET PARA PONER EN LA TABLA
							EJ: 
							for(Cuota cuota : cuotas){
								String nroCuota = cuota.getNroCuota();
								String fechaVenc = cuota.getFechaVencimiento();
								double importeMensual = prestamo.getImporteMensual();						
						 -->
						 
						 <tr>
						 <!--  
						 	<td>"mostrar variable nroCuota" </td>
						 	<td>"mostrar variable fechaVenc" </td>
						 	<td>"mostrar variable importeMensual" </td>
						 -->
						 	<td> 
						 		<input type="checkbox" name="cuotas" value="">
						 	</td>						 	
						 </tr>
						 <!-- } CERRAMOS EL FOR -->	 
											  
					</tbody>							
				</table>		
			</fieldset>
			
			<input type="submit" value="Pagar Seleccionadas">
			<input type="submit" value="Volver">
			
	
	</form>
</body>
</html>
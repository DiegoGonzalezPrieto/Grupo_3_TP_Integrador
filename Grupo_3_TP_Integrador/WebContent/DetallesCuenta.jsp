<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalles de Cuenta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-white">
        	<div>
        		<h4 class="card-header bg-primary text-white">Movimientos de Cuenta</h4>
        	</div>
            
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label text-muted">Tipo de Cuenta</label>
                    <p class="fw-bold mb-2">Caja de Ahorro</p>
                </div>
                <div class="col-md-4">
                    <label class="form-label text-muted">Número de Cuenta</label>
                    <p class="fw-bold mb-2">1234567890</p>
                </div>
                <div class="col-md-4">
                    <label class="form-label text-muted">CBU</label>
                    <p class="fw-bold mb-2">0123456789012345678901</p>
                </div>
            </div>
        </div>
        
        <div class="card-body">
            <div class="row g-3 mb-4">
                <div class="col-md-4">
                    <label class="form-label">Buscar</label>
                    <input type="text" class="form-control" placeholder="Buscar ...">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Filtrar por importe</label>
                    <select class="form-select" id="importeFilter">
                        <option value="all">Todos</option>
                        <option value="mayor">Menor a </option>
                        <option value="100to1000">Entre</option>
                        <option value="more1000">Mayor a </option>
                    </select>
                </div>
                <div class="col-md-5" id="rango">
				    <label class="form-label">Valor</label>
				    <div class="input-group">
				        <input type="number" class="form-control" placeholder="Mínimo">
				        <span class="input-group-text">-</span>
				        <input type="number" class="form-control" placeholder="Máximo">
				        <button type="button" class="btn btn-primary">
				            <i class="bi bi-search"></i> Filtrar
				        </button>
				    </div>
				</div>
            </div>

            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-light">
                        <tr>
                            <th class="text-center">Fecha</th>
                            <th class="text-center">Detalle</th>
                            <th class="text-end">Importe</th>
                            <th class="text-center">Tipo de Movimiento</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center">01/03/2024</td>
                            <td class="text-center" style="padding-left: 50px;">Depósito inicial</td>
                            <td class="text-end text-success">$10,000.00</td>
                            <td class="text-center">Deposito</td>
                        </tr>
                        <tr>
                            <td class="text-center">02/03/2024</td>
                            <td class="text-center" style="padding-left: 50px;">Transferencia saliente</td>
                            <td class="text-end text-danger">-$1,500.00</td>
                            <td class="text-center">Transferencia</td>
                        </tr>
                        <tr>
                            <td class="text-center">03/03/2024</td>
                            <td class="text-center" style="padding-left: 50px;">Préstamo aprobado</td>
                            <td class="text-end text-success">$5,000.00</td>
                            <td class="text-center">Prestamo</td>
                        </tr>
                        <tr>
                            <td class="text-center">04/03/2024</td>
                            <td class="text-center" style="padding-left: 50px;">Pago cuota préstamo</td>
                            <td class="text-end text-danger">-$800.00</td>
                            <td class="text-center">Pago Prestamo</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos Personales</title>
</head>
<body>
    <center>
        <table width="800" bgcolor="#00A7D1" cellpadding="10" cellspacing="0">
            <tr>
                <td width="50%" align="right">
                    <label>Nombre: </label>
                    <input type="text" value="Juan Pablo" readonly size="20">
                </td>
                <td width="50%" align="right">
                    <label>Apellido: </label>
                    <input type="text" value="Rajoy" readonly size="20">
                </td>
            </tr>
            <tr>
                <td width="50%" align="right">
                    <label>Nro. Documento o CUIL: </label>
                    <select>
                        <option value="DNI" selected>DNI</option>
                        <option value="CUIL">CUIL</option>
                    </select>
                    <input type="text" value="35035199" readonly size="20">
                </td>
                <td width="50%" align="right">
                    <label>Fecha Nac.: </label>
                    <input type="text" value="01/03/2001" readonly size="20">
                </td>
            </tr>
            <tr>
                <td width="50%" align="right">
                    <label>Sexo: </label>
                    <input type="text" value="Masculino" readonly size="20">
                </td>
                <td width="50%" align="right">
                    <label>Nacionalidad: </label>
                    <input type="text" value="Argentina" readonly size="20">
                </td>
            </tr>
            <tr>
                <td width="50%" align="right">
                    <label>Dirección: </label>
                    <input type="text" value="Calle 6" readonly size="20">
                </td>
                <td width="50%" align="right">
                    <label>Localidad: </label>
                    <input type="text" value="San Antonio" readonly size="20">
                </td>
            </tr>
            <tr>
                <td width="50%" align="right">
                    <label>Provincia: </label>
                    <input type="text" value="Buenos Aires" readonly size="20">
                </td>
                <td width="50%" align="right">
                    <label>Email: </label>
                    <input type="text" value="user@test.com" readonly size="20">
                </td>
            </tr>
        </table>
        <br><br><br>
        <form action="DetallesCuenta.jsp" method="GET">
            <button type="submit">Ver Detalle de Cuenta</button>
        </form>
    </center>
</body>
</html>
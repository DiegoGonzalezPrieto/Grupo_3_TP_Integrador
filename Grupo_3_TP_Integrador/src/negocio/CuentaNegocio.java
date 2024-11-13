package negocio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dominio.Cuenta;

public interface CuentaNegocio {
    boolean crearCuenta(Cuenta cuenta);
    boolean actualizarCuenta(Cuenta cuenta);
    boolean eliminarCuenta(int id);
    
    Cuenta obtenerCuentaPorId(int id);
    List<Cuenta> listarTodas();
    List<Cuenta> listarPorCliente(int idCliente);
    
    boolean puedeCrearCuenta(int idCliente);
    boolean validarCBU(String cbu);
    boolean validarNumeroCuenta(Long numeroCuenta);
    
    /*
     * Obtiene la cantidad de cuentas creadas en el periodo especificado
     * */
    int obtenerReporteCantidadDeCuentas(Date fechaInicio, Date fechaFin);
    
    /*
     * Obtiene la suma de saldos de cuentas creadas en el periodo especificado
     * */
    BigDecimal obtenerReporteSumaDeSaldos(Date fechaInicio, Date fechaFin);
    
    /*
     * Obtiene el saldo promedio de las cuentas creadas en el periodo especificado
     * */
    BigDecimal obtenerReporteSaldoPromedio(Date fechaInicio, Date fechaFin);
}

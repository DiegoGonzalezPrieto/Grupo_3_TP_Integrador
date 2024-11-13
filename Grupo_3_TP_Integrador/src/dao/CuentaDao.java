package dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dominio.Cuenta;

public interface CuentaDao {
	boolean insert(Cuenta cuenta);
    boolean update(Cuenta cuenta);
    boolean delete(int id);
    
    Cuenta encontrarPorId(int id);
    List<Cuenta> obtenerTodos();
    List<Cuenta> obtenerTodoPorCliente(int idCliente);
    int encontrarCuentaActivaPorCliente(int idCliente);
    
    boolean existeCBU(String cbu);
    boolean existeNumeroCuenta(Long numeroCuenta);
    
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

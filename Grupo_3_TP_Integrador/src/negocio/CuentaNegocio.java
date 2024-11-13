package negocio;

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
    
    Long obtenerUltimoNumeroCuenta();
    String obtenerUltimoCBU();
}

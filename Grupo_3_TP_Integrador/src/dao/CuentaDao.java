package dao;

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
    
    Long obtenerUltimoNumeroCuenta();
    String obtenerUltimoCBU();

}

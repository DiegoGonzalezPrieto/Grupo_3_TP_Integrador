package dominio;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Cuenta {
	private int id;
    private Cliente cliente;
    private TipoCuenta tipoCuenta;
    private Date fechaCreacion;
    private Long numeroCuenta;
    private String cbu;
    private BigDecimal saldo;
    private boolean activa;
	    
    public Cuenta() {
        this.fechaCreacion = Date.valueOf(LocalDate.now());
        this.saldo = new BigDecimal("10000.00");
        this.activa = true;
    }
    
    public Cuenta(int id, Cliente cliente, TipoCuenta tipoCuenta, Long numeroCuenta, String cbu) {
        this();  
        this.id = id;
        this.cliente = cliente;
        this.tipoCuenta = tipoCuenta;
        this.numeroCuenta = numeroCuenta;
        this.cbu = cbu;
    }


	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", cliente=" + cliente + ", tipoCuenta=" + tipoCuenta + ", fechaCreacion="
				+ fechaCreacion + ", numeroCuenta=" + numeroCuenta + ", cbu=" + cbu + ", saldo=" + saldo + ", activa="
				+ activa + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean Activa() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	    
	    
}

package dominio;

import java.math.BigDecimal;
import java.util.Date;

public class Movimiento {
	private int id;
	
	private Cuenta cuenta;
	private TipoMovimiento tipo;
	private Date fecha;
	private String concepto;
	private BigDecimal monto;
	
	
	
	public Movimiento(int id, Cuenta cuenta, TipoMovimiento tipo, Date fecha, String concepto, BigDecimal monto) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.tipo = tipo;
		this.fecha = fecha;
		this.concepto = concepto;
		this.monto = monto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoMovimiento getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", cuenta=" + cuenta + ", tipo=" + tipo + ", fecha=" + fecha + ", concepto="
				+ concepto + ", monto=" + monto + "]";
	}
	
	
	
	
	
}

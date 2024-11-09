package dominio;

import java.math.BigDecimal;
import java.util.Date;

public class Movimiento {
	private int id;
	
	//TODO IMPORTANTE! Para completar esta clase se necesita merge de Cuenta ya que tiene composición.
	
	//private Cuenta cuenta;
	
	private TipoMovimiento tipo;
	private Date fecha;
	private String concepto;
	private BigDecimal monto;
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
	
	
	
}

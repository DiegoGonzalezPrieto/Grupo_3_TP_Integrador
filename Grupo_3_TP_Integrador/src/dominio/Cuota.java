package dominio;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class Cuota {
	
	private int Id;
	private Prestamo prestamo;
	private int numeroCuota;
	private BigDecimal montoPagado;
	private Date fechaPago;
	private boolean estadoPago;
	
	
	
	//CONSTRUCTORES
	public Cuota() {
		
	}	
	
	public Cuota(Prestamo prestamo,int numerocuota, BigDecimal montopagado, Date fechapago, boolean estadopago) {
		
		super();
		this.prestamo = prestamo;
		this.numeroCuota = numerocuota;
		this.montoPagado = montopagado;
		this.fechaPago = fechapago;
		this.estadoPago = estadopago;
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public BigDecimal getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(BigDecimal montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public boolean getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
	}

	@Override
	public String toString() {
		return "Cuota [Id=" + Id + ", prestamo=" + prestamo + ", numeroCuota=" + numeroCuota + ", montoPagado="
				+ montoPagado + ", fechaPago=" + fechaPago + ", estadoPago=" + estadoPago + "]";
	}
	
	

}

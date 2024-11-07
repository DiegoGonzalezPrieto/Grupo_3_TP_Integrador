package dominio;

import java.math.BigDecimal;
import java.sql.Date;


public class Prestamo {
	
	private int id;
	private Cliente cliente;
	private Cuenta cuenta;
	private Date fechaAltaPrestamo;
	private BigDecimal importePrestamo;
	private int mesesPlazo;
	private BigDecimal importeMensual;
	private int cuotas;
	private Date fechaValidacion;
	private EstadoPrestamo estadoValidacion;
	
	//CONSTRUCTOR POR REFERENCIA
	
	public Prestamo (int id, Cliente cliente, Cuenta cuenta, Date fechaAltaPrestamo, BigDecimal importePrestamo,
	int mesesPlazo,	BigDecimal importeMensual, int cuotas, Date fechaValidacion, EstadoPrestamo estadoValidacion) 
	{
		this.id = id;
		this.cliente= cliente;
		this.cuenta = cuenta;
		this.fechaAltaPrestamo = fechaAltaPrestamo;
		this.importePrestamo = importePrestamo;
		this.cuotas = cuotas;
		this.fechaValidacion = fechaValidacion;
		this.estadoValidacion = estadoValidacion;
		
	}
	
	//CONSTRUCTOR VACIO
	
	public Prestamo () {}
	
	//GETTER AND SETTER

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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaAltaPrestamo() {
		return fechaAltaPrestamo;
	}

	public void setFechaAltaPrestamo(Date fechaAltaPrestamo) {
		this.fechaAltaPrestamo = fechaAltaPrestamo;
	}

	public BigDecimal getImportePrestamo() {
		return importePrestamo;
	}

	public void setImportePrestamo(BigDecimal importePrestamo) {
		this.importePrestamo = importePrestamo;
	}

	public int getMesesPlazo() {
		return mesesPlazo;
	}

	public void setMesesPlazo(int mesesPlazo) {
		this.mesesPlazo = mesesPlazo;
	}

	public BigDecimal getImporteMensual() {
		return importeMensual;
	}

	public void setImporteMensual(BigDecimal importeMensual) {
		this.importeMensual = importeMensual;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	
	//TOSTRING
	
	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", cliente=" + cliente + ", fechaAltaPrestamo=" + fechaAltaPrestamo
				+ ", importePrestamo=" + importePrestamo + ", mesesPlazo=" + mesesPlazo + ", importeMensual="
				+ importeMensual + ", cuotas=" + cuotas + ", fechaValidacion=" + fechaValidacion + ", estadoValidacion="
				+ estadoValidacion + "]";
	};
	
	
	

}

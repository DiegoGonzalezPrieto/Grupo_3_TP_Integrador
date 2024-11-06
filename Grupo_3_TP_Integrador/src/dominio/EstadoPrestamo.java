package dominio;

public class EstadoPrestamo {
	private int id;
	private String estado;

	public EstadoPrestamo(int id, String estado) {
		super();
		this.id = id;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return estado;
	}

	public void setNombre(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return estado;
	}
}

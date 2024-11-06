package dominio;

public class Localidad {
	private int id;
	private String nombre;
	private Provincia provincia;

	public Localidad(int id, String nombre, Provincia provincia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.provincia = provincia;
	}

	public int getId() {
		return id;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre + " - " + provincia;
	}

}

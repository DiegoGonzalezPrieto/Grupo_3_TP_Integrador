package dominio;

public class Cliente {
	public String nombre;
	public String apellido;
	//En la tabla de la BD el genero es char, me parecio mejor en la clase poner String y cuando trae la info de la BD
	//comprobar que char trae y completarlo a String para mostrar... Les parece? si no les va cambio a char
	public String genero;
	public String correoElectronico;
	public Localidad localidad;
	public Provincia provincia;
	public TipoUsuario tipoUsuario;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

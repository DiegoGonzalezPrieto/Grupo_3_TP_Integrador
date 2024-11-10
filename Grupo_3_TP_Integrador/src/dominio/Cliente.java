package dominio;

import java.sql.Date;

public class Cliente extends Usuario {
	private int idCliente;
	private String dni;
	private String cuil;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private String telefono;
	//private char genero; //alternativa.... segun como se quiera implementar... 
	private String genero;
	private Nacionalidad nacionalidad;
	private Date fechaNacimiento;
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	
	
	public Cliente() {
		
	}
	
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono
				+ ", genero=" + genero + ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento
				+ ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia
				+ "]";
	}


	/*Un cliente tiene un DNI, CUIL, nombre, apellido, sexo, nacionalidad,
	fecha de nacimiento, dirección, localidad, provincia, correo
	electrónico, teléfono, un usuario y contraseña para acceder a la
	página
	*/
	public Cliente(int id, String nombreUsuario, String pass, TipoUsuario tipoUsuario, boolean estadoUsuario) {
		super(id, nombreUsuario, pass, tipoUsuario, estadoUsuario);
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	

}

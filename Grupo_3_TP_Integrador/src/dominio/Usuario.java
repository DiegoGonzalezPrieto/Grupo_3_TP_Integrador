package dominio;

public class Usuario {
	private int id;
    private String nombreUsuario;
    private String pass;
    private TipoUsuario tipoUsuario;
    private boolean estadoUsuario;

    public Usuario(int id, String nombreUsuario, String pass, TipoUsuario tipoUsuario, boolean estadoUsuario) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
        this.estadoUsuario = estadoUsuario;

    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public boolean activo() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	
	public boolean esAdmin() {
		return this.tipoUsuario.getId() == 2;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", pass=" + pass + ", idTipoUsuario="
				+ tipoUsuario + ", estadoUsuario=" + estadoUsuario + "]";
	}
	
	public Usuario() {
		
	}
	
}

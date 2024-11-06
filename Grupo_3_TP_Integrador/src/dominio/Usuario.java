package dominio;

public class Usuario {
	private int id;
    private String nombreUsuario;
    private String pass;
    private int idTipoUsuario;
    private boolean estadoUsuario;

    public Usuario(int id, String nombreUsuario, String pass, int idTipoUsuario, boolean estadoUsuario) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.pass = pass;
        this.idTipoUsuario = idTipoUsuario;
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

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public boolean isEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", pass=" + pass + ", idTipoUsuario="
				+ idTipoUsuario + ", estadoUsuario=" + estadoUsuario + "]";
	}
	
	
}

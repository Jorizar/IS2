package aeropuertois2.seguridad.modelo;

public class Usuario {

	private int idUsuario;
	private String nombre;
	private String tipo;
	private String credencialAcceso;

	public Usuario(int idUsuario, String nombre, String tipo, String credencialAcceso) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.tipo = tipo;
		this.credencialAcceso = credencialAcceso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoUsuario() {
		return tipo;
	}

	public void setTipoUsuario(String tipo) {
		this.tipo = tipo;
	}

	public String getCredencialAcceso() {
		return credencialAcceso;
	}

	public void setCredencialAcceso(String credencialAcceso) {
		this.credencialAcceso = credencialAcceso;
	}
}
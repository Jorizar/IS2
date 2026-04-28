package aeropuertois2.seguridad.modelo;

import java.util.Date;

public class PermisoAcceso {

	private int idPermisoAcceso;
	private int idUsuario;
	private int idZonaRestringida;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;

	public PermisoAcceso(int idUsuario, int idZonaRestringida, Date fechaInicio, Date fechaFin) {
		this.idUsuario = idUsuario;
		this.idZonaRestringida = idZonaRestringida;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = "Activo";
	}

	public int getIdPermisoAcceso() {
		return idPermisoAcceso;
	}

	public void setIdPermisoAcceso(int idPermisoAcceso) {
		this.idPermisoAcceso = idPermisoAcceso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getidZonaRestringida() {
		return idZonaRestringida;
	}

	public void setIdZonaRestringida(int idZonaRestringida) {
		this.idZonaRestringida = idZonaRestringida;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
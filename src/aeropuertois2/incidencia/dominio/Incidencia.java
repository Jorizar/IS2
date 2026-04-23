package aeropuertois2.incidencia.dominio;

import java.time.LocalDateTime; //para usar LocalDateTime (momento actual)

public class Incidencia {

	private String idIncidencia;
	private tipoIncidencia tipo;
	private estadoIncidencia estado;
	private LocalDateTime fecha;
	private String descrip;
	private String causa;

	public Incidencia(String id, tipoIncidencia tipo, String descrip, String causa) {
		this.idIncidencia = id;
		this.tipo = tipo;
		this.estado = estadoIncidencia.ABIERTA; // default
		this.fecha = LocalDateTime.now(); // default
		this.descrip = descrip;
		this.causa = causa;
	}

	public String getIdIncidencia() {
		return idIncidencia;
	}

	public tipoIncidencia getTipo() {
		return tipo;
	}

	public estadoIncidencia getEstado() {
		return estado;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public String getDescrip() {
		return descrip;
	}

	public String getCausa() {
		return causa;
	}
}

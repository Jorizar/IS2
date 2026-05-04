package aeropuertois2.paneles.modelo;

import java.time.*;

public class Aviso {

	private String id;
	private LocalDateTime fecha;
	private String mensaje;

	public Aviso(String id, LocalDateTime fecha, String mensaje) {
		this.id = id;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}

	public String getIdAviso() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

}

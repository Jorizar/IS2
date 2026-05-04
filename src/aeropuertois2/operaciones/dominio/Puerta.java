package aeropuertois2.operaciones.dominio;

public class Puerta {
	private int idPuerta;
	private String numeroGate;
	private int idTerminal;
	private ZonaTerminal zona;
	private boolean bloqueada;

	public Puerta(int idPuerta, String numeroGate, int idTerminal, ZonaTerminal zona, boolean bloqueada) {
		this.idPuerta = idPuerta;
		this.numeroGate = numeroGate;
		this.idTerminal = idTerminal;
		this.zona = zona;
		this.bloqueada = bloqueada;
	}

	public int getIdPuerta() {
		return idPuerta;
	}

	public String getNumeroGate() {
		return numeroGate;
	}

	public int getIdTerminal() {
		return idTerminal;
	}

	public ZonaTerminal getZona() {
		return zona;
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	@Override
	public String toString() {
		return String.format("Puerta %s | Zona: %s | %s", numeroGate, zona.getNombre(),
				bloqueada ? "BLOQUEADA" : "Disponible");
	}
}
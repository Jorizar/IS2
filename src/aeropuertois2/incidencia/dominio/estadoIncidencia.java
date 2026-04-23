package aeropuertois2.incidencia.dominio;

public enum estadoIncidencia {

	ABIERTA, ASIGNADA, EN_PROGRESO, CERRADA, CANCELADA;

	public static estadoIncidencia fromDatabase(String value) {
		String formatted = value.replace(" ", "_").toUpperCase(); // adaptar formato del enum a texto normal
		return estadoIncidencia.valueOf(formatted);
	}

	public String toDatabaseValue() {
		return switch (this) {
		case EN_PROGRESO -> "En progreso"; // caso concreto por el espacio
		default -> name().substring(0, 1) + name().substring(1).toLowerCase();
		};
	}
}

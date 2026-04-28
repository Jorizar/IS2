package aeropuertois2.personal.dominio;

public enum Turno {
	MANANA, TARDE, NOCHE;

	public static Turno fromDatabase(String value) {
		return switch (value.toLowerCase()) {
		case "mañana", "manana" -> MANANA;
		case "tarde" -> TARDE;
		case "noche" -> NOCHE;
		default -> throw new IllegalArgumentException("Turno no válido en BD: " + value);
		};
	}

	public String toDatabaseValue() {
		return switch (this) {
		case MANANA -> "mañana";
		case TARDE -> "tarde";
		case NOCHE -> "noche";
		};
	}
}

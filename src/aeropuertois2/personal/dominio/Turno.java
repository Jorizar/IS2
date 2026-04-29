package aeropuertois2.personal.dominio;

import java.util.Locale;

public enum Turno {
	MANANA, TARDE, NOCHE;

	public static Turno fromDatabase(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Turno no válido en BD: valor vacío");
		}

		return switch (value.trim().toLowerCase(Locale.ROOT)) {
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
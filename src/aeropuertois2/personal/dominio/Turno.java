package aeropuertois2.personal.dominio;

import java.util.Locale;

public enum Turno {
	MANANA, TARDE, NOCHE;

	public static Turno fromDatabase(String value) {
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException("Turno no válido en BD: valor vacío");
		}

		String normalizado = value.trim().toLowerCase(Locale.ROOT);

		if (normalizado.equals("mañana") || normalizado.equals("manana")) {
			return MANANA;
		}

		if (normalizado.equals("tarde")) {
			return TARDE;
		}

		if (normalizado.equals("noche")) {
			return NOCHE;
		}

		throw new IllegalArgumentException("Turno no válido en BD: " + value);
	}

	public String toDatabaseValue() {
		if (this == MANANA) {
			return "mañana";
		}

		if (this == TARDE) {
			return "tarde";
		}

		return "noche";
	}
}
package aeropuertois2.personal.dominio;

import java.util.Locale;

public enum RolEmpleado {
	PERSONAL_DE_EQUIPO, ADMINISTRADOR;

	public static RolEmpleado fromDatabase(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Rol no válido en BD: valor vacío");
		}

		return switch (value.trim().toLowerCase(Locale.ROOT)) {
		case "personal de equipo" -> PERSONAL_DE_EQUIPO;
		case "administrador" -> ADMINISTRADOR;
		default -> throw new IllegalArgumentException("Rol no válido en BD: " + value);
		};
	}

	public String toDatabaseValue() {
		return switch (this) {
		case PERSONAL_DE_EQUIPO -> "personal de equipo";
		case ADMINISTRADOR -> "administrador";
		};
	}
}
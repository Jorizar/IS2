package aeropuertois2.personal.dominio;

import java.util.Locale;

public enum RolEmpleado {
	PERSONAL_DE_EQUIPO, ADMINISTRADOR;

	public static RolEmpleado fromDatabase(String value) {
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException("Rol no válido en BD: valor vacío");
		}

		String normalizado = value.trim().toLowerCase(Locale.ROOT);

		if (normalizado.equals("personal de equipo")) {
			return PERSONAL_DE_EQUIPO;
		}

		if (normalizado.equals("administrador")) {
			return ADMINISTRADOR;
		}

		throw new IllegalArgumentException("Rol no válido en BD: " + value);
	}

	public String toDatabaseValue() {
		if (this == PERSONAL_DE_EQUIPO) {
			return "personal de equipo";
		}

		return "administrador";
	}
}
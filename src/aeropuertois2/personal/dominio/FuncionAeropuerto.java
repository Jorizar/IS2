package aeropuertois2.personal.dominio;

import java.util.Locale;

public enum FuncionAeropuerto {
	FINANCIERO, INCIDENCIAS, OPERACIONES, PANELES, PERSONAL, SEGURIDAD, VUELOS;

	public static FuncionAeropuerto fromDatabase(String value) {
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException("Función no válida en BD: valor vacío");
		}

		return FuncionAeropuerto.valueOf(value.trim().toUpperCase(Locale.ROOT));
	}

	public String toDatabaseValue() {
		return name().toLowerCase(Locale.ROOT);
	}
}
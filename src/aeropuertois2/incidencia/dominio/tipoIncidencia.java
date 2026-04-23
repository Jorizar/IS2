package aeropuertois2.incidencia.dominio;

public enum tipoIncidencia {

	GENERAL, PERSONAL;

    // Convierte lo que viene de SQL a formato Java
    public static tipoIncidencia fromDatabase(String value) {
        return tipoIncidencia.valueOf(value.toUpperCase());
    }

    public String toDatabaseValue() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }
}

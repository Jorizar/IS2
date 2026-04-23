package aeropuertois2.personal.dominio;

public enum FuncionAeropuerto {
    FINANCIERO,
    INCIDENCIAS,
    OPERACIONES,
    PANELES,
    PERSONAL,
    SEGURIDAD,
    VUELOS;

    public static FuncionAeropuerto fromDatabase(String value) {
        return FuncionAeropuerto.valueOf(value.toUpperCase());
    }

    public String toDatabaseValue() {
        return name().toLowerCase();
    }
}

package aeropuertois2.personal.dominio;

public enum RolEmpleado {
    PERSONAL_DE_EQUIPO,
    ADMINISTRADOR;

    public static RolEmpleado fromDatabase(String value) {
        return switch (value.toLowerCase()) {
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

package aeropuertois2.operaciones.dominio;

public enum ZonaTerminal {
    NORTE("Norte"),
    SUR("Sur"),
    ESTE("Este"),
    OESTE("Oeste");

    private final String nombre;

    ZonaTerminal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static ZonaTerminal fromString(String zona) {
        for (ZonaTerminal z : values()) {
            if (z.nombre.equalsIgnoreCase(zona)) {
                return z;
            }
        }
        throw new IllegalArgumentException("Zona no válida: " + zona);
    }
}
package aeropuertois2.operaciones.dominio;

public class Terminal {
    private int idTerminal;
    private String nombre;

    public Terminal(int idTerminal, String nombre) {
        this.idTerminal = idTerminal;
        this.nombre = nombre;
    }

    public int getIdTerminal() {
        return idTerminal;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
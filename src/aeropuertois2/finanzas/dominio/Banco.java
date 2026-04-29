package aeropuertois2.finanzas.dominio;

public class Banco {
    private String usuarioGestor;
    private String nombreBanco;
    private String passwordGestor;

    public Banco(String usuarioGestor, String nombreBanco, String passwordGestor) {
        this.usuarioGestor = usuarioGestor;
        this.nombreBanco = nombreBanco;
        this.passwordGestor = passwordGestor;
    }

    public boolean autenticarGestor(String usr, String pass) {
        return this.usuarioGestor.equals(usr) && this.passwordGestor.equals(pass);
    }

    public String getNombreBanco() { return nombreBanco; }
}
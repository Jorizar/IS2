package aeropuertois2.comun.excepciones; 

public class AuthorizationException extends Exception {
    public AuthorizationException(String message) {
        super(message);
    }
}
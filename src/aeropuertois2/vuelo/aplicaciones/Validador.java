package aeropuertois2.vuelo.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;

public interface Validador <T> {
    void validar(T dato) throws ValidationException;
}

package aeropuertois2.vuelo.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.vuelo.transfers.TransferVuelo;

public class ValidadorVuelo implements Validador<TransferVuelo> {

    @Override
    public void validar(TransferVuelo vuelo) throws ValidationException {

        if (vuelo.getIdVuelo() == null || vuelo.getIdVuelo().isBlank()
                || vuelo.getOrigen() == null || vuelo.getOrigen().isBlank()
                || vuelo.getDestino() == null || vuelo.getDestino().isBlank()) {

            throw new ValidationException("Datos del vuelo incompletos.");
        }

        if (vuelo.getOrigen().equalsIgnoreCase(vuelo.getDestino())) {
            throw new ValidationException("Origen y destino no pueden ser iguales.");
        }
    }
}
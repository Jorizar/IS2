package aeropuertois2.vuelo.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.vuelo.transfers.TransferCalendario;

import java.util.List;
import java.util.Scanner;

public class ValidadorCalendarios implements Validador<List<TransferCalendario>> {

    @Override
    public void validar(List<TransferCalendario> calendarios) throws ValidationException {

        if (calendarios == null || calendarios.isEmpty()) {
            throw new ValidationException("Debe existir al menos un calendario.");
        }

        for (int i = 0; i < calendarios.size(); i++) {
            TransferCalendario c = calendarios.get(i);

            if (c.getFechaInicio() == null || c.getFechaFin() == null) {
                throw new ValidationException("Fechas nulas en calendario #" + (i + 1));
            }

            if (c.getFechaFin().isBefore(c.getFechaInicio())) {
                throw new ValidationException("Fecha fin anterior a inicio en calendario #" + (i + 1));
            }

            if (c.getHoraSalida() == null || c.getHoraLlegada() == null) {
                throw new ValidationException("Horarios nulos en calendario #" + (i + 1));
            }

            if (c.getHoraLlegada().isBefore(c.getHoraSalida())) {
                throw new ValidationException("Hora llegada anterior a salida en calendario #" + (i + 1));
            }

            if (!c.getDias().matches("[L_][M_][X_][J_][V_][S_][D_]") || c.getDias().equals("_______")) {
                throw new ValidationException("Formato incorrecto. Usa 7 caracteres: LMXJVSD o _.");
            }

        }
    }
}
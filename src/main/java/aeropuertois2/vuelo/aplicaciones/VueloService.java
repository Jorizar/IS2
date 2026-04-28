
package aeropuertois2.vuelo.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.vuelo.transfers.TransferCalendario;
import aeropuertois2.vuelo.transfers.TransferVuelo;
import aeropuertois2.vuelo.infraestructura.CalendarioDao;
import aeropuertois2.vuelo.infraestructura.VueloDao;

import java.sql.SQLException;
import java.util.List;

public class VueloService {

    private final VueloDao vueloDao;
    private final CalendarioDao calendarioDao;

    public VueloService(VueloDao vueloDao, CalendarioDao calendarioDao) {
        this.vueloDao = vueloDao;
        this.calendarioDao = calendarioDao;
    }

    public boolean crearVuelo( TransferVuelo vuelo, List<TransferCalendario> calendarios, String idOperador) throws SQLException, ValidationException {

        validarVuelo(vuelo);
        validarNoExisteVuelo(vuelo.getIdVuelo());
        validarCalendarios(calendarios);

        boolean insertado = vueloDao.guardar(vuelo);

        if (!insertado) {
            throw new ValidationException("No se pudo guardar el vuelo en base de datos.");
        }

        if (calendarios != null) {
            for (TransferCalendario c : calendarios) {

                TransferCalendario calendario = new TransferCalendario(
                        c.getFechaInicio(),
                        c.getFechaFin(),
                        c.getHoraSalida(),
                        c.getHoraLlegada(),
                        vuelo.getIdVuelo()
                );

                boolean ok = calendarioDao.guardar(calendario);

                if (!ok) {
                    throw new ValidationException("Error al guardar un calendario del vuelo.");
                }
            }
        }

        return true;
    }

    private void validarCalendarios(List<TransferCalendario> calendarios)
            throws ValidationException {

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
        }
    }

    private void validarVuelo(TransferVuelo vuelo) throws ValidationException {

        if (vuelo.getIdVuelo() == null || vuelo.getIdVuelo().isBlank()
                || vuelo.getOrigen() == null || vuelo.getOrigen().isBlank()
                || vuelo.getDestino() == null || vuelo.getDestino().isBlank()) {

            throw new ValidationException("Datos del vuelo incompletos.");
        }

        if (vuelo.getOrigen().equalsIgnoreCase(vuelo.getDestino())) {
            throw new ValidationException("El origen y destino no pueden ser iguales.");
        }
    }

    private void validarNoExisteVuelo(String idVuelo)throws SQLException, ValidationException {

        if (vueloDao.existe(idVuelo)) {
            throw new ValidationException("Ya existe un vuelo con ese ID.");
        }
    }

    public boolean existeVuelo(String idVuelo) throws SQLException {

        return vueloDao.existe(idVuelo); //Si existe el vuelo, no podemos crear otro vuelo con la misma ID
    }    

}
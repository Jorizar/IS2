package aeropuertois2.vuelo.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.vuelo.infraestructura.CalendarioDao;
import aeropuertois2.vuelo.infraestructura.VueloDao;
import aeropuertois2.vuelo.transfers.TransferCalendario;
import aeropuertois2.vuelo.transfers.TransferVuelo;

import java.sql.SQLException;
import java.util.List;

public class VueloService {

    private final VueloDao vueloDao;
    private final CalendarioDao calendarioDao;

    private final Validador<TransferVuelo> validadorVuelo;
    private final Validador<List<TransferCalendario>> validadorCalendarios;

    public VueloService(VueloDao vueloDao, CalendarioDao calendarioDao) {
        this.vueloDao = vueloDao;
        this.calendarioDao = calendarioDao;
        this.validadorVuelo = new ValidadorVuelo();
        this.validadorCalendarios = new ValidadorCalendarios();
    }

    public boolean crearVuelo(TransferVuelo vuelo,
                              List<TransferCalendario> calendarios,
                              String idOperador)
            throws SQLException, ValidationException {

        // Strategy: validaciones que NO dependen de BD
        validadorVuelo.validar(vuelo);
        validadorCalendarios.validar(calendarios);

        // Regla que depende de BD: se queda en el Service
        validarNoExisteVuelo(vuelo.getIdVuelo());

        boolean insertado = vueloDao.guardar(vuelo, idOperador);

        if (!insertado) {
            throw new ValidationException("No se pudo guardar el vuelo en base de datos.");
        }

        for (TransferCalendario c : calendarios) {
            TransferCalendario calendario = new TransferCalendario(
                    c.getFechaInicio(),
                    c.getFechaFin(),
                    c.getHoraSalida(),
                    c.getHoraLlegada(),
                    vuelo.getIdVuelo(),
                    c.getDias()
            );

            boolean ok = calendarioDao.guardar(calendario);

            if (!ok) {
                throw new ValidationException("Error al guardar un calendario del vuelo.");
            }
        }

        return true;
    }

    private void validarNoExisteVuelo(String idVuelo)
            throws SQLException, ValidationException {

        if (vueloDao.existe(idVuelo)) {
            throw new ValidationException("Ya existe un vuelo con ese ID.");
        }
    }

    public boolean existeVuelo(String idVuelo) throws SQLException {
        return vueloDao.existe(idVuelo);
    }

    public List<TransferVuelo> listarVuelos() throws SQLException {
        return vueloDao.listarVuelos();
    }
}
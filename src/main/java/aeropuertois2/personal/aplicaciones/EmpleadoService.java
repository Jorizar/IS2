package aeropuertois2.personal.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.personal.dominio.Empleado;
import aeropuertois2.personal.dominio.FiltroTipo;
import aeropuertois2.personal.infraestructura.EmpleadoDao;

import java.sql.SQLException;
import java.util.List;

public class EmpleadoService {

    private final EmpleadoDao empleadoDao;

    public EmpleadoService(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }

    public List<Empleado> listarTodos() throws SQLException {
        return empleadoDao.listarTodos();
    }

    public List<Empleado> buscarPorNombreODni(String criterio) throws SQLException, ValidationException {
        ValidadorEmpleado.validarBusqueda(criterio);

        if (ValidadorEmpleado.esDni(criterio)) {
            return empleadoDao.buscarPorDni(criterio.trim());
        } else {
            return empleadoDao.buscarPorNombre(criterio.trim());
        }
    }

    public List<Empleado> filtrar(FiltroTipo tipo, String valor) throws SQLException, ValidationException {
        if (tipo == null) {
            throw new ValidationException("Debe seleccionar un tipo de filtro válido.");
        }

        if (valor == null || valor.isBlank()) {
            throw new ValidationException("Debe seleccionar un valor de filtro válido.");
        }

        return empleadoDao.filtrarPor(tipo, valor.trim().toLowerCase());
    }
}

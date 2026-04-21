package aeropuertois2.personal.controladores;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.AuthorizationException;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.personal.aplicaciones.AuthService;
import aeropuertois2.personal.aplicaciones.EmpleadoService;
import aeropuertois2.personal.dominio.Empleado;
import aeropuertois2.personal.dominio.FiltroTipo;
import aeropuertois2.personal.infraestructura.EmpleadoDao;

import java.sql.SQLException;
import java.util.List;

public class PersonalController {

    private final AuthService authService;
    private final EmpleadoService empleadoService;

    public PersonalController() {
        DatabaseConfig config = DatabaseConfig.load();
        DatabaseConnection databaseConnection = new DatabaseConnection(config);
        EmpleadoDao empleadoDao = new EmpleadoDao(databaseConnection);

        this.authService = new AuthService(empleadoDao);
        this.empleadoService = new EmpleadoService(empleadoDao);
    }

    public Empleado login(String dni, String password)
            throws SQLException, AuthorizationException, ValidationException {
        return authService.login(dni, password);
    }

    public List<Empleado> listarTodos() throws SQLException {
        return empleadoService.listarTodos();
    }

    public List<Empleado> buscarPorNombreODni(String criterio)
            throws SQLException, ValidationException {
        return empleadoService.buscarPorNombreODni(criterio);
    }

    public List<Empleado> filtrar(FiltroTipo tipo, String valor)
            throws SQLException, ValidationException {
        return empleadoService.filtrar(tipo, valor);
    }
}

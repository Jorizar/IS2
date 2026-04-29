package aeropuertois2.finanzas.controlador;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.aplicaciones.ContabilidadService;
import aeropuertois2.finanzas.dominio.RegistroContable;
import aeropuertois2.finanzas.infrastructura.RegistroContableDAO;

import java.sql.SQLException;
import java.util.List;

public class ContabilidadController {
    
    private final ContabilidadService contabilidadService;

    public ContabilidadController() {
        DatabaseConfig config = DatabaseConfig.load();
        DatabaseConnection dataBaseConnection = new DatabaseConnection(config);
        RegistroContableDAO dao = new RegistroContableDAO(dataBaseConnection);

        this.contabilidadService = ContabilidadService.getInstance(dao);
    }

    public void crearRegistro(String cuenta, String concepto, String tipo, double monto) 
            throws SQLException, ValidationException {
        contabilidadService.crearRegistro(cuenta, concepto, tipo, monto);
    }

    public void modificarRegistro(String idRegistro, String cuenta, String concepto, String tipo, double monto) 
            throws SQLException, ValidationException {
        contabilidadService.modificarRegistro(idRegistro, cuenta, concepto, tipo, monto);
    }
    
    public List<RegistroContable> obtenerTodosRegistros() throws SQLException {
        return contabilidadService.obtenerTodosRegistros();
    }
}
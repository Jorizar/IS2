package aeropuertois2.finanzas.controlador;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.aplicaciones.NominaService;
import aeropuertois2.finanzas.aplicaciones.ContabilidadService;
import aeropuertois2.finanzas.infrastructura.NominaDAO;
import aeropuertois2.finanzas.infrastructura.RegistroContableDAO;

import java.sql.SQLException;

public class NominaController {
    private final NominaService nominaService;

    public NominaController() {
        DatabaseConfig config = DatabaseConfig.load();
        DatabaseConnection conn = new DatabaseConnection(config);
        
        NominaDAO nDao = new NominaDAO(conn);
        RegistroContableDAO rDao = new RegistroContableDAO(conn);
        
        ContabilidadService cServ = ContabilidadService.getInstance(rDao);
        this.nominaService = NominaService.getInstance(nDao, cServ);
    }

    public void emitirNomina(String empId, String iban, double bruto, double ret) 
            throws SQLException, ValidationException {
        nominaService.procesarPagoNomina(empId, iban, bruto, ret);
    }
}
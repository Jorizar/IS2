package aeropuertois2.finanzas.controlador;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.aplicaciones.CuentaBancariaService;
import aeropuertois2.finanzas.infrastructura.CuentaBancariaDAO;

import java.sql.SQLException;

public class CuentaBancariaController {
    
    private final CuentaBancariaService cuentaService;

    public CuentaBancariaController() {
        DatabaseConfig config = DatabaseConfig.load();
        DatabaseConnection dataBaseConnection = new DatabaseConnection(config);
        CuentaBancariaDAO cuentaDAO = new CuentaBancariaDAO(dataBaseConnection);

        this.cuentaService = CuentaBancariaService.getInstance(cuentaDAO);
    }

    public void crearCuenta(String iban, String banco, String sucursal, String tipo, String moneda, double saldo) 
            throws SQLException, ValidationException {
        cuentaService.crearCuenta(iban, banco, sucursal, tipo, moneda, saldo);
    }

    public boolean validarCuentaConBanco(String iban, String usuarioGestor, String passGestor) 
            throws SQLException, ValidationException {
        return cuentaService.validarCuentaConBanco(iban, usuarioGestor, passGestor);
    }
}
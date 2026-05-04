package aeropuertois2.finanzas.controlador;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.aplicaciones.ContabilidadService;
import aeropuertois2.finanzas.aplicaciones.OperacionesService;
import aeropuertois2.finanzas.infrastructura.CuentaBancariaDAO;
import aeropuertois2.finanzas.infrastructura.RegistroContableDAO;

import java.sql.SQLException;

public class OperacionesController {

	private final OperacionesService operacionesService;

	public OperacionesController() {
		DatabaseConfig config = DatabaseConfig.load();
		DatabaseConnection conn = new DatabaseConnection(config);

		CuentaBancariaDAO cuentaDao = new CuentaBancariaDAO(conn);
		RegistroContableDAO registroDao = new RegistroContableDAO(conn);

		ContabilidadService contabilidadService = ContabilidadService.getInstance(registroDao);
		this.operacionesService = OperacionesService.getInstance(cuentaDao, contabilidadService);
	}

	public void transferir(String ibanOrigen, String ibanDestino, double monto, String concepto)
			throws SQLException, ValidationException {
		operacionesService.realizarTransferencia(ibanOrigen, ibanDestino, monto, concepto);
	}
}
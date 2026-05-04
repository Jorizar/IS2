
package aeropuertois2.vuelo.controladores;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.vuelo.aplicaciones.VueloService;
import aeropuertois2.vuelo.transfers.TransferCalendario;
import aeropuertois2.vuelo.transfers.TransferVuelo;
import aeropuertois2.vuelo.infraestructura.CalendarioDao;
import aeropuertois2.vuelo.infraestructura.VueloDao;

import java.sql.SQLException;
import java.util.List;

public class VueloController {

	private final VueloService vueloService;

	public VueloController() {
		DatabaseConfig config = DatabaseConfig.load();
		DatabaseConnection databaseConnection = new DatabaseConnection(config);

		VueloDao vueloDao = new VueloDao(databaseConnection);
		CalendarioDao calendarioDao = new CalendarioDao(databaseConnection);

		this.vueloService = new VueloService(vueloDao, calendarioDao);
	}

	public boolean crearVuelo(TransferVuelo vuelo, List<TransferCalendario> calendarios, String idOperador)
			throws SQLException, ValidationException {

		return vueloService.crearVuelo(vuelo, calendarios, idOperador);
	}

	public boolean existeVuelo(String idVuelo) throws SQLException {
		return vueloService.existeVuelo(idVuelo);
	}
}

package aeropuertois2.incidencia.controlador;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.incidencia.aplicaciones.IncidenciaService;
import aeropuertois2.incidencia.dominio.tipoIncidencia;
import aeropuertois2.incidencia.infrastructura.IncidenciaDAO;

import java.sql.SQLException;

public class IncidenciaController {

	private final IncidenciaService incidenciaService;

	public IncidenciaController() {
		DatabaseConfig config = DatabaseConfig.load();
		DatabaseConnection dataBaseConnection = new DatabaseConnection(config);
		IncidenciaDAO incidenciaDAO = new IncidenciaDAO(dataBaseConnection);

		this.incidenciaService = IncidenciaService.getInstance(incidenciaDAO); // crea la instancia unica con el dao
	}

	public void crearIncidencia(String id, tipoIncidencia tipo, String descrip, String causa)
			throws SQLException, ValidationException {
		incidenciaService.crearIncidencia(id, tipo, descrip, causa);
	}
}
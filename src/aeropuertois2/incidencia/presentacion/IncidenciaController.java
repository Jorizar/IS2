package aeropuertois2.incidencia.presentacion;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.incidencia.aplicaciones.IncidenciaService;
import aeropuertois2.incidencia.dominio.Incidencia;
import aeropuertois2.incidencia.dominio.tipoIncidencia;
import aeropuertois2.incidencia.infrastructura.IncidenciaDAO;
import aeropuertois2.paneles.infraestructura.AvisoDao;
import aeropuertois2.paneles.negocio.ServicioPaneles;

import java.sql.SQLException;
import java.util.List;

public class IncidenciaController {

	private final IncidenciaService incidenciaService;

	public IncidenciaController() {
		DatabaseConfig config = DatabaseConfig.load();
		DatabaseConnection dataBaseConnection = new DatabaseConnection(config);
		IncidenciaDAO incidenciaDAO = new IncidenciaDAO(dataBaseConnection);

		AvisoDao avisoDao = new AvisoDao(dataBaseConnection);
		ServicioPaneles servicioPaneles = new ServicioPaneles(avisoDao);

		this.incidenciaService = IncidenciaService.getInstance(incidenciaDAO, servicioPaneles); // crea la instancia
																								// unica con el dao
	}

	public void crearIncidencia(String id, tipoIncidencia tipo, String descrip, String causa)
			throws SQLException, ValidationException {
		incidenciaService.crearIncidencia(id, tipo, descrip, causa);
	}

	public List<Incidencia> mostrarIncidencias() throws SQLException {
		return incidenciaService.mostrarIncidencias();
	}
}
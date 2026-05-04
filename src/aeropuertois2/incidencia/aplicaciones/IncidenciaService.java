package aeropuertois2.incidencia.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.incidencia.dominio.Incidencia;
import aeropuertois2.incidencia.dominio.tipoIncidencia;
import aeropuertois2.incidencia.infrastructura.IncidenciaDAO;
import aeropuertois2.paneles.negocio.ServicioPaneles;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class IncidenciaService {
	private static IncidenciaService instancia; // unica instancia (singleton)
	private final IncidenciaDAO incidenciaDao;
	private ServicioPaneles paneles;

	// constructor privado (singleton)
	private IncidenciaService(IncidenciaDAO incidenciaDao, ServicioPaneles paneles) {
		this.incidenciaDao = incidenciaDao;
		this.paneles = paneles;
	}

	// metodo para obtener la instancia unica
	public static IncidenciaService getInstance(IncidenciaDAO incidenciaDao, ServicioPaneles paneles) {
		if (instancia == null)
			instancia = new IncidenciaService(incidenciaDao, paneles);

		return instancia;
	}

	// metodo que "orquesta" el resto de operaciones (fachada/facade)
	public void crearIncidencia(String id, tipoIncidencia tipo, String descrip, String causa)
			throws ValidationException, SQLException {
		ValidadorIncidencia.validarNuevaIncidencia(id, descrip, causa); // validamos los datos introducidos
		Incidencia nuevaIncidencia = new Incidencia(id, tipo, descrip, causa); // creamos la incidencia
		incidenciaDao.crearIncidencia(nuevaIncidencia);

		/*
		 * --- LA INCIDENCIA CREA UN NUEVO AVISO EN PANELES SI ES DE TIPO GENERAL ---
		 */
		if (tipo == tipoIncidencia.GENERAL)
			paneles.crearAviso(id, LocalDateTime.now(), descrip);

		System.out.println("Incidencia creada con exito.");
	}

	public List<Incidencia> mostrarIncidencias() throws SQLException {
		return incidenciaDao.mostrarIncidencias();
	}
}

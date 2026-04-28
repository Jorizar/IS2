package aeropuertois2.incidencia.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.incidencia.dominio.Incidencia;
import aeropuertois2.incidencia.dominio.tipoIncidencia;
import aeropuertois2.incidencia.infrastructura.IncidenciaDAO;

import java.sql.SQLException;

public class IncidenciaService {
	private static IncidenciaService instancia; // unica instancia (singleton)
	private final IncidenciaDAO incidenciaDao;

	// constructor privado (singleton)
	private IncidenciaService(IncidenciaDAO incidenciaDao) {
		this.incidenciaDao = incidenciaDao;
	}

	// metodo para obtener la instancia unica
	public static IncidenciaService getInstance(IncidenciaDAO incidenciaDao) {
		if (instancia == null)
			instancia = new IncidenciaService(incidenciaDao);

		return instancia;
	}

	// metodo que "orquesta" el resto de operaciones (fachada/facade)
	public void crearIncidencia(String id, tipoIncidencia tipo, String descrip, String causa)
			throws ValidationException, SQLException {
		ValidadorIncidencia.validarNuevaIncidencia(id, descrip, causa); // validamos los datos introducidos
		Incidencia nuevaIncidencia = new Incidencia(id, tipo, descrip, causa); // creamos la incidencia
		incidenciaDao.crearIncidencia(nuevaIncidencia);

		/*
		 * --- LA INCIDENCIA CREAR EL AVISO EN PANELES ---
		 * 
		 * if (tipo == TipoIncidencia.GENERAL) paneles.crearAviso();
		 */

		System.out.println("Incidencia creada con exito.");
	}
}

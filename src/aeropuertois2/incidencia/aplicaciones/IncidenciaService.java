package aeropuertois2.incidencia.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.incidencia.dominio.Incidencia;
import aeropuertois2.incidencia.dominio.tipoIncidencia;
import aeropuertois2.incidencia.infrastructura.IncidenciaDAO;

import java.sql.SQLException;

public class IncidenciaService {

	// --- PARTE DEL PATRÓN SINGLETON ---
    private static IncidenciaService instancia;
    private final IncidenciaDAO incidenciaDao;

    // Constructor privado: nadie puede hacer "new" desde fuera
    private IncidenciaService(IncidenciaDAO incidenciaDao) {
        this.incidenciaDao = incidenciaDao;
    }

    // Método para obtener la instancia única (se le pasa el DAO la primera vez)
    public static IncidenciaService getInstance(IncidenciaDAO incidenciaDao) {
        if (instancia == null) 
            instancia = new IncidenciaService(incidenciaDao);
        
        return instancia;
    }

    // --- PARTE DEL CASO DE USO (FACHADA) ---
    
    /**
     * Ejecuta el caso de uso: Crear una nueva incidencia.
     */
    public void crearIncidencia(String id, tipoIncidencia tipo, String descrip, String causa) throws ValidationException, SQLException {
        ValidadorIncidencia.validarNuevaIncidencia(id, descrip, causa); //validamos los datos introducidos
        Incidencia nuevaIncidencia = new Incidencia(id, tipo, descrip, causa); //creamos la incidencia

        incidenciaDao.crearIncidencia(nuevaIncidencia);
        
        System.out.println("Incidencia creada con exito.");
    }
}

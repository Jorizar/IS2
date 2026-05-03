package aeropuertois2.paneles.controladores;

import java.util.List;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.paneles.aplicaciones.ServicioPaneles;
import aeropuertois2.paneles.dominio.Aviso;
import aeropuertois2.paneles.infraestructura.AvisoDao;


public class ControladorPaneles {

    private ServicioPaneles servicio;

    public ControladorPaneles() {
    	 DatabaseConfig config = DatabaseConfig.load();
         DatabaseConnection databaseConnection = new DatabaseConnection(config);
         AvisoDao avisoDao = new AvisoDao(databaseConnection);
         
        this.servicio = new ServicioPaneles(avisoDao);
    }

    public void crearAviso(String mensaje, String tipo, String vuelo) {
        servicio.crearAviso(mensaje, tipo, vuelo);
    }

    public List<Aviso> obtenerAvisos() {
        return servicio.obtenerAvisos();
    }
}
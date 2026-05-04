package aeropuertois2.paneles.presentacion;

import java.time.LocalDateTime;
import java.util.List;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.paneles.infraestructura.AvisoDao;
import aeropuertois2.paneles.modelo.Aviso;
import aeropuertois2.paneles.negocio.ServicioPaneles;

public class ControladorPaneles {

	private ServicioPaneles servicio;

	public ControladorPaneles() {
		DatabaseConfig config = DatabaseConfig.load();
		DatabaseConnection databaseConnection = new DatabaseConnection(config);
		AvisoDao avisoDao = new AvisoDao(databaseConnection);

		this.servicio = new ServicioPaneles(avisoDao);
	}

	public void crearAviso(String id, LocalDateTime fecha, String mensaje) {
		servicio.crearAviso(id, fecha, mensaje);
	}

	public List<Aviso> obtenerAvisos() {
		return servicio.obtenerAvisos();
	}
}
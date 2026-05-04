package aeropuertois2.operaciones.aplicaciones;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.operaciones.dominio.Puerta;
import aeropuertois2.operaciones.dominio.Terminal;
import aeropuertois2.operaciones.dominio.ZonaTerminal;
import aeropuertois2.operaciones.infraestructura.PuertaDAO;
import aeropuertois2.operaciones.infraestructura.TerminalDAO;

import java.sql.SQLException;
import java.util.List;

public class GestionPuertasService {

	private final TerminalDAO terminalDAO;
	private final PuertaDAO puertaDAO;

	public GestionPuertasService(DatabaseConnection conexion) {
		this.terminalDAO = new TerminalDAO(conexion);
		this.puertaDAO = new PuertaDAO(conexion);
	}

	public List<Terminal> obtenerTodasLasTerminales() throws SQLException {
		return terminalDAO.obtenerTodas();
	}

	public List<Puerta> obtenerPuertas(int idTerminal, ZonaTerminal zona) throws SQLException {
		return puertaDAO.obtenerPorTerminalYZona(idTerminal, zona);
	}

	public boolean comprobarSiTieneAvion(int idPuerta) throws SQLException {
		return puertaDAO.tieneAvionAsignado(idPuerta);
	}

	public boolean registrarNuevaPuerta(Puerta puerta) throws SQLException {
		return puertaDAO.crear(puerta);
	}

	public boolean borrarPuerta(int idPuerta) throws SQLException {
		return puertaDAO.eliminar(idPuerta);
	}
}
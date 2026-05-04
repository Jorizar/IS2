package aeropuertois2.operaciones.controlador;

import aeropuertois2.operaciones.aplicaciones.GestionPuertasService;
import aeropuertois2.operaciones.dominio.Puerta;
import aeropuertois2.operaciones.dominio.Terminal;
import aeropuertois2.operaciones.dominio.ZonaTerminal;
import aeropuertois2.operaciones.presentacion.GestionPuertasView;
import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GestionPuertasController {

	private final GestionPuertasService servicio;
	private GestionPuertasView vista;

	public GestionPuertasController() {
		DatabaseConfig config = DatabaseConfig.load();
		DatabaseConnection conexion = new DatabaseConnection(config);
		this.servicio = new GestionPuertasService(conexion);
	}

	public void iniciar(Scanner scanner) {
		this.vista = new GestionPuertasView(scanner);
		boolean salir = false;

		while (!salir) {
			try {

				List<Terminal> terminales = servicio.obtenerTodasLasTerminales();
				vista.mostrarMensaje("\n--- SELECCIÓN DE TERMINAL ---");
				for (int i = 0; i < terminales.size(); i++) {
					vista.mostrarMensaje((i + 1) + ". " + terminales.get(i).getNombre());
				}
				vista.mostrarMensaje("0. Volver atrás");

				int opc = vista.leerEntero("Seleccione terminal: ");
				if (opc == 0)
					return;

				if (opc > 0 && opc <= terminales.size()) {
					menuZonas(terminales.get(opc - 1));
				}
			} catch (SQLException e) {
				vista.mostrarError("Error de BD: " + e.getMessage());
				return;
			}
		}
	}

	private void menuZonas(Terminal terminal) throws SQLException {
		boolean volver = false;
		while (!volver) {
			vista.mostrarMensaje("\n--- ZONAS DE " + terminal.getNombre() + " ---");
			ZonaTerminal[] zonas = ZonaTerminal.values();
			for (int i = 0; i < zonas.length; i++) {
				vista.mostrarMensaje((i + 1) + ". " + zonas[i].getNombre());
			}
			vista.mostrarMensaje("0. Cambiar terminal");

			int opc = vista.leerEntero("Seleccione zona: ");
			if (opc == 0)
				return;

			if (opc > 0 && opc <= zonas.length) {
				menuGestionPuertas(terminal, zonas[opc - 1]);
			}
		}
	}

	private void menuGestionPuertas(Terminal t, ZonaTerminal z) throws SQLException {
		boolean volver = false;
		while (!volver) {
			vista.mostrarMensaje("\n--- GESTIÓN DE PUERTAS (" + z.getNombre() + ") ---");
			List<Puerta> puertas = servicio.obtenerPuertas(t.getIdTerminal(), z);

			for (Puerta p : puertas) {
				boolean tieneAvion = servicio.comprobarSiTieneAvion(p.getIdPuerta());
				String ocupacion = tieneAvion ? "[CON AVIÓN]" : "[VACÍA]";
				String bloqueo = p.isBloqueada() ? "[BLOQUEADA]" : "[ACTIVA]";
				vista.mostrarMensaje(
						"ID: " + p.getIdPuerta() + " | Gate: " + p.getNumeroGate() + " " + ocupacion + " " + bloqueo);
			}

			vista.mostrarMensaje("\nACCIONES:");
			vista.mostrarMensaje("1. Crear nueva Puerta");
			vista.mostrarMensaje("2. Eliminar Puerta");
			vista.mostrarMensaje("0. Volver");

			int accion = vista.leerEntero("Opción: ");
			switch (accion) {
			case 1 -> crearNuevaPuerta(t, z);
			case 2 -> borrarPuerta();
			case 0 -> volver = true;
			default -> vista.mostrarError("Opción no válida.");
			}
		}
	}

	private void crearNuevaPuerta(Terminal t, ZonaTerminal z) throws SQLException {
		String gate = vista.leerCadena("Nombre del nuevo Gate (Ej: A20): ");
		Puerta p = new Puerta(0, gate, t.getIdTerminal(), z, false);
		if (servicio.registrarNuevaPuerta(p)) {
			vista.mostrarMensaje("Puerta creada con éxito.");
		}
	}

	private void borrarPuerta() throws SQLException {
		int id = vista.leerEntero("ID de la puerta a eliminar: ");

		if (servicio.comprobarSiTieneAvion(id)) {
			vista.mostrarError(
					"Error: No se puede eliminar la puerta porque tiene un avión asignado actualmente. Desocúpela primero.");
		}

		if (vista.leerConfirmacion("¿Seguro que desea eliminarla?")) {
			if (servicio.borrarPuerta(id)) {
				vista.mostrarMensaje("Puerta eliminada correctamente.");
			} else {
				vista.mostrarError("No se pudo eliminar la puerta.");
			}
		}
	}
}
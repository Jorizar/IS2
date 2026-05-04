package aeropuertois2.inicio.controladores;

import aeropuertois2.personal.presentacion.PersonalMenu;
import aeropuertois2.finanzas.presentacion.FinanzasMenu;
import aeropuertois2.incidencia.presentacion.IncidenciaMenu;
import aeropuertois2.vuelo.presentacion.VueloMenu;
import aeropuertois2.seguridad.vista.CrearAccesoView;
import aeropuertois2.operaciones.presentacion.OperacionesMenu;
import aeropuertois2.paneles.presentacion.VistaPaneles;

import java.util.Scanner;

public class InicioController {

	public boolean procesarOpcion(String opcion, Scanner scanner) {
		switch (opcion) {
		case "1" -> {
			PersonalMenu personalMenu = new PersonalMenu();
			personalMenu.iniciar(scanner);
			return true;
		}
		case "2" -> {
			FinanzasMenu finanzasMenu = new FinanzasMenu();
			finanzasMenu.iniciar(scanner);
			return true;
		}
		case "3" -> {
			IncidenciaMenu incidenciaMenu = new IncidenciaMenu();
			incidenciaMenu.iniciar(scanner);
			return true;
		}
		case "4" -> {
			OperacionesMenu operacionesMenu = new OperacionesMenu();
			operacionesMenu.iniciar(scanner);
			return true;
		}
		case "5" -> {
			VistaPaneles panelMenu = new VistaPaneles();
			panelMenu.iniciar(scanner);
			return true;
		}
		case "6" -> {
			CrearAccesoView view = new CrearAccesoView();
			view.mostrarFormulario();

			return true;
		}
		case "7" -> {
			VueloMenu vueloMenu = new VueloMenu();
			vueloMenu.iniciar(scanner);
			return true;
		}
		case "8" -> {
			System.out.println("Saliendo del sistema...");
			return false;
		}
		default -> {
			System.out.println("Opción no válida.");
			return true;
		}
		}
	}
}

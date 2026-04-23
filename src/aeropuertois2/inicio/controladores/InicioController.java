package aeropuertois2.inicio.controladores;

import aeropuertois2.personal.presentacion.PersonalMenu;
import aeropuertois2.incidencia.presentacion.IncidenciaMenu;

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
			System.out.println("Módulo Financiera no implementado todavía.");
			return true;
		}
		case "3" -> {
			IncidenciaMenu incidenciaMenu = new IncidenciaMenu();
			incidenciaMenu.iniciar();
			return true;
		}
		case "4" -> {
			System.out.println("Módulo Operaciones no implementado todavía.");
			return true;
		}
		case "5" -> {
			System.out.println("Módulo Paneles no implementado todavía.");
			return true;
		}
		case "6" -> {
			System.out.println("Módulo Seguridad no implementado todavía.");
			return true;
		}
		case "7" -> {
			System.out.println("Módulo Vuelos no implementado todavía.");
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
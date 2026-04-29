package aeropuertois2.incidencia.presentacion;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.incidencia.aplicaciones.IncidenciaService;
import aeropuertois2.incidencia.aplicaciones.ValidadorIncidencia;
import aeropuertois2.incidencia.dominio.tipoIncidencia;
import aeropuertois2.incidencia.infrastructura.IncidenciaDAO;
import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection; // Asumiendo que esta es la ruta
import aeropuertois2.personal.presentacion.ConsolePrinter;

import java.sql.SQLException;
import java.util.Scanner;

public class IncidenciaMenu {

	private final IncidenciaService incidenciaService;

	public IncidenciaMenu() {
		DatabaseConfig config = DatabaseConfig.load();
		DatabaseConnection dataBaseConnection = new DatabaseConnection(config);
		IncidenciaDAO incidenciaDAO = new IncidenciaDAO(dataBaseConnection);
		this.incidenciaService = IncidenciaService.getInstance(incidenciaDAO);
	}

	public void iniciar(Scanner scanner) {
		ConsolePrinter.printTitulo("Gestion de Incidencias - Nuevo Registro de Incidencia");
		ejecutarCrearIncidencia(scanner);
		
	}

	private void ejecutarCrearIncidencia(Scanner scanner) {
		try {
			String id = pedirId(scanner);
			tipoIncidencia tipo = seleccionarTipo(scanner);
			String descrip = pedirDescrip(scanner);
			String causa = pedirCausa(scanner);

			incidenciaService.crearIncidencia(id, tipo, descrip, causa);
			System.out.println("\n[ÉXITO] La incidencia ha sido volcada en la base de datos.");

		} catch (ValidationException e) {
			// no deberia saltar nunca porque ya se gestiona antes, pero por si acaso
			System.out.println("ERROR de validacion: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("ERROR de base de datos: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private String pedirId(Scanner scanner) {
		String id = "";
		boolean idValido = false;

		while (!idValido) // hasta que id sea valido
		{
			System.out.print("Introduzca ID (9 digitos): ");
			id = scanner.nextLine();

			try {
				ValidadorIncidencia.validarId(id);
				idValido = true;
			} catch (ValidationException e) {
				System.out.println("ERROR de validacion: " + e.getMessage() + " Intentelo de nuevo.\n");
			}
		}
		return id;
	}

	private String pedirDescrip(Scanner scanner) {
		String descrip = "";
		boolean descripValida = false;
		while (!descripValida) // hasta que descrp sea valido
		{
			System.out.print("Introduzdca descripcion del suceso (max 200 caracteres): ");
			descrip = scanner.nextLine();
			try {
				ValidadorIncidencia.validarDescrip(descrip);
				descripValida = true;
			} catch (ValidationException e) {
				System.out.println("ERROR de validacion: " + e.getMessage() + " Intentelo de nuevo.\n");
			}
		}
		return descrip;
	}

	private String pedirCausa(Scanner scanner) {
		String causa = "";
		boolean causaValida = false;
		while (!causaValida) // hasta que causa sea valido
		{
			System.out.print("Introduzca causa aparente (max 100 caracteres): ");
			causa = scanner.nextLine();
			try {
				ValidadorIncidencia.validarCausa(causa);
				causaValida = true;
			} catch (ValidationException e) {
				System.out.println("ERROR de validacion: " + e.getMessage() + " Intentelo de nuevo.\n");
			}
		}
		return causa;
	}

	private tipoIncidencia seleccionarTipo(Scanner scanner) {
		System.out.println("Tipos de incidencias:");
		System.out.println("1. General");
		System.out.println("2. Personal");
		System.out.print("Seleccione un tipo: ");
		String op = scanner.nextLine();

		return op.equals("2") ? tipoIncidencia.PERSONAL : tipoIncidencia.GENERAL;
	}
}
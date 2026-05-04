package aeropuertois2.vuelo.presentacion;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.vuelo.controladores.VueloController;
import aeropuertois2.vuelo.transfers.TransferCalendario;
import aeropuertois2.vuelo.transfers.TransferVuelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VueloMenu {

	private final VueloController vueloController = new VueloController();

	public void iniciar(Scanner scanner) {

		try {
			printTitulo("Gestión de Vuelos");

			// ---------------- 1. ID VÁLIDO PRIMERO ----------------
			String idVuelo = pedirIdValido(scanner);

			// ---------------- 2. DATOS VUELO ----------------
			String origen = pedirTexto(scanner, "Origen: ");
			String destino = pedirTexto(scanner, "Destino: ");
			String aerolinea = pedirTexto(scanner, "Aerolínea: ");

			TransferVuelo vuelo = new TransferVuelo(idVuelo, origen, destino, aerolinea);

			// ---------------- 3. CALENDARIOS ----------------
			List<TransferCalendario> calendarios = pedirCalendarios(scanner, idVuelo);

			// ---------------- 4. CREAR ----------------
			boolean creado = vueloController.crearVuelo(vuelo, calendarios, "operador");

			System.out.println(creado ? "Vuelo creado correctamente." : "No se pudo crear el vuelo.");

		} catch (ValidationException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error de base de datos: " + e.getMessage());
		}
	}

	// ---------------- ID VALIDADO ----------------

	private String pedirIdValido(Scanner scanner) {

		while (true) {

			System.out.print("ID vuelo: ");
			String id = scanner.nextLine().trim();

			if (id.isBlank()) {
				System.out.println("El ID no puede estar vacío.");
				continue;
			}

			try {
				if (vueloController.existeVuelo(id)) {
					System.out.println("El ID ya existe. Introduce otro.");
					continue;
				}
			} catch (SQLException e) {
				System.out.println("Error comprobando el ID.");
				continue;
			}

			return id; // SOLO SALE SI ES VÁLIDO
		}
	}

	// ---------------- INPUT SIMPLE ----------------

	private String pedirTexto(Scanner scanner, String msg) {
		System.out.print(msg);
		return scanner.nextLine().trim();
	}

	// ---------------- CALENDARIOS ----------------

	private List<TransferCalendario> pedirCalendarios(Scanner scanner, String idVuelo) {

		List<TransferCalendario> calendarios = new ArrayList<>();

		boolean continuar = true;

		while (continuar) {

			System.out.println("\n--- Nuevo calendario ---");

			LocalDate inicio = pedirFecha(scanner, "Fecha inicio (YYYY-MM-DD): ");
			LocalDate fin = pedirFecha(scanner, "Fecha fin (YYYY-MM-DD): ");
			LocalTime salida = pedirHora(scanner, "Hora salida (HH:MM): ");
			LocalTime llegada = pedirHora(scanner, "Hora llegada (HH:MM): ");

			calendarios.add(new TransferCalendario(inicio, fin, salida, llegada, idVuelo));

			System.out.print("¿Añadir otro calendario? (s/n): ");
			continuar = scanner.nextLine().trim().equalsIgnoreCase("s");
		}

		return calendarios;
	}

	private LocalDate pedirFecha(Scanner scanner, String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return LocalDate.parse(scanner.nextLine());
			} catch (DateTimeParseException e) {
				System.out.println("Formato incorrecto (YYYY-MM-DD)");
			}
		}
	}

	private LocalTime pedirHora(Scanner scanner, String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return LocalTime.parse(scanner.nextLine());
			} catch (DateTimeParseException e) {
				System.out.println("Formato incorrecto (HH:MM)");
			}
		}
	}

	private static void printTitulo(String titulo) {
		System.out.println();
		System.out.println("==============================================================");
		System.out.println(titulo);
		System.out.println("==============================================================");
	}
}

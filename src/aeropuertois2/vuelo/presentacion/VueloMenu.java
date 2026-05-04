package aeropuertois2.vuelo.presentacion;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.personal.presentacion.ConsolePrinter;
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

    private final VueloController vueloController;

    public VueloMenu() {
        vueloController = new VueloController();
    }

    public void iniciar(Scanner scanner) {
        //ConsolePrinter.printTitulo("Gestion de Incidencias - Nuevo Registro de Incidencia");
        //ejecutarCrearIncidencia(scanner);


        boolean salir = false;
        while (!salir) {
            ConsolePrinter.printTitulo("Gestion de Vuelos");
            System.out.println("1. Crear nuevo vuelo");
            System.out.println("2. Mostrar todas los vuelos");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> ejecutarCrearVuelo(scanner);
                case "2" -> ejecutarMostrarVuelos();
                case "3" -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }

    }

    private void ejecutarMostrarVuelos() {
        try {
            List<TransferVuelo> vuelos = vueloController.mostrarVuelos();

            System.out.println("--------------------------- VUELOS ---------------------------");
            System.out.printf("%-10s %-20s %-20s %-15s%n",
                    "ID", "ORIGEN", "DESTINO", "AEROLINEA");

            for (TransferVuelo vuelo : vuelos) {
                System.out.printf("%-10s %-20s %-20s %-15s%n",
                        vuelo.getIdVuelo(),
                        vuelo.getOrigen(),
                        vuelo.getDestino(),
                        vuelo.getAerolinea());
            }

        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    public void ejecutarCrearVuelo(Scanner scanner) {

        try {
            // 1. Pedir datos completos del vuelo
            TransferVuelo vuelo = pedirVueloValido(scanner);

            // 2. Pedir calendarios
            List<TransferCalendario> calendarios =
                    pedirCalendarios(scanner, vuelo.getIdVuelo());

            // 3. Crear vuelo
            boolean creado = vueloController.crearVuelo(
                    vuelo,
                    calendarios,
                    "operador"
            );

            System.out.println(creado
                    ? "Vuelo creado correctamente."
                    : "No se pudo crear el vuelo.");

        } catch (ValidationException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    // ---------------- VUELO COMPLETO VALIDADO ----------------

    private TransferVuelo pedirVueloValido(Scanner scanner) {

        while (true) {

            System.out.println("\n--- Datos del vuelo ---");

            String idVuelo = pedirTexto(scanner, "ID vuelo: ");
            String origen = pedirTexto(scanner, "Origen: ");
            String destino = pedirTexto(scanner, "Destino: ");
            String aerolinea = pedirTexto(scanner, "Aerolínea: ");

            try {
                if (vueloController.existeVuelo(idVuelo)) {
                    System.out.println("Ya existe un vuelo con ese ID. Introduce los datos del vuelo nuevamente.");
                    continue;
                }

                return new TransferVuelo(idVuelo, origen, destino, aerolinea);

            } catch (SQLException e) {
                System.out.println("Error comprobando si existe el vuelo. Inténtalo de nuevo.");
            }
        }
    }

    // ---------------- INPUT SIMPLE ----------------

    private String pedirTexto(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String texto = scanner.nextLine().trim();

            if (!texto.isBlank()) {
                return texto;
            }

            System.out.println("Este campo no puede estar vacío.");
        }
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
            String dias = pedirDias(scanner, "Dias de la semana(L_X__SD): ");
            calendarios.add(new TransferCalendario(
                    inicio,
                    fin,
                    salida,
                    llegada,
                    idVuelo,
                    dias
            ));

            System.out.print("¿Añadir otro calendario? (s/n): ");
            continuar = scanner.nextLine().trim().equalsIgnoreCase("s");
        }

        return calendarios;
    }

    private String pedirDias(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String dias = scanner.nextLine().trim().toUpperCase();

            if (dias.matches("[L_][M_][X_][J_][V_][S_][D_]") && !dias.equals("_______")) {
                return dias;
            }

            System.out.println("Formato incorrecto. Usa 7 caracteres: LMXJVSD o _.");
            System.out.println("Ejemplos: LMXJV__, L_XJ_SD, _____SD");
        }
    }

    private LocalDate pedirFecha(Scanner scanner, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Usa YYYY-MM-DD.");
            }
        }
    }

    private LocalTime pedirHora(Scanner scanner, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalTime.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Usa HH:MM.");
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
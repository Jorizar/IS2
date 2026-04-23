package aeropuertois2.personal.presentacion;

import aeropuertois2.comun.excepciones.AuthorizationException;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.personal.controladores.PersonalController;
import aeropuertois2.personal.dominio.Empleado;
import aeropuertois2.personal.dominio.FiltroTipo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PersonalMenu {

    private final PersonalController personalController;

    public PersonalMenu() {
        this.personalController = new PersonalController();
    }

    public void iniciar() {
        try (Scanner scanner = new Scanner(System.in)) {
            ConsolePrinter.printTitulo("Sistema Personal - Login");

            System.out.print("Introduzca su DNI: ");
            String dni = scanner.nextLine();

            System.out.print("Introduzca su contraseña: ");
            String password = scanner.nextLine();

            Empleado empleadoLogueado = personalController.login(dni, password);
            System.out.println("Login correcto. Bienvenido/a, " + empleadoLogueado.getNombre());

            lanzarMenu(scanner);

        } catch (ValidationException | AuthorizationException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Se ha producido un error inesperado: " + e.getMessage());
        }
    }

    private void lanzarMenu(Scanner scanner) throws SQLException {
        boolean salir = false;

        while (!salir) {
            ConsolePrinter.printTitulo("Módulo Personal");
            System.out.println("1. Mostrar lista completa de empleados");
            System.out.println("2. Buscar empleado por nombre o DNI");
            System.out.println("3. Filtrar empleados");
            System.out.println("4. Volver al inicio");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> mostrarTodos();
                case "2" -> buscarEmpleados(scanner);
                case "3" -> filtrarEmpleados(scanner);
                case "4" -> {
                    salir = true;
                    System.out.println("Volviendo al menú principal...");
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void mostrarTodos() throws SQLException {
        ConsolePrinter.printTitulo("Lista completa de empleados");
        List<Empleado> empleados = personalController.listarTodos();
        ConsolePrinter.printEmpleados(empleados);
    }

    private void buscarEmpleados(Scanner scanner) throws SQLException {
        ConsolePrinter.printTitulo("Búsqueda de empleados");
        System.out.print("Introduzca nombre o DNI: ");
        String criterio = scanner.nextLine();

        try {
            List<Empleado> empleados = personalController.buscarPorNombreODni(criterio);
            ConsolePrinter.printEmpleados(empleados);
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("El sistema solicita que introduzca datos válidos.");
        }
    }

    private void filtrarEmpleados(Scanner scanner) throws SQLException {
        ConsolePrinter.printTitulo("Filtro de empleados");
        System.out.println("1. Función");
        System.out.println("2. Rol");
        System.out.println("3. Turno");
        System.out.print("Seleccione el tipo de filtro: ");
        String opcionTipo = scanner.nextLine();

        FiltroTipo tipo;
        String valor;

        switch (opcionTipo) {
            case "1" -> {
                tipo = FiltroTipo.FUNCION;
                valor = seleccionarFuncion(scanner);
            }
            case "2" -> {
                tipo = FiltroTipo.ROL;
                valor = seleccionarRol(scanner);
            }
            case "3" -> {
                tipo = FiltroTipo.TURNO;
                valor = seleccionarTurno(scanner);
            }
            default -> {
                System.out.println("Tipo de filtro no válido.");
                return;
            }
        }

        try {
            List<Empleado> empleados = personalController.filtrar(tipo, valor);
            ConsolePrinter.printEmpleados(empleados);
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String seleccionarFuncion(Scanner scanner) {
        System.out.println("1. financiero");
        System.out.println("2. incidencias");
        System.out.println("3. operaciones");
        System.out.println("4. paneles");
        System.out.println("5. personal");
        System.out.println("6. seguridad");
        System.out.println("7. vuelos");
        System.out.print("Seleccione una función: ");

        return switch (scanner.nextLine()) {
            case "1" -> "financiero";
            case "2" -> "incidencias";
            case "3" -> "operaciones";
            case "4" -> "paneles";
            case "5" -> "personal";
            case "6" -> "seguridad";
            case "7" -> "vuelos";
            default -> "";
        };
    }

    private String seleccionarRol(Scanner scanner) {
        System.out.println("1. personal de equipo");
        System.out.println("2. administrador");
        System.out.print("Seleccione un rol: ");

        return switch (scanner.nextLine()) {
            case "1" -> "personal de equipo";
            case "2" -> "administrador";
            default -> "";
        };
    }

    private String seleccionarTurno(Scanner scanner) {
        System.out.println("1. mañana");
        System.out.println("2. tarde");
        System.out.println("3. noche");
        System.out.print("Seleccione un turno: ");

        return switch (scanner.nextLine()) {
            case "1" -> "mañana";
            case "2" -> "tarde";
            case "3" -> "noche";
            default -> "";
        };
    }
}
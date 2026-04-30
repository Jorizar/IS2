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

	public void iniciar(Scanner scanner) {
		try {
			ConsolePrinter.printTitulo("Sistema Personal - Login");

			System.out.print("Introduzca su DNI: ");
			String dni = scanner.nextLine();

			System.out.print("Introduzca su contraseña: ");
			String password = scanner.nextLine();

			Empleado empleadoLogueado = personalController.login(dni, password);
			System.out.println("Login correcto. Bienvenido/a, " + empleadoLogueado.getNombre());

			lanzarMenu(scanner);

		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		} catch (AuthorizationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error de base de datos: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido un error inesperado: " + e.getMessage());
		}
	}

	private void lanzarMenu(Scanner scanner) {
		boolean salir = false;

		while (!salir) {
			ConsolePrinter.printTitulo("Módulo Personal");
			System.out.println("1. Mostrar lista completa de empleados");
			System.out.println("2. Buscar empleado por nombre o DNI");
			System.out.println("3. Filtrar empleados");
			System.out.println("4. Volver al inicio");
			System.out.print("Seleccione una opción: ");

			String opcion = scanner.nextLine().trim();

			if (opcion.equals("1")) {
				mostrarTodos();
			} else if (opcion.equals("2")) {
				buscarEmpleados(scanner);
			} else if (opcion.equals("3")) {
				filtrarEmpleados(scanner);
			} else if (opcion.equals("4")) {
				salir = true;
				System.out.println("Volviendo al menú principal...");
			} else {
				System.out.println("Opción no válida.");
			}
		}
	}

	private void mostrarTodos() {
		try {
			ConsolePrinter.printTitulo("Lista completa de empleados");
			List<Empleado> empleados = personalController.listarTodos();
			ConsolePrinter.printEmpleados(empleados);
		} catch (SQLException e) {
			System.out.println("Error de base de datos al listar empleados: " + e.getMessage());
		}
	}

	private void buscarEmpleados(Scanner scanner) {
		ConsolePrinter.printTitulo("Búsqueda de empleados");
		System.out.print("Introduzca nombre o DNI: ");
		String criterio = scanner.nextLine();

		try {
			List<Empleado> empleados = personalController.buscarPorNombreODni(criterio);
			ConsolePrinter.printEmpleados(empleados);
		} catch (ValidationException e) {
			System.out.println("Error: " + e.getMessage());
			System.out.println("El sistema solicita que introduzca datos válidos.");
		} catch (SQLException e) {
			System.out.println("Error de base de datos al buscar empleados: " + e.getMessage());
		}
	}

	private void filtrarEmpleados(Scanner scanner) {
		ConsolePrinter.printTitulo("Filtro de empleados");
		System.out.println("1. Función");
		System.out.println("2. Rol");
		System.out.println("3. Turno");
		System.out.print("Seleccione el tipo de filtro: ");
		String opcionTipo = scanner.nextLine().trim();

		FiltroTipo tipo;
		String valor;

		if (opcionTipo.equals("1")) {
			tipo = FiltroTipo.FUNCION;
			valor = seleccionarFuncion(scanner);
		} else if (opcionTipo.equals("2")) {
			tipo = FiltroTipo.ROL;
			valor = seleccionarRol(scanner);
		} else if (opcionTipo.equals("3")) {
			tipo = FiltroTipo.TURNO;
			valor = seleccionarTurno(scanner);
		} else {
			System.out.println("Tipo de filtro no válido.");
			return;
		}

		if (valor == null || valor.trim().isEmpty()) {
			System.out.println("Valor de filtro no válido.");
			return;
		}

		try {
			List<Empleado> empleados = personalController.filtrar(tipo, valor);
			ConsolePrinter.printEmpleados(empleados);
		} catch (ValidationException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error de base de datos al filtrar empleados: " + e.getMessage());
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

		String opcion = scanner.nextLine().trim();

		if (opcion.equals("1")) {
			return "financiero";
		} else if (opcion.equals("2")) {
			return "incidencias";
		} else if (opcion.equals("3")) {
			return "operaciones";
		} else if (opcion.equals("4")) {
			return "paneles";
		} else if (opcion.equals("5")) {
			return "personal";
		} else if (opcion.equals("6")) {
			return "seguridad";
		} else if (opcion.equals("7")) {
			return "vuelos";
		}

		return "";
	}

	private String seleccionarRol(Scanner scanner) {
		System.out.println("1. personal de equipo");
		System.out.println("2. administrador");
		System.out.print("Seleccione un rol: ");

		String opcion = scanner.nextLine().trim();

		if (opcion.equals("1")) {
			return "personal de equipo";
		} else if (opcion.equals("2")) {
			return "administrador";
		}

		return "";
	}

	private String seleccionarTurno(Scanner scanner) {
		System.out.println("1. mañana");
		System.out.println("2. tarde");
		System.out.println("3. noche");
		System.out.print("Seleccione un turno: ");

		String opcion = scanner.nextLine().trim();

		if (opcion.equals("1")) {
			return "mañana";
		} else if (opcion.equals("2")) {
			return "tarde";
		} else if (opcion.equals("3")) {
			return "noche";
		}

		return "";
	}
}
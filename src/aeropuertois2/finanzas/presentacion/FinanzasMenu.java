package aeropuertois2.finanzas.presentacion;

import java.util.Scanner;

public class FinanzasMenu {

	public void iniciar(Scanner scanner) {
		boolean salir = false;

		while (!salir) {
			System.out.println("\n==============================================================");
			System.out.println("        SISTEMA FINANCIERO DEL AEROPUERTO (MÓDULO)");
			System.out.println("==============================================================");
			System.out.println("1. Gestión de Cuentas Bancarias");
			System.out.println("2. Gestión Contable y Registros");
			System.out.println("3. Gestión de Nóminas");
			System.out.println("4. Operaciones Financieras");
			System.out.println("5. Salir al Menú General del Aeropuerto");
			System.out.print("Seleccione un subsistema: ");

			String opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				new CuentaBancariaMenu().iniciar();
				break;
			case "2":
				new ContabilidadMenu().iniciar();
				break;
			case "3":
				new NominaMenu().iniciar();
				break;
			case "4":
				new OperacionesMenu().iniciar();
				break;
			case "5":
				salir = true;
				break;
			default:
				System.out.println("Opción no válida.");
			}
		}
	}
}
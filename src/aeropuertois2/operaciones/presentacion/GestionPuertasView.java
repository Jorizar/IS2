package aeropuertois2.operaciones.presentacion;

import java.util.Scanner;

public class GestionPuertasView {

	private final Scanner scanner;

	public GestionPuertasView(Scanner scanner) {
		this.scanner = scanner;
	}

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	public void mostrarError(String error) {
		System.err.println("ERROR: " + error);
	}

	public int leerEntero(String mensaje) {
		System.out.print(mensaje);
		while (!scanner.hasNextInt()) {
			System.out.print("Entrada no válida. Por favor, introduce un número: ");
			scanner.next();
		}
		int valor = scanner.nextInt();
		scanner.nextLine();
		return valor;
	}

	public String leerCadena(String mensaje) {
		System.out.print(mensaje);
		return scanner.nextLine().trim();
	}

	public boolean leerConfirmacion(String mensaje) {
		System.out.print(mensaje + " (s/n): ");
		String respuesta = scanner.nextLine().trim().toLowerCase();
		return respuesta.equals("s");
	}
}
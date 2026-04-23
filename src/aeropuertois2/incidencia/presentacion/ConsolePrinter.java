package aeropuertois2.incidencia.presentacion;

import java.time.format.DateTimeFormatter;
import aeropuertois2.incidencia.dominio.Incidencia;

public class ConsolePrinter {

	private ConsolePrinter() {
	}

	public static void printTitulo(String titulo) { // comun para todos?
		System.out.println();
		System.out.println("==============================================================");
		System.out.println(titulo);
		System.out.println("==============================================================");
	}

	public static void printResumenIncidencia(Incidencia incidencia) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); // formato de fecha

		System.out.println("\n--- CREAR NUEVA INCIDENCIA ---");
		System.out.println("ID:          " + incidencia.getIdIncidencia());
		System.out.println("Tipo:        " + incidencia.getTipo().toDatabaseValue());
		System.out.println("Estado:      " + incidencia.getEstado().toDatabaseValue());
		System.out.println("Fecha:       " + incidencia.getFecha().format(formatter));
		System.out.println("Descripción: " + incidencia.getDescrip());
		System.out.println("Causa:       " + incidencia.getCausa());
		System.out.println("--------------------------------------------------------");
	}

}

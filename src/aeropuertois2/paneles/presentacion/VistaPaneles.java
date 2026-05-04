package aeropuertois2.paneles.presentacion;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import aeropuertois2.paneles.modelo.Aviso;
import aeropuertois2.personal.presentacion.ConsolePrinter;

public class VistaPaneles {

    private ControladorPaneles controlador;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public VistaPaneles() {
        this.controlador = new ControladorPaneles();
    }

    public void mostrarAvisos() {
    	List<Aviso> avisos = controlador.obtenerAvisos();

        System.out.println("\n=====================================");
        System.out.println("         PANEL DE AVISOS");
        System.out.println("=====================================");

        if (avisos.isEmpty()) {
            System.out.println("No hay avisos disponibles.");
        } else {
            for (Aviso aviso : avisos) {
                System.out.println("-------------------------------------");
                System.out.println("¡AVISO!   "  + aviso.getMensaje());
                System.out.print("Fecha : " + aviso.getFecha().format(FORMATTER));
            }
            System.out.println("-------------------------------------");
        }
        System.out.println("=====================================\n");
    }
    
    public void iniciar(Scanner scanner) {
    	boolean salir = false;
        while (!salir) {
            ConsolePrinter.printTitulo("Gestion de Incidencias - Nuevo Registro de Incidencia");
            System.out.println("1. Mostrar panel de avisos");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1" -> {
                	mostrarAvisos();
                	salir = true;
                }
                case "2" -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
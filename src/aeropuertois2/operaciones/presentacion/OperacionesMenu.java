package aeropuertois2.operaciones.presentacion;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.operaciones.aplicaciones.GestionPuertasService;
import aeropuertois2.operaciones.controlador.GestionPuertasController;


import java.util.Scanner;

public class OperacionesMenu {

    public void iniciar(Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=====================================");
            System.out.println("      MÓDULO DE OPERACIONES");
            System.out.println("=====================================");
            System.out.println("1. Gestión de Puertas");
            System.out.println("2. Volver al menú principal");
            System.out.println("=====================================");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();

            switch (opcion) {
            	case "1" -> {
            		
                GestionPuertasController controlador = new GestionPuertasController();
                controlador.iniciar(scanner); 
            	}
                case "2" -> {
                    System.out.println("Saliendo de Operaciones...");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
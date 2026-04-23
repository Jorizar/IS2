package aeropuertois2.incidencia.presentacion;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.incidencia.aplicaciones.IncidenciaService;
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

    public void iniciar() {
        try (Scanner scanner = new Scanner(System.in)) {
            ConsolePrinter.printTitulo("Gestion de Incidencias - GS Airport");
            
            System.out.println("1. Registrar nueva incidencia: ");
            ejecutarCrearIncidencia(scanner);
            //System.out.print("Seleccione una opción: ");
            
            //String opcion = scanner.nextLine();
            
            //if (opcion.equals("1")) 
                //ejecutarCrearIncidencia(scanner);
            
        }
    }

    private void ejecutarCrearIncidencia(Scanner scanner) {
        ConsolePrinter.printTitulo("Nuevo Registro de Incidencia");

        try {
            System.out.print("Introduzca ID (9 digitos): ");
            String id = scanner.nextLine();

            tipoIncidencia tipo = seleccionarTipo(scanner);

            System.out.print("Descripción del suceso (max 150 caracteres.): ");
            String descrip = scanner.nextLine();

            System.out.print("Causa aparente (max 100 caracteres.): ");
            String causa = scanner.nextLine();

            incidenciaService.crearIncidencia(id, tipo, descrip, causa);
            
            System.out.println("\n[ÉXITO] La incidencia ha sido volcada en la base de datos.");

        } catch (ValidationException e) {
            System.out.println("ERROR de validacion: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ERROR de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }
    }

    private tipoIncidencia seleccionarTipo(Scanner scanner) {
        System.out.println("Seleccione el tipo:");
        System.out.println("1. General");
        System.out.println("2. Personal");
        String op = scanner.nextLine();
        
        return op.equals("2") ? tipoIncidencia.PERSONAL : tipoIncidencia.GENERAL;
    }
}
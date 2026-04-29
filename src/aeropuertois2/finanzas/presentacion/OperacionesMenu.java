package aeropuertois2.finanzas.presentacion;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.controlador.OperacionesController;

import java.sql.SQLException;
import java.util.Scanner;

public class OperacionesMenu {
    
    private final OperacionesController ctrl;

    public OperacionesMenu() {
        this.ctrl = new OperacionesController();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- OPERACIONES FINANCIERAS ---");
            System.out.println("1. Realizar Transferencia Bancaria");
            System.out.println("2. Volver al menú de finanzas");
            System.out.print("Seleccione opción: ");

            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                ejecutarTransferencia(scanner);
            } else if (opcion.equals("2")) {
                salir = true;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private void ejecutarTransferencia(Scanner scanner) {
        try {
            System.out.print("IBAN de origen: ");
            String ibanOrigen = scanner.nextLine();
            
            System.out.print("IBAN de destino: ");
            String ibanDestino = scanner.nextLine();
            
            System.out.print("Monto a transferir (€): ");
            double monto = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Concepto de la transferencia: ");
            String concepto = scanner.nextLine();

            ctrl.transferir(ibanOrigen, ibanDestino, monto, concepto);
            
            System.out.println("\n[ÉXITO] Transferencia realizada y registrada en contabilidad.");

        } catch (ValidationException | SQLException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR] El monto debe ser un número válido.");
        }
    }
}
package aeropuertois2.finanzas.presentacion;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.controlador.NominaController;

import java.sql.SQLException;
import java.util.Scanner;

public class NominaMenu {
    private final NominaController ctrl;

    public NominaMenu() {
        this.ctrl = new NominaController();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- GESTIÓN DE NÓMINAS ---");
        
        try {
            System.out.print("ID del Empleado: ");
            String idEmp = scanner.nextLine();
            
            System.out.print("IBAN de la cuenta de origen: ");
            String iban = scanner.nextLine();
            
            System.out.print("Salario Bruto (€): ");
            double bruto = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Retenciones IRPF/Seg. Soc (€): ");
            double ret = Double.parseDouble(scanner.nextLine());

            ctrl.emitirNomina(idEmp, iban, bruto, ret);
            
            System.out.println("\n[ÉXITO] Nómina emitida.");

        } catch (ValidationException | SQLException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR] Importe numérico inválido.");
        }
    }
}
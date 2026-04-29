package aeropuertois2.finanzas.presentacion;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.controlador.ContabilidadController;
import aeropuertois2.finanzas.dominio.RegistroContable;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ContabilidadMenu {
    
    private final ContabilidadController ctrl;

    public ContabilidadMenu() {
        this.ctrl = new ContabilidadController();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MÓDULO DE CONTABILIDAD ---");
            System.out.println("1. Nuevo Registro Contable");
            System.out.println("2. Modificar Registro Existente");
            System.out.println("3. Ver Todos los Registros");
            System.out.println("4. Volver");
            System.out.print("Seleccione opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ejecutarNuevoRegistro(scanner);
                    break;
                case "2":
                    ejecutarModificarRegistro(scanner);
                    break;
                case "3":
                    ejecutarVerRegistros();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void ejecutarNuevoRegistro(Scanner scanner) {
        try {
            System.out.print("Cuenta Contable: ");
            String cuenta = scanner.nextLine();
            System.out.print("Concepto: ");
            String concepto = scanner.nextLine();
            System.out.print("Tipo (INGRESO/EGRESO/TRANSFERENCIA): ");
            String tipo = scanner.nextLine().toUpperCase();
            System.out.print("Monto (€): ");
            double monto = Double.parseDouble(scanner.nextLine());

            ctrl.crearRegistro(cuenta, concepto, tipo, monto);
            System.out.println("\n[ÉXITO] Registro contable guardado correctamente.");
        } catch (ValidationException | SQLException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR] El monto debe ser numérico.");
        }
    }

    private void ejecutarModificarRegistro(Scanner scanner) {
        try {
            System.out.print("ID del Registro a modificar: ");
            String id = scanner.nextLine();
            System.out.print("Nueva Cuenta Contable: ");
            String cuenta = scanner.nextLine();
            System.out.print("Nuevo Concepto: ");
            String concepto = scanner.nextLine();
            System.out.print("Nuevo Tipo (INGRESO/EGRESO/TRANSFERENCIA): ");
            String tipo = scanner.nextLine().toUpperCase();
            System.out.print("Nuevo Monto (€): ");
            double monto = Double.parseDouble(scanner.nextLine());

            ctrl.modificarRegistro(id, cuenta, concepto, tipo, monto);
            System.out.println("\n[ÉXITO] Registro contable modificado correctamente.");
        } catch (ValidationException | SQLException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR] El monto debe ser numérico.");
        }
    }

    private void ejecutarVerRegistros() {
        System.out.println("\n--- LISTADO DE REGISTROS CONTABLES ---");
        try {
            List<RegistroContable> registros = ctrl.obtenerTodosRegistros();
            
            if (registros.isEmpty()) {
                System.out.println("No hay registros contables en el sistema.");
            } else {
                System.out.printf("%-12s | %-26s | %-15s | %-10s | %-10s%n", 
                                  "ID", "CUENTA", "TIPO", "MONTO (€)", "BALANCE");
                System.out.println("----------------------------------------------------------------------------------");
                for (RegistroContable r : registros) {
                    System.out.printf("%-12s | %-26s | %-15s | %-10.2f | %-10s%n", 
                            r.getIdRegistro(), 
                            r.getCuentaContable(), 
                            r.getTipoOperacion(), 
                            r.getMonto(), 
                            r.isEstadoBalance() ? "OK" : "DESCUADRE");
                }
            }
        } catch (SQLException e) {
            System.out.println("\n[ERROR DE BASE DE DATOS] No se pudieron cargar los registros: " + e.getMessage());
        }
    }
}
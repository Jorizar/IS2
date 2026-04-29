package aeropuertois2.finanzas.presentacion;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.aplicaciones.ContabilidadService;
import aeropuertois2.finanzas.aplicaciones.CuentaBancariaService;
import aeropuertois2.finanzas.controlador.CuentaBancariaController;
import aeropuertois2.finanzas.infrastructura.CuentaBancariaDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class CuentaBancariaMenu {
    
    private final CuentaBancariaController ctrl;

    public CuentaBancariaMenu() {
        this.ctrl = new CuentaBancariaController();
        
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n==============================================================");
            System.out.println("   Módulo de Finanzas - Gestión de Cuentas Bancarias");
            System.out.println("==============================================================");
            System.out.println("1. Dar de alta una nueva Cuenta Bancaria");
            System.out.println("2. Validar Cuenta Pendiente con Banco");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ejecutarCrearCuenta(scanner);
                    break;
                case "2":
                    ejecutarValidarCuenta(scanner);
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void ejecutarCrearCuenta(Scanner scanner) {
        System.out.println("\n--- ALTA DE CUENTA BANCARIA ---");
        try {
            System.out.print("Introduzca IBAN (Ej. ES12...): ");
            String iban = scanner.nextLine();

            System.out.print("Introduzca Nombre del Banco: ");
            String banco = scanner.nextLine();

            System.out.print("Introduzca Sucursal: ");
            String sucursal = scanner.nextLine();

            System.out.print("Introduzca Tipo de Cuenta (ej. Corriente): ");
            String tipo = scanner.nextLine();

            System.out.print("Introduzca Moneda (ej. EUR): ");
            String moneda = scanner.nextLine();

            System.out.print("Introduzca Saldo Inicial: ");
            double saldo = Double.parseDouble(scanner.nextLine());

            ctrl.crearCuenta(iban, banco, sucursal, tipo, moneda, saldo);
            System.out.println("\n[ÉXITO] La cuenta se ha guardado en la base de datos (Estado: PENDIENTE).");

        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR FORMATO] El saldo debe ser un número válido.");
        } catch (ValidationException e) {
            System.out.println("\n[ERROR DE VALIDACIÓN] " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("\n[ERROR DE BASE DE DATOS] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO] " + e.getMessage());
        }
    }

    private void ejecutarValidarCuenta(Scanner scanner) {
        System.out.println("\n--- VALIDACIÓN BANCARIA DE CUENTA PENDIENTE ---");
        try {
            System.out.print("Introduzca el IBAN a validar: ");
            String iban = scanner.nextLine();

            System.out.print("Usuario del Gestor del Banco: ");
            String usr = scanner.nextLine();

            System.out.print("Contraseña del Banco: ");
            String pass = scanner.nextLine();

            boolean exito = ctrl.validarCuentaConBanco(iban, usr, pass);

            if (exito) {
                System.out.println("\n[ÉXITO] ¡Cuenta validada correctamente con el banco! Estado actualizado a VALIDADA.");
            } else {
                System.out.println("\n[AVISO] Credenciales incorrectas. La cuenta sigue en estado PENDIENTE.");
            }

        } catch (ValidationException e) {
            System.out.println("\n[ERROR DE VALIDACIÓN] " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("\n[ERROR DE BASE DE DATOS] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERROR INESPERADO] " + e.getMessage());
        }
    }
}
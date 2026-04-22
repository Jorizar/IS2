package aeropuertois2.inicio.presentacion;

import aeropuertois2.inicio.controladores.InicioController;

import java.util.Scanner;

public class InicioMenu {

    private final InicioController inicioController;

    public InicioMenu() {
        this.inicioController = new InicioController();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarPantallaInicio();
            System.out.print("Seleccione un departamento: ");
            String opcion = scanner.nextLine();
            continuar = inicioController.procesarOpcion(opcion, scanner);
        }
    }

    private void mostrarPantallaInicio() {
        System.out.println();
        System.out.println("==============================================================");
        System.out.println("           SISTEMA AEROPORTUARIO - AEROPUERTOIS2");
        System.out.println("==============================================================");
        System.out.println("Departamentos disponibles:");
        System.out.println("1. Personal");
        System.out.println("2. Financiera");
        System.out.println("3. Incidencias");
        System.out.println("4. Operaciones");
        System.out.println("5. Paneles");
        System.out.println("6. Seguridad");
        System.out.println("7. Vuelos");
        System.out.println("8. Salir");
        System.out.println("==============================================================");
    }
}
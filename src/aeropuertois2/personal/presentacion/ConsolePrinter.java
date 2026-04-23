package aeropuertois2.personal.presentacion;

import aeropuertois2.personal.dominio.Empleado;

import java.util.List;

public final class ConsolePrinter {

    private ConsolePrinter() {
    }

    public static void printTitulo(String titulo) {
        System.out.println();
        System.out.println("==============================================================");
        System.out.println(titulo);
        System.out.println("==============================================================");
    }

    public static void printEmpleados(List<Empleado> empleados) {
        if (empleados == null || empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
            return;
        }

        System.out.printf("%-12s %-30s %-15s %-22s %-10s%n",
                "DNI", "NOMBRE", "FUNCIÓN", "ROL", "TURNO");
        System.out.println("-------------------------------------------------------------------------------------");

        for (Empleado e : empleados) {
            System.out.printf("%-12s %-30s %-15s %-22s %-10s%n",
                    e.getId(),
                    e.getNombre(),
                    e.getFuncion().toDatabaseValue(),
                    e.getRol().toDatabaseValue(),
                    e.getTurno().toDatabaseValue());
        }
    }
}

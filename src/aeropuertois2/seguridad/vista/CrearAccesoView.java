package aeropuertois2.seguridad.vista;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import aeropuertois2.seguridad.controller.PermisoAccesoController;

public class CrearAccesoView {

    private Scanner sc = new Scanner(System.in);
    private PermisoAccesoController controller = new PermisoAccesoController();

    public void mostrarFormulario() {

        try {
            System.out.println("=== CREAR ACCESO ZONA RESTRINGIDA ===");

  
            int idUsuario;
            while (true) {
                System.out.print("ID Usuario: ");
                idUsuario = sc.nextInt();

                if (controller.existeUsuario(idUsuario)) {
                    break;
                } else {
                    System.out.println("Usuario no existe. Inténtalo de nuevo.");
                }
            }

        
            int idZona;
            while (true) {
                System.out.print("ID Zona: ");
                idZona = sc.nextInt();

                if (controller.existeZona(idZona)) {
                    break;
                } else {
                    System.out.println("Zona no válida. Inténtalo de nuevo.");
                }
            }

            sc.nextLine(); 

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            Date fechaInicio;
            while (true) {
                try {
                    System.out.print("Fecha inicio (yyyy-MM-dd): ");
                    String fInicioStr = sc.nextLine();
                    fechaInicio = sdf.parse(fInicioStr);
                    break;
                } catch (Exception e) {
                    System.out.println("Formato incorrecto. Usa yyyy-MM-dd y/o una fecha válida.");
                }
            }

            Date fechaFin;
            while (true) {
                try {
                    System.out.print("Fecha fin (yyyy-MM-dd): ");
                    String fFinStr = sc.nextLine();
                    fechaFin = sdf.parse(fFinStr);

                    if (fechaFin.before(fechaInicio)) {
                        System.out.println("La fecha fin no puede ser anterior a la fecha inicio.");
                    } else {
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("Formato incorrecto. Usa yyyy-MM-dd y una fecha válida.");
                }
            }

            
            controller.crearAcceso(idUsuario, idZona, fechaInicio, fechaFin);

        } catch (Exception e) {
            System.out.println("Error en la entrada de datos: " + e.getMessage());
        }
    }
}
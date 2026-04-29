package aeropuertois2.paneles.presentacion;

import java.time.format.DateTimeFormatter;
import java.util.List;
import aeropuertois2.paneles.controladores.ControladorPaneles;
import aeropuertois2.paneles.dominio.Aviso;

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
}
package aeropuertois2.paneles.aplicaciones;

import java.time.LocalDateTime;
import java.util.List;

import aeropuertois2.paneles.dominio.Aviso;
import aeropuertois2.paneles.infraestructura.AvisoDao;

public class ServicioPaneles {

    private AvisoDao repositorio;

    public ServicioPaneles(AvisoDao repositorio) {
        this.repositorio = repositorio;
    }

    public void crearAviso(String mensaje, String tipo, String vuelo) {
        Aviso aviso = new Aviso("0", LocalDateTime.now(), mensaje);
        repositorio.guardar(aviso);
    }

    public List<Aviso> obtenerAvisos() {
        return repositorio.obtenerAvisos();
    }
}

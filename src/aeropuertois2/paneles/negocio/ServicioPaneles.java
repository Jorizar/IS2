package aeropuertois2.paneles.negocio;

import java.time.LocalDateTime;
import java.util.List;

import aeropuertois2.paneles.infraestructura.AvisoDao;
import aeropuertois2.paneles.modelo.Aviso;

public class ServicioPaneles {

    private AvisoDao repositorio;

    public ServicioPaneles(AvisoDao repositorio) {
        this.repositorio = repositorio;
    }

    public void crearAviso(String id, LocalDateTime fecha, String mensaje) {
        Aviso aviso = new Aviso(id, fecha, mensaje);
        repositorio.guardar(aviso);
    }

    public List<Aviso> obtenerAvisos() {
        return repositorio.obtenerAvisos();
    }
}

package aeropuertois2.seguridad.modelo;

public class ZonaRestringida {

    private int idZonaRestringida;
    private String nombreZona;
    private int nivelSeguridad;
    private String ubicacion;

    public ZonaRestringida(int idZonaRestringida, String nombreZona, int nivelSeguridad, String ubicacion) {
        this.idZonaRestringida = idZonaRestringida;
        this.nombreZona = nombreZona;
        this.nivelSeguridad = nivelSeguridad;
        this.ubicacion = ubicacion;
    }

    public int getIdZonaRestringida() {
        return idZonaRestringida;
    }

    public void setIdZonaRestringida(int idZonaRestringida) {
        this.idZonaRestringida = idZonaRestringida;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public int getNivelSeguridad() {
        return nivelSeguridad;
    }

    public void setNivelSeguridad(int nivelSeguridad) {
        this.nivelSeguridad = nivelSeguridad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}


package aeropuertois2.vuelo.transfers;

/**
 * Patrón DTO
 * Transporta datos de vuelo entre capas
 */
public class TransferVuelo {
    private String idVuelo;
    private String origen;
    private String destino;
    private String aerolinea;
    
    public TransferVuelo(String id, String origen, String destino, String aerolinea) {
        this.idVuelo = id;
        this.origen = origen;
        this.destino = destino;
        this.aerolinea = aerolinea;
    }
    
    public String getIdVuelo() { return idVuelo; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public String getAerolinea() { return aerolinea; }
}
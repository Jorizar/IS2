


package aeropuertois2.vuelo.transfers;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Patrón DTO
  * Transporta datos calendarios entre capas
 */

public class TransferCalendario {
	    private LocalDate fechaInicio;
	    private LocalDate fechaFin;
	    private LocalTime horaSalida;
	    private LocalTime horaLlegada;
	    private String vueloId;
	    
	    public TransferCalendario(LocalDate inicio, LocalDate fin, 
	                          LocalTime salida, LocalTime llegada, String vueloId) {
	        this.fechaInicio = inicio;
	        this.fechaFin = fin;
	        this.horaSalida = salida;
	        this.horaLlegada = llegada;
	        this.vueloId = vueloId;
	    }
	    
	    public LocalDate getFechaInicio() { return fechaInicio; }
	    public LocalDate getFechaFin() { return fechaFin; }
	    public LocalTime getHoraSalida() { return horaSalida; }
	    public LocalTime getHoraLlegada() { return horaLlegada; }
	    public String getVueloId( ) {return vueloId;}
}

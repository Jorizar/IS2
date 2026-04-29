package aeropuertois2.finanzas.dominio;

import java.time.LocalDate;

public class Nomina {
    private String idNomina;
    private String idEmpleado;
    private String ibanCuenta;
    private double importeBruto;
    private double retenciones;
    private double importeNeto;
    private LocalDate fechaEmision;
    private String estado; // "PENDIENTE", "PAGADA"

    public Nomina() {}

    public Nomina(String idNomina, String idEmpleado, String ibanCuenta, double bruto, double retenciones, String estado) {
        this.idNomina = idNomina;
        this.idEmpleado = idEmpleado;
        this.ibanCuenta = ibanCuenta;
        this.importeBruto = bruto;
        this.retenciones = retenciones;
        this.importeNeto = bruto - retenciones;
        this.fechaEmision = LocalDate.now();
        this.estado = estado;
    }

    // Getters y Setters
    public String getIdNomina() { return idNomina; }
    public void setIdNomina(String idNomina) { this.idNomina = idNomina; }
    public String getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(String idEmpleado) { this.idEmpleado = idEmpleado; }
    public String getIbanCuenta() { return ibanCuenta; }
    public void setIbanCuenta(String iban) { this.ibanCuenta = iban; }
    public double getImporteBruto() { return importeBruto; }
    public double getImporteNeto() { return importeNeto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getFechaEmision() { return this.fechaEmision; }
}
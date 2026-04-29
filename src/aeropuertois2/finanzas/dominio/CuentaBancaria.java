package aeropuertois2.finanzas.dominio;

public class CuentaBancaria {
    private String iban;
    private String nombreBanco;
    private String sucursal;
    private String tipoCuenta;
    private String moneda;
    private double saldo;
    private String estadoValidacion; // "PENDIENTE" o "VALIDADA"
    private Banco bancoAsociado;

    public CuentaBancaria() {}

    public CuentaBancaria(String iban, String nombreBanco, String sucursal, String tipoCuenta, String moneda, double saldo, String estadoValidacion, Banco bancoAsociado) {
        this.iban = iban;
        this.nombreBanco = nombreBanco;
        this.sucursal = sucursal;
        this.tipoCuenta = tipoCuenta;
        this.moneda = moneda;
        this.saldo = saldo;
        this.estadoValidacion = estadoValidacion;
        this.bancoAsociado = bancoAsociado;
    }

    public boolean validarConBanco(String usrGestor, String passGestor) {
        if (bancoAsociado != null && bancoAsociado.autenticarGestor(usrGestor, passGestor)) {
            this.estadoValidacion = "VALIDADA";
            return true;
        }
        return false;
    }

    // Getters y Setters
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
    public String getNombreBanco() { return nombreBanco; }
    public void setNombreBanco(String nombreBanco) { this.nombreBanco = nombreBanco; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public String getEstadoValidacion() { return estadoValidacion; }
    public void setEstadoValidacion(String estadoValidacion) { this.estadoValidacion = estadoValidacion; }
    public Banco getBancoAsociado() { return bancoAsociado; }
    public void setBancoAsociado(Banco bancoAsociado) { this.bancoAsociado = bancoAsociado; }
}
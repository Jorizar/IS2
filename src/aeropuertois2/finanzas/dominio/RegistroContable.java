package aeropuertois2.finanzas.dominio;

public class RegistroContable {
	private String idRegistro;
	private String cuentaContable;
	private String concepto;
	private String tipoOperacion; // INGRESO, EGRESO, TRANSFERENCIA
	private double monto;
	private boolean estadoBalance;

	public RegistroContable() {
	}

	public RegistroContable(String idRegistro, String cuentaContable, String concepto, String tipoOperacion,
			double monto, boolean estadoBalance) {
		this.idRegistro = idRegistro;
		this.cuentaContable = cuentaContable;
		this.concepto = concepto;
		this.tipoOperacion = tipoOperacion;
		this.monto = monto;
		this.estadoBalance = estadoBalance;
	}

	// Getters y Setters
	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public boolean isEstadoBalance() {
		return estadoBalance;
	}

	public void setEstadoBalance(boolean estadoBalance) {
		this.estadoBalance = estadoBalance;
	}
}
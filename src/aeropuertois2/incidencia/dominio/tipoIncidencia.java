package aeropuertois2.incidencia.dominio;

public enum tipoIncidencia {

	GENERAL, PERSONAL;

	public static tipoIncidencia fromDatabase(String value) {
		return tipoIncidencia.valueOf(value.toUpperCase()); // de sql a java
	}

	public String toDatabaseValue() {
		return name().substring(0, 1) + name().substring(1).toLowerCase(); // prim mayus resto minus
	}
}

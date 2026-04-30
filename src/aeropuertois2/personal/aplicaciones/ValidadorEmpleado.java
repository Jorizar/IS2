package aeropuertois2.personal.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;

import java.util.Locale;
import java.util.regex.Pattern;

public final class ValidadorEmpleado {

	private static final Pattern PATRON_DNI = Pattern.compile("^[0-9]{8}[A-Z]$");
	private static final Pattern PATRON_NOMBRE_BUSQUEDA = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,60}$");

	private ValidadorEmpleado() {
	}

	public static void validarBusqueda(String criterio) throws ValidationException {
		String valor = normalizarCriterio(criterio);

		if (estaVacio(valor)) {
			throw new ValidationException("Debe introducir un nombre o un DNI.");
		}

		boolean esDni = esDni(valor);
		boolean esNombre = PATRON_NOMBRE_BUSQUEDA.matcher(valor).matches();

		if (!esDni && !esNombre) {
			throw new ValidationException(
					"Datos no válidos. Introduzca un nombre o un DNI válido de 8 números y una letra.");
		}
	}

	public static void validarDniLogin(String dni) throws ValidationException {
		if (!esDni(dni)) {
			throw new ValidationException("El DNI introducido no es válido.");
		}
	}

	public static boolean esDni(String criterio) {
		return PATRON_DNI.matcher(normalizarDni(criterio)).matches();
	}

	public static String normalizarDni(String dni) {
		return dni == null ? "" : dni.trim().toUpperCase(Locale.ROOT);
	}

	public static String normalizarCriterio(String criterio) {
		return criterio == null ? "" : criterio.trim().replaceAll("\\s+", " ");
	}

	public static boolean estaVacio(String texto) {
		return texto == null || texto.trim().isEmpty();
	}
}
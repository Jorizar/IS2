package aeropuertois2.personal.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;

import java.util.regex.Pattern;

public final class ValidadorEmpleado {

	private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,29}$");
	private static final Pattern PATRON_DNI = Pattern.compile("^[0-9]{8}[A-Z]$");

	private ValidadorEmpleado() {
	}

	public static void validarBusqueda(String criterio) throws ValidationException {
		if (criterio == null || criterio.isBlank()) {
			throw new ValidationException("Debe introducir un nombre o un DNI.");
		}

		String valor = criterio.trim();
		boolean esNombre = PATRON_NOMBRE.matcher(valor).matches();
		boolean esDni = PATRON_DNI.matcher(valor).matches();

		if (!esNombre && !esDni) {
			throw new ValidationException(
					"Datos no válidos. Introduzca un nombre válido (máx. 30 caracteres, formato NombreApellido1Apellido2) o un DNI válido (8 números y una letra mayúscula).");
		}
	}

	public static void validarDniLogin(String dni) throws ValidationException {
		if (dni == null || !PATRON_DNI.matcher(dni.trim()).matches()) {
			throw new ValidationException("El DNI introducido no es válido.");
		}
	}

	public static boolean esDni(String criterio) {
		return criterio != null && PATRON_DNI.matcher(criterio.trim()).matches();
	}
}
package aeropuertois2.incidencia.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;

import java.util.regex.Pattern;

public class ValidadorIncidencia {
	private static final Pattern PATRON_ID = Pattern.compile("^[0-9]{9}$"); // exactamente 9 digitos

	private ValidadorIncidencia() {
	}

	// validar id
	public static void validarId(String id) throws ValidationException {
		if (id == null) // obligatorio
			throw new ValidationException("Es necesario introducir un id.  Recuerde, debe tener exactamante 9 digitos");
		else if (!PATRON_ID.matcher(id.trim()).matches())
			throw new ValidationException("El ID debe tener exactamente 9 digitos (0-9).");
	}

	// validar descripcion
	public static void validarDescrip(String descrip) throws ValidationException {
		if (descrip == null || descrip.trim().isBlank()) // obligatorio
			throw new ValidationException("Es necesario introducir una descripcion.");
		else if (descrip.length() > 200)
			throw new ValidationException("La descripcion no puede superar los 200 caracteres.");
	}

	// validar causa
	public static void validarCausa(String causa) throws ValidationException {
		if (causa.length() > 100)
			throw new ValidationException("La causa no puede superar los 100 caracteres.");
	}

	public static void validarNuevaIncidencia(String id, String descrip, String causa) throws ValidationException {
		validarId(id);
		validarDescrip(descrip);
		validarCausa(causa);
	}
}
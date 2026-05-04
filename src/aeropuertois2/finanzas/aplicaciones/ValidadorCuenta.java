package aeropuertois2.finanzas.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;

public class ValidadorCuenta {

	private ValidadorCuenta() {
	}

	public static void validarIban(String iban) throws ValidationException {
		if (iban == null || iban.trim().isBlank()) {
			throw new ValidationException("El IBAN es obligatorio.");
		}
	}

	public static void validarBanco(String banco) throws ValidationException {
		if (banco == null || banco.trim().isBlank()) {
			throw new ValidationException("El nombre del banco es obligatorio.");
		} else if (banco.length() > 50) {
			throw new ValidationException("El nombre del banco no puede superar los 50 caracteres.");
		}
	}

	public static void validarSaldo(double saldo) throws ValidationException {
		if (saldo < 0) {
			throw new ValidationException("El saldo inicial no puede ser negativo.");
		}
	}

	public static void validarNuevaCuenta(String iban, String banco, double saldo) throws ValidationException {
		validarIban(iban);
		validarBanco(banco);
		validarSaldo(saldo);
	}
}
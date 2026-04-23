package aeropuertois2.comun.utilidades;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtil {

	private HashUtil() {
	}

	public static String sha256(String texto) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));

			StringBuilder hex = new StringBuilder();
			for (byte b : hash) {
				String parte = Integer.toHexString(0xff & b);
				if (parte.length() == 1) {
					hex.append('0');
				}
				hex.append(parte);
			}
			return hex.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No se pudo generar el hash SHA-256", e);
		}
	}
}
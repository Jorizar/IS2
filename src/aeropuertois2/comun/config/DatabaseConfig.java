package aeropuertois2.comun.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class DatabaseConfig {
	private final String url;
	private final String username;
	private final String password;

	public DatabaseConfig(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DatabaseConfig load() {
		Properties properties = new Properties();

		try {
			InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties");

			if (input != null) {
				try {
					properties.load(input);
					return construirConfig(properties);
				} finally {
					input.close();
				}
			}

			Path[] rutasPosibles = new Path[] {
					Path.of("src/resources/config.properties"),
					Path.of("src/main/resources/config.properties"),
					Path.of("target/classes/config.properties"),
					Path.of("config.properties")
			};

			for (Path ruta : rutasPosibles) {
				if (Files.exists(ruta)) {
					InputStream fileInput = null;
					try {
						fileInput = Files.newInputStream(ruta);
						properties.load(fileInput);
						return construirConfig(properties);
					} finally {
						if (fileInput != null) {
							fileInput.close();
						}
					}
				}
			}

			throw new IllegalStateException("No se encontró el archivo config.properties");

		} catch (IOException e) {
			throw new IllegalStateException("No se pudo cargar la configuración de la base de datos", e);
		}
	}

	private static DatabaseConfig construirConfig(Properties properties) {
		String url = properties.getProperty("db.url");
		String username = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");

		if (url == null || username == null || password == null) {
			throw new IllegalStateException("Faltan propiedades obligatorias en config.properties");
		}

		return new DatabaseConfig(url, username, password);
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
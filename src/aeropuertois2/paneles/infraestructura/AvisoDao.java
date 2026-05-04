package aeropuertois2.paneles.infraestructura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.paneles.modelo.Aviso;

public class AvisoDao {

	private final DatabaseConnection databaseConnection;

	public AvisoDao(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public void guardar(Aviso aviso) {
		String sql = "INSERT INTO avisos (idAviso, fecha, mensaje) VALUES (?, ?, ?)";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, aviso.getIdAviso());
			statement.setTimestamp(2, Timestamp.valueOf(aviso.getFecha()));
			statement.setString(3, aviso.getMensaje());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Error al guardar aviso", e);
		}
	}

	public List<Aviso> obtenerAvisos() {
		List<Aviso> avisos = new ArrayList<>();

		String sql = "SELECT * FROM avisos";
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				String id = rs.getString("idAviso");
				LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
				String mensaje = rs.getString("mensaje");

				Aviso aviso = new Aviso(id, fecha, mensaje);
				avisos.add(aviso);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener avisos", e);
		}
		return avisos;
	}
}

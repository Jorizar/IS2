package aeropuertois2.seguridad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;

public class UsuarioDAO {

	private final DatabaseConnection dbConnection;

	public UsuarioDAO() {
		this.dbConnection = new DatabaseConnection(DatabaseConfig.load());
	}

	public boolean existeUsuario(int idUsuario) {
		String sql = "SELECT idUsuario FROM Usuario WHERE idUsuario = ?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idUsuario);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}

		} catch (SQLException e) {
			System.out.println("Error verificar usuario: " + e.getMessage());
			return false;
		}
	}
}
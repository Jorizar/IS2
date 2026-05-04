package aeropuertois2.seguridad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.seguridad.modelo.PermisoAcceso;

public class PermisoDAO {

	private final DatabaseConnection dbConnection;

	public PermisoDAO() {
		this.dbConnection = new DatabaseConnection(DatabaseConfig.load());
	}

	public boolean insertarPermiso(PermisoAcceso p) {
		String sql = "INSERT INTO PermisoAcceso "
				+ "(fechaInicio, fechaFin, estado, Usuario_idUsuario, ZonaRestringida_idZonaRestringida) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setDate(1, new java.sql.Date(p.getFechaInicio().getTime()));
			ps.setDate(2, new java.sql.Date(p.getFechaFin().getTime()));
			ps.setString(3, p.getEstado());
			ps.setInt(4, p.getIdUsuario());
			ps.setInt(5, p.getidZonaRestringida());

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			System.out.println("Error insertar permiso: " + e.getMessage());
			return false;
		}
	}

	public boolean existePermiso(int idUsuario, int idZona, Date inicio, Date fin) {
		String sql = "SELECT 1 FROM PermisoAcceso " + "WHERE Usuario_idUsuario = ? "
				+ "AND ZonaRestringida_idZonaRestringida = ? " + "AND estado = 'Activo' " + "AND fechaFin >= CURDATE() "
				+ "AND fechaInicio <= ? AND fechaFin >= ?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idUsuario);
			ps.setInt(2, idZona);
			ps.setDate(3, new java.sql.Date(fin.getTime()));
			ps.setDate(4, new java.sql.Date(inicio.getTime()));

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}

		} catch (SQLException e) {
			System.out.println("Error verificar permiso: " + e.getMessage());
			return false;
		}
	}
}
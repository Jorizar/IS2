package aeropuertois2.operaciones.infraestructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.operaciones.dominio.Puerta;
import aeropuertois2.operaciones.dominio.ZonaTerminal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PuertaDAO {
	private final DatabaseConnection databaseConnection;

	public PuertaDAO(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public List<Puerta> obtenerPorTerminalYZona(int idTerminal, ZonaTerminal zona) throws SQLException {
		String sql = "SELECT id_puerta, numero_gate, id_terminal, zona, bloqueada "
				+ "FROM puertas WHERE id_terminal = ? AND zona = ? ORDER BY numero_gate";
		List<Puerta> puertas = new ArrayList<>();

		try (Connection conn = databaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idTerminal);
			stmt.setString(2, zona.getNombre());

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					puertas.add(mapearPuerta(rs));
				}
			}
		}
		return puertas;
	}

	public boolean crear(Puerta puerta) throws SQLException {
		String sql = "INSERT INTO puertas (numero_gate, id_terminal, zona, bloqueada) VALUES (?, ?, ?, ?)";

		try (Connection conn = databaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, puerta.getNumeroGate());
			stmt.setInt(2, puerta.getIdTerminal());
			stmt.setString(3, puerta.getZona().getNombre());
			stmt.setBoolean(4, puerta.isBloqueada());

			return stmt.executeUpdate() > 0;
		}
	}

	public boolean modificarEstado(int idPuerta, boolean bloqueada) throws SQLException {
		String sql = "UPDATE puertas SET bloqueada = ? WHERE id_puerta = ?";

		try (Connection conn = databaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setBoolean(1, bloqueada);
			stmt.setInt(2, idPuerta);

			return stmt.executeUpdate() > 0;
		}
	}

	public boolean eliminar(int idPuerta) throws SQLException {
		String sql = "DELETE FROM puertas WHERE id_puerta = ?";

		try (Connection conn = databaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPuerta);
			return stmt.executeUpdate() > 0;
		}
	}

	public boolean tieneAvionAsignado(int idPuerta) throws SQLException {
		String sql = "SELECT COUNT(*) FROM viajes WHERE id_puerta = ? AND estado IN ('Programado', 'EnCurso')";

		try (Connection conn = databaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPuerta);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}
		return false;
	}

	private Puerta mapearPuerta(ResultSet rs) throws SQLException {
		return new Puerta(rs.getInt("id_puerta"), rs.getString("numero_gate"), rs.getInt("id_terminal"),
				ZonaTerminal.fromString(rs.getString("zona")), rs.getBoolean("bloqueada"));
	}
}
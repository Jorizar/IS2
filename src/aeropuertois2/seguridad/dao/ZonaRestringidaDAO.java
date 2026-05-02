package aeropuertois2.seguridad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeropuertois2.comun.config.DatabaseConfig;
import aeropuertois2.comun.config.DatabaseConnection;

public class ZonaRestringidaDAO {

    private final DatabaseConnection dbConnection;

    public ZonaRestringidaDAO() {
        this.dbConnection = new DatabaseConnection(DatabaseConfig.load());
    }

    public boolean existeZona(int idZona) {
        String sql = "SELECT idZonaRestringida FROM ZonaRestringida WHERE idZonaRestringida = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idZona);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error verificar zona: " + e.getMessage());
            return false;
        }
    }
}
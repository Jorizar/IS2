package aeropuertois2.operaciones.infraestructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.operaciones.dominio.Terminal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TerminalDAO {
    private final DatabaseConnection databaseConnection;

    public TerminalDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<Terminal> obtenerTodas() throws SQLException {
        String sql = "SELECT id_terminal, nombre FROM terminales ORDER BY nombre";
        List<Terminal> terminales = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                terminales.add(new Terminal(
                    rs.getInt("id_terminal"),
                    rs.getString("nombre")
                ));
            }
        }
        return terminales;
    }
}
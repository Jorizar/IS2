
package aeropuertois2.vuelo.infraestructura;

import aeropuertois2.comun.config.DatabaseConnection;

import aeropuertois2.vuelo.transfers.TransferVuelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VueloDao {

    private final DatabaseConnection databaseConnection;

    public VueloDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean guardar(TransferVuelo vuelo) throws SQLException {
        String sql = """
                INSERT INTO vuelo
                (id_vuelo, origen, destino, aerolinea, activo, id_operador_creador)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vuelo.getIdVuelo());
            statement.setString(2, vuelo.getOrigen());
            statement.setString(3, vuelo.getDestino());
            statement.setString(4, vuelo.getAerolinea());
            //statement.setBoolean(5, vuelo.isActivo());
            //statement.setString(6, vuelo.getIdOperadorCreador());

            return statement.executeUpdate() > 0;
        }
    }

    public Optional<TransferVuelo> buscarPorId(String id) throws SQLException {
        String sql = """
                SELECT id_vuelo, origen, destino, aerolinea, activo, id_operador_creador
                FROM vuelo
                WHERE id_vuelo = ?
                """;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearVuelo(rs));
                }
            }
        }

        return Optional.empty();
    }

    public boolean existe(String id) throws SQLException {
        String sql = "SELECT 1 FROM vuelo WHERE id_vuelo = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        }
    }

    private TransferVuelo mapearVuelo(ResultSet rs) throws SQLException {
        TransferVuelo vuelo = new TransferVuelo(
                rs.getString("id_vuelo"),
                rs.getString("origen"),
                rs.getString("destino"),
                rs.getString("aerolinea")/* ,
                rs.getString("id_operador_creador")*/
        );

        //vuelo.setActivo(rs.getBoolean("activo"));
        return vuelo;
    }
}
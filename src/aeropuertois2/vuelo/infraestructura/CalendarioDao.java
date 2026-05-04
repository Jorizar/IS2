

package aeropuertois2.vuelo.infraestructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.vuelo.transfers.TransferCalendario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalendarioDao {

    private final DatabaseConnection databaseConnection;

    public CalendarioDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean guardar(TransferCalendario calendario) throws SQLException {
        String sql = """
                INSERT INTO calendarios
                (vuelo_id, fecha_inicio, fecha_fin, hora_salida, hora_llegada, dias)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, calendario.getVueloId());
            statement.setDate(2, Date.valueOf(calendario.getFechaInicio()));
            statement.setDate(3, Date.valueOf(calendario.getFechaFin()));
            statement.setTime(4, Time.valueOf(calendario.getHoraSalida()));
            statement.setTime(5, Time.valueOf(calendario.getHoraLlegada()));
            statement.setString(6, calendario.getDias());

            return statement.executeUpdate() > 0;
        }
    }

    public List<TransferCalendario> buscarPorVueloId(String vueloId) throws SQLException {
        String sql = """
                SELECT vuelo_id, fecha_inicio, fecha_fin, hora_salida, hora_llegada, dias
                FROM calendarios
                WHERE vuelo_id = ?
                ORDER BY fecha_inicio ASC
                """;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vueloId);

            try (ResultSet rs = statement.executeQuery()) {
                return mapearLista(rs);
            }
        }
    }

    private List<TransferCalendario> mapearLista(ResultSet rs) throws SQLException {
        List<TransferCalendario> calendarios = new ArrayList<>();

        while (rs.next()) {
            calendarios.add(mapearCalendario(rs));
        }

        return calendarios;
    }

    private TransferCalendario mapearCalendario(ResultSet rs) throws SQLException {
        return new TransferCalendario(
                rs.getDate("fecha_inicio").toLocalDate(),
                rs.getDate("fecha_fin").toLocalDate(),
                rs.getTime("hora_salida").toLocalTime(),
                rs.getTime("hora_llegada").toLocalTime(),
                rs.getString("vuelo_id"),
                rs.getString("dias")
        );
    }
}

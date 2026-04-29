package aeropuertois2.finanzas.infrastructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.finanzas.dominio.Nomina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class NominaDAO {
    private final DatabaseConnection dbConnection;

    public NominaDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void insertar(Nomina nomina) throws SQLException {
        String sql = "INSERT INTO nominas (id_nomina, id_empleado, iban_cuenta, bruto, neto, fecha_emision, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomina.getIdNomina());
            stmt.setString(2, nomina.getIdEmpleado());
            stmt.setString(3, nomina.getIbanCuenta());
            stmt.setDouble(4, nomina.getImporteBruto());
            stmt.setDouble(5, nomina.getImporteNeto());
            stmt.setDate(6, Date.valueOf(nomina.getFechaEmision()));
            stmt.setString(7, nomina.getEstado());
            stmt.executeUpdate();
        }
    }
}
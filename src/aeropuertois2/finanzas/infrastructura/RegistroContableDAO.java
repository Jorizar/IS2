package aeropuertois2.finanzas.infrastructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.finanzas.dominio.RegistroContable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegistroContableDAO {
    private final DatabaseConnection dbConnection;

    public RegistroContableDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void insertar(RegistroContable registro) throws SQLException {
        String sql = "INSERT INTO registros_contables (id_registro, cuenta_contable, concepto, tipo_operacion, monto, estado_balance) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registro.getIdRegistro());
            stmt.setString(2, registro.getCuentaContable());
            stmt.setString(3, registro.getConcepto());
            stmt.setString(4, registro.getTipoOperacion());
            stmt.setDouble(5, registro.getMonto());
            stmt.setBoolean(6, registro.isEstadoBalance());
            stmt.executeUpdate();
        }
    }

    public void actualizar(RegistroContable registro) throws SQLException {
        String sql = "UPDATE registros_contables SET cuenta_contable = ?, concepto = ?, tipo_operacion = ?, monto = ?, estado_balance = ? WHERE id_registro = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registro.getCuentaContable());
            stmt.setString(2, registro.getConcepto());
            stmt.setString(3, registro.getTipoOperacion());
            stmt.setDouble(4, registro.getMonto());
            stmt.setBoolean(5, registro.isEstadoBalance());
            stmt.setString(6, registro.getIdRegistro());
            
            if (stmt.executeUpdate() == 0) {
                throw new SQLException("No se encontró ningún registro con el ID: " + registro.getIdRegistro());
            }
        }
    }
    
    public List<RegistroContable> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM registros_contables";
        List<RegistroContable> lista = new java.util.ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                RegistroContable r = new RegistroContable();
                r.setIdRegistro(rs.getString("id_registro"));
                r.setCuentaContable(rs.getString("cuenta_contable"));
                r.setConcepto(rs.getString("concepto"));
                r.setTipoOperacion(rs.getString("tipo_operacion"));
                r.setMonto(rs.getDouble("monto"));
                r.setEstadoBalance(rs.getBoolean("estado_balance"));
                lista.add(r);
            }
        }
        return lista;
    }
}
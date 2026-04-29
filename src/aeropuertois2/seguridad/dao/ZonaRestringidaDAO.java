package aeropuertois2.seguridad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeropuertois2.seguridad.util.ConexionBD;

public class ZonaRestringidaDAO {

    public boolean existeZona(int idZona) {

        Connection conn = null;

        try {
            conn = ConexionBD.getConexion();

            String sql = "SELECT idZonaRestringida FROM ZonaRestringida WHERE idZonaRestringida=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idZona);

            ResultSet rs = ps.executeQuery();

            boolean existe = rs.next();

            rs.close();
            ps.close();

            return existe;

        } catch (SQLException e) {
            System.out.println("Error verificar zona: " + e.getMessage());
            return false;
        }
    }
}
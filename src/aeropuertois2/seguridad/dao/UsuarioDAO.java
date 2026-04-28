package aeropuertois2.seguridad.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aeropuertois2.seguridad.util.ConexionBD;

public class UsuarioDAO {

    public boolean existeUsuario(int idUsuario) {

        Connection conn = null;

        try {
            conn = ConexionBD.getConexion();

            String sql = "SELECT idUsuario FROM Usuario WHERE idUsuario=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            boolean existe = rs.next();

            rs.close();
            ps.close();

            return existe;

        } catch (SQLException e) {
            System.out.println("Error verificar usuario: " + e.getMessage());
            return false;
        }
    }
}
package aeropuertois2.seguridad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/CU_Seguridad";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConexion() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Error al conectar con la BD: " + e.getMessage());
        }

        return conn;
    }
}
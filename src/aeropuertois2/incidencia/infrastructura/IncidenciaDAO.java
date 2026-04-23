package aeropuertois2.incidencia.infrastructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.incidencia.dominio.Incidencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class IncidenciaDAO {
    private final DatabaseConnection databaseConnection; //conexion db

    public IncidenciaDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void crearIncidencia(Incidencia incidencia) throws SQLException {
        String sql = "INSERT INTO incidencias (idIncidencia, tipo, estado, fecha, descrip, causa) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) 
        {

            statement.setString(1, incidencia.getIdIncidencia()); //id
            
            statement.setString(2, incidencia.getTipo().toDatabaseValue()); //tipo
            statement.setString(3, incidencia.getEstado().toDatabaseValue());//estado
            
            statement.setTimestamp(4, Timestamp.valueOf(incidencia.getFecha())); //fecha + hora
            
            statement.setString(5, incidencia.getDescrip()); //descrip
            statement.setString(6, incidencia.getCausa()); //causa

            statement.executeUpdate(); //por tratarse de un INSERT 
        }
    }
}
package aeropuertois2.incidencia.infrastructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.incidencia.dominio.Incidencia;
import aeropuertois2.incidencia.dominio.tipoIncidencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaDAO {
	private final DatabaseConnection databaseConnection; // conexion db

	public IncidenciaDAO(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	
	// esta capa no se encarga de capturar excepciones o errores
	public void crearIncidencia(Incidencia incidencia) throws SQLException {
		// sentencia sql:
		String sql = "INSERT INTO incidencias (idIncidencia, tipo, estado, fecha, descrip, causa) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			// sustituimos los statements (?). Mapeo:
			statement.setString(1, incidencia.getIdIncidencia()); // id
			statement.setString(2, incidencia.getTipo().toDatabaseValue()); // tipo
			statement.setString(3, incidencia.getEstado().toDatabaseValue());// estado
			statement.setTimestamp(4, Timestamp.valueOf(incidencia.getFecha())); // fecha + hora; Timestamp.valueOf()
																					// para conversion (LocalDeltaTime -> DATETIME)
			statement.setString(5, incidencia.getDescrip()); // descrip
			statement.setString(6, incidencia.getCausa()); // causa
			statement.executeUpdate(); // por tratarse de un INSERT
		}
	}
	
	
	public List<Incidencia> mostrarIncidencias() throws SQLException {
        List<Incidencia> lista = new ArrayList<>();
        // sentencia sql: 
        String sql = "SELECT idIncidencia, tipo, estado, fecha, descrip, causa FROM incidencias";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) { // este caso para el SELECT

            while (resultSet.next()) //mientras haya datos: 
            {
                String id = resultSet.getString("idIncidencia");
                tipoIncidencia tipo = tipoIncidencia.valueOf(resultSet.getString("tipo").toUpperCase()); // sin el upper no coincidiria con el enum
                
                String descrip = resultSet.getString("descrip");
                String causa = resultSet.getString("causa");

                Incidencia incidencia = new Incidencia(id, tipo, descrip, causa);
                lista.add(incidencia);
            }
        }
        
        return lista;
    }
	

}
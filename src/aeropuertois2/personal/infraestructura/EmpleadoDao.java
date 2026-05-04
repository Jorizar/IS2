package aeropuertois2.personal.infraestructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.personal.dominio.Empleado;
import aeropuertois2.personal.dominio.FiltroTipo;
import aeropuertois2.personal.dominio.FuncionAeropuerto;
import aeropuertois2.personal.dominio.RolEmpleado;
import aeropuertois2.personal.dominio.Turno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoDao {

	private final DatabaseConnection databaseConnection;

	public EmpleadoDao(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public Optional<Empleado> buscarPorId(String id) throws SQLException {
		String sql = "SELECT id, password_hash, nombre, funcion, rol, turno " + "FROM empleados "
				+ "WHERE UPPER(TRIM(id)) = UPPER(TRIM(?))";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, id);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapearEmpleado(rs));
				}
			}
		}

		return Optional.empty();
	}

	public List<Empleado> listarTodos() throws SQLException {
		String sql = "SELECT id, password_hash, nombre, funcion, rol, turno " + "FROM empleados "
				+ "ORDER BY nombre ASC";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet rs = statement.executeQuery()) {

			return mapearLista(rs);
		}
	}

	public List<Empleado> buscarPorDni(String dni) throws SQLException {
		String sql = "SELECT id, password_hash, nombre, funcion, rol, turno " + "FROM empleados "
				+ "WHERE UPPER(TRIM(id)) = UPPER(TRIM(?)) " + "ORDER BY nombre ASC";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, dni);

			try (ResultSet rs = statement.executeQuery()) {
				return mapearLista(rs);
			}
		}
	}

	public List<Empleado> buscarPorNombre(String nombre) throws SQLException {
		String sql = "SELECT id, password_hash, nombre, funcion, rol, turno " + "FROM empleados "
				+ "WHERE LOWER(REPLACE(TRIM(nombre), ' ', '')) LIKE LOWER(?) " + "ORDER BY nombre ASC";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			String nombreNormalizado = nombre == null ? "" : nombre.trim().replaceAll("\\s+", "");
			statement.setString(1, "%" + nombreNormalizado + "%");

			try (ResultSet rs = statement.executeQuery()) {
				return mapearLista(rs);
			}
		}
	}

	public List<Empleado> filtrarPor(FiltroTipo tipo, String valor) throws SQLException {
		String columna;

		if (tipo == FiltroTipo.FUNCION) {
			columna = "funcion";
		} else if (tipo == FiltroTipo.ROL) {
			columna = "rol";
		} else if (tipo == FiltroTipo.TURNO) {
			columna = "turno";
		} else {
			throw new IllegalArgumentException("Tipo de filtro no válido.");
		}

		String sql = "SELECT id, password_hash, nombre, funcion, rol, turno " + "FROM empleados " + "WHERE LOWER(TRIM("
				+ columna + ")) = LOWER(TRIM(?)) " + "ORDER BY nombre ASC";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, valor);

			try (ResultSet rs = statement.executeQuery()) {
				return mapearLista(rs);
			}
		}
	}

	private List<Empleado> mapearLista(ResultSet rs) throws SQLException {
		List<Empleado> empleados = new ArrayList<Empleado>();

		while (rs.next()) {
			empleados.add(mapearEmpleado(rs));
		}

		return empleados;
	}

	private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
		return new Empleado(rs.getString("id"), rs.getString("password_hash"), rs.getString("nombre"),
				FuncionAeropuerto.fromDatabase(rs.getString("funcion")), RolEmpleado.fromDatabase(rs.getString("rol")),
				Turno.fromDatabase(rs.getString("turno")));
	}
}
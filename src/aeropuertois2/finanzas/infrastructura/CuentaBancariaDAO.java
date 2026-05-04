package aeropuertois2.finanzas.infrastructura;

import aeropuertois2.comun.config.DatabaseConnection;
import aeropuertois2.finanzas.dominio.CuentaBancaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaBancariaDAO {
	private final DatabaseConnection databaseConnection;

	public CuentaBancariaDAO(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public void insertar(CuentaBancaria cuenta) throws SQLException {
		String sql = "INSERT INTO cuentas_bancarias (iban, nombre_banco, sucursal, tipo_cuenta, moneda, saldo, estado_validacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, cuenta.getIban());
			statement.setString(2, cuenta.getNombreBanco());
			statement.setString(3, cuenta.getSucursal());
			statement.setString(4, cuenta.getTipoCuenta());
			statement.setString(5, cuenta.getMoneda());
			statement.setDouble(6, cuenta.getSaldo());
			statement.setString(7, cuenta.getEstadoValidacion());

			statement.executeUpdate();
		}
	}

	public CuentaBancaria buscarPorIban(String iban) throws SQLException {
		String sql = "SELECT * FROM cuentas_bancarias WHERE iban = ?";
		CuentaBancaria cuenta = null;

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, iban);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					cuenta = new CuentaBancaria();
					cuenta.setIban(rs.getString("iban"));
					cuenta.setNombreBanco(rs.getString("nombre_banco"));
					cuenta.setSucursal(rs.getString("sucursal"));
					cuenta.setTipoCuenta(rs.getString("tipo_cuenta"));
					cuenta.setMoneda(rs.getString("moneda"));
					cuenta.setSaldo(rs.getDouble("saldo"));
					cuenta.setEstadoValidacion(rs.getString("estado_validacion"));
				}
			}
		}
		return cuenta;
	}

	public void actualizarEstado(String iban, String nuevoEstado) throws SQLException {
		String sql = "UPDATE cuentas_bancarias SET estado_validacion = ? WHERE iban = ?";

		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, nuevoEstado);
			statement.setString(2, iban);
			statement.executeUpdate();
		}
	}

	public void actualizarSaldo(String iban, double nuevoSaldo) throws SQLException {
		String sql = "UPDATE cuentas_bancarias SET saldo = ? WHERE iban = ?";
		try (Connection connection = databaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDouble(1, nuevoSaldo);
			statement.setString(2, iban);
			statement.executeUpdate();
		}
	}
}
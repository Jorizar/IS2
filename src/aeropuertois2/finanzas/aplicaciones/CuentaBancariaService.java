package aeropuertois2.finanzas.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.dominio.Banco;
import aeropuertois2.finanzas.dominio.CuentaBancaria;
import aeropuertois2.finanzas.infrastructura.CuentaBancariaDAO;

import java.sql.SQLException;

public class CuentaBancariaService {
	private static CuentaBancariaService instancia;
	private final CuentaBancariaDAO cuentaDao;

	private CuentaBancariaService(CuentaBancariaDAO cuentaDao) {
		this.cuentaDao = cuentaDao;
	}

	public static CuentaBancariaService getInstance(CuentaBancariaDAO cuentaDao) {
		if (instancia == null) {
			instancia = new CuentaBancariaService(cuentaDao);
		}
		return instancia;
	}

	public void crearCuenta(String iban, String banco, String sucursal, String tipo, String moneda, double saldo)
			throws ValidationException, SQLException {

		ValidadorCuenta.validarNuevaCuenta(iban, banco, saldo);
		CuentaBancaria nuevaCuenta = new CuentaBancaria(iban, banco, sucursal, tipo, moneda, saldo, "PENDIENTE", null);
		cuentaDao.insertar(nuevaCuenta);
	}

	public boolean validarCuentaConBanco(String iban, String usuarioGestor, String passGestor)
			throws ValidationException, SQLException {

		ValidadorCuenta.validarIban(iban);

		CuentaBancaria cuenta = cuentaDao.buscarPorIban(iban);
		if (cuenta == null) {
			throw new ValidationException("No existe ninguna cuenta con el IBAN proporcionado.");
		}

		Banco bancoSimulado = new Banco("gestor123", cuenta.getNombreBanco(), "pass123");
		cuenta.setBancoAsociado(bancoSimulado);

		boolean esValida = cuenta.validarConBanco(usuarioGestor, passGestor);

		if (esValida) {
			cuentaDao.actualizarEstado(cuenta.getIban(), cuenta.getEstadoValidacion());
			return true;
		}
		return false;
	}
}
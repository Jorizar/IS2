package aeropuertois2.finanzas.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.dominio.CuentaBancaria;
import aeropuertois2.finanzas.infrastructura.CuentaBancariaDAO;

import java.sql.SQLException;

public class OperacionesService {
	private static OperacionesService instancia;
	private final CuentaBancariaDAO cuentaDao;
	private final ContabilidadService contabilidadService;

	private OperacionesService(CuentaBancariaDAO cuentaDao, ContabilidadService contabilidadService) {
		this.cuentaDao = cuentaDao;
		this.contabilidadService = contabilidadService;
	}

	public static OperacionesService getInstance(CuentaBancariaDAO cuentaDao, ContabilidadService contabilidadService) {
		if (instancia == null) {
			instancia = new OperacionesService(cuentaDao, contabilidadService);
		}
		return instancia;
	}

	public void realizarTransferencia(String ibanOrigen, String ibanDestino, double monto, String concepto)
			throws ValidationException, SQLException {

		if (monto <= 0)
			throw new ValidationException("El monto a transferir debe ser mayor a 0.");
		if (ibanOrigen.equals(ibanDestino))
			throw new ValidationException("La cuenta de origen y destino no pueden ser la misma.");

		CuentaBancaria cuentaOrigen = cuentaDao.buscarPorIban(ibanOrigen);
		CuentaBancaria cuentaDestino = cuentaDao.buscarPorIban(ibanDestino);

		if (cuentaOrigen == null)
			throw new ValidationException("La cuenta de origen no existe.");
		if (cuentaDestino == null)
			throw new ValidationException("La cuenta de destino no existe.");
		if (!"VALIDADA".equals(cuentaOrigen.getEstadoValidacion()))
			throw new ValidationException("La cuenta de origen no está validada.");
		if (cuentaOrigen.getSaldo() < monto)
			throw new ValidationException("Saldo insuficiente en la cuenta de origen.");

		cuentaDao.actualizarSaldo(ibanOrigen, cuentaOrigen.getSaldo() - monto);
		cuentaDao.actualizarSaldo(ibanDestino, cuentaDestino.getSaldo() + monto);

		contabilidadService.crearRegistro(ibanOrigen, concepto + " (Hacia: " + ibanDestino + ")", "TRANSFERENCIA",
				monto);
	}
}
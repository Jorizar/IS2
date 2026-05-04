package aeropuertois2.finanzas.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.dominio.RegistroContable;
import aeropuertois2.finanzas.infrastructura.RegistroContableDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ContabilidadService {
	private static ContabilidadService instancia;
	private final RegistroContableDAO contabilidadDAO;

	private ContabilidadService(RegistroContableDAO contabilidadDAO) {
		this.contabilidadDAO = contabilidadDAO;
	}

	public static ContabilidadService getInstance(RegistroContableDAO dao) {
		if (instancia == null) {
			instancia = new ContabilidadService(dao);
		}
		return instancia;
	}

	public void crearRegistro(String cuenta, String concepto, String tipo, double monto)
			throws ValidationException, SQLException {
		validarDatos(cuenta, concepto, monto);
		validarDebeHaber(tipo, monto);

		String idGenerado = "REG-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		RegistroContable nuevo = new RegistroContable(idGenerado, cuenta, concepto, tipo, monto, true);
		contabilidadDAO.insertar(nuevo);
	}

	public void modificarRegistro(String idRegistro, String cuenta, String concepto, String tipo, double monto)
			throws ValidationException, SQLException {
		if (idRegistro == null || idRegistro.trim().isEmpty())
			throw new ValidationException("El ID del registro es obligatorio.");
		validarDatos(cuenta, concepto, monto);
		validarDebeHaber(tipo, monto);

		RegistroContable modificado = new RegistroContable(idRegistro, cuenta, concepto, tipo, monto, true);
		contabilidadDAO.actualizar(modificado);
	}

	private void validarDatos(String cuenta, String concepto, double monto) throws ValidationException {
		if (cuenta == null || cuenta.trim().isEmpty())
			throw new ValidationException("La cuenta contable no puede estar vacía.");
		if (concepto == null || concepto.trim().isEmpty())
			throw new ValidationException("El concepto no puede estar vacío.");
		if (monto <= 0)
			throw new ValidationException("El monto debe ser mayor a 0.");
	}

	private void validarDebeHaber(String tipo, double monto) throws ValidationException {
		if (tipo.equals("EGRESO") && monto > 100000) {
			throw new ValidationException(
					"Desbalance detectado: El egreso supera el límite permitido sin autorización superior.");
		}
	}

	public List<RegistroContable> obtenerTodosRegistros() throws SQLException {
		return contabilidadDAO.obtenerTodos();
	}
}
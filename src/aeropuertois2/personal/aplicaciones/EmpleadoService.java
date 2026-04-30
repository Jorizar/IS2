package aeropuertois2.personal.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.personal.dominio.Empleado;
import aeropuertois2.personal.dominio.FiltroTipo;
import aeropuertois2.personal.infraestructura.EmpleadoDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class EmpleadoService {

	private final EmpleadoDao empleadoDao;

	public EmpleadoService(EmpleadoDao empleadoDao) {
		this.empleadoDao = empleadoDao;
	}

	public List<Empleado> listarTodos() throws SQLException {
		return empleadoDao.listarTodos();
	}

	public List<Empleado> buscarPorNombreODni(String criterio) throws SQLException, ValidationException {
		ValidadorEmpleado.validarBusqueda(criterio);

		String criterioNormalizado = ValidadorEmpleado.normalizarCriterio(criterio);

		if (ValidadorEmpleado.esDni(criterioNormalizado)) {
			return empleadoDao.buscarPorDni(ValidadorEmpleado.normalizarDni(criterioNormalizado));
		}

		return empleadoDao.buscarPorNombre(criterioNormalizado);
	}

	public List<Empleado> filtrar(FiltroTipo tipo, String valor) throws SQLException, ValidationException {
		if (tipo == null) {
			throw new ValidationException("Debe seleccionar un tipo de filtro válido.");
		}

		if (ValidadorEmpleado.estaVacio(valor)) {
			throw new ValidationException("Debe seleccionar un valor de filtro válido.");
		}

		String valorNormalizado = normalizarValorFiltro(tipo, valor);

		return empleadoDao.filtrarPor(tipo, valorNormalizado);
	}

	private String normalizarValorFiltro(FiltroTipo tipo, String valor) {
		String valorNormalizado = valor.trim().toLowerCase(Locale.ROOT);

		if (tipo == FiltroTipo.TURNO && valorNormalizado.equals("manana")) {
			return "mañana";
		}

		return valorNormalizado;
	}
}
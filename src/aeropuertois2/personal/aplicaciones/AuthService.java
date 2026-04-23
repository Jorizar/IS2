package aeropuertois2.personal.aplicaciones;

import aeropuertois2.comun.excepciones.AuthorizationException;
import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.comun.utilidades.HashUtil;
import aeropuertois2.personal.dominio.Empleado;
import aeropuertois2.personal.infraestructura.EmpleadoDao;

import java.sql.SQLException;
import java.util.Optional;

public class AuthService {

	private final EmpleadoDao empleadoDao;

	public AuthService(EmpleadoDao empleadoDao) {
		this.empleadoDao = empleadoDao;
	}

	public Empleado login(String dni, String password)
			throws SQLException, AuthorizationException, ValidationException {
		ValidadorEmpleado.validarDniLogin(dni);

		Optional<Empleado> empleadoOpt = empleadoDao.buscarPorId(dni.trim());

		if (empleadoOpt.isEmpty()) {
			throw new AuthorizationException("Credenciales inválidas.");
		}

		Empleado empleado = empleadoOpt.get();
		String hashIntroducido = HashUtil.sha256(password);

		if (!empleado.getPasswordHash().equals(hashIntroducido)) {
			throw new AuthorizationException("Credenciales inválidas.");
		}

		if (!empleado.esAdministradorDePersonal()) {
			throw new AuthorizationException(
					"Acceso denegado. Solo puede acceder un empleado con rol administrador y función personal.");
		}

		return empleado;
	}
}
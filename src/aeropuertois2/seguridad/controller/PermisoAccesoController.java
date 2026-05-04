package aeropuertois2.seguridad.controller;

import java.util.Date;

import aeropuertois2.seguridad.dao.PermisoDAO;
import aeropuertois2.seguridad.dao.UsuarioDAO;
import aeropuertois2.seguridad.dao.ZonaRestringidaDAO;
import aeropuertois2.seguridad.modelo.PermisoAcceso;

public class PermisoAccesoController {

	private PermisoDAO permisoDAO = new PermisoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private ZonaRestringidaDAO zonaDAO = new ZonaRestringidaDAO();

	public boolean existeUsuario(int idUsuario) {
		return usuarioDAO.existeUsuario(idUsuario);
	}

	public boolean existeZona(int idZona) {
		return zonaDAO.existeZona(idZona);
	}

	public void crearAcceso(int idUsuario, int idZonaRestringida, Date inicio, Date fin) {

		System.out.println("Procesando creación de acceso...");

		if (permisoDAO.existePermiso(idUsuario, idZonaRestringida, inicio, fin)) {
			System.out.println("Ya existe un acceso activo para este usuario en esa zona en ese periodo");
			return;
		}

		PermisoAcceso permiso = new PermisoAcceso(idUsuario, idZonaRestringida, inicio, fin);
		boolean ok = permisoDAO.insertarPermiso(permiso);

		if (ok) {
			System.out.println("Conexión exitosa a la base de datos");
			System.out.println("Acceso creado correctamente");
		} else {
			System.out.println("Error al crear acceso");
		}
	}
}
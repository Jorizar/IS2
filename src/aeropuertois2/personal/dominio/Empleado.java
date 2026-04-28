package aeropuertois2.personal.dominio;

public class Empleado {
	private String id; // DNI
	private String passwordHash;
	private String nombre;
	private FuncionAeropuerto funcion;
	private RolEmpleado rol;
	private Turno turno;

	public Empleado() {
	}

	public Empleado(String id, String passwordHash, String nombre, FuncionAeropuerto funcion, RolEmpleado rol,
			Turno turno) {
		this.id = id;
		this.passwordHash = passwordHash;
		this.nombre = nombre;
		this.funcion = funcion;
		this.rol = rol;
		this.turno = turno;
	}

	public String getId() {
		return id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getNombre() {
		return nombre;
	}

	public FuncionAeropuerto getFuncion() {
		return funcion;
	}

	public RolEmpleado getRol() {
		return rol;
	}

	public Turno getTurno() {
		return turno;
	}

	public boolean esAdministradorDePersonal() {
		return rol == RolEmpleado.ADMINISTRADOR && funcion == FuncionAeropuerto.PERSONAL;
	}
}
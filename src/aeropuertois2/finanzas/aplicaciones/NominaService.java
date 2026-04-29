package aeropuertois2.finanzas.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException;
import aeropuertois2.finanzas.dominio.Nomina;
import aeropuertois2.finanzas.infrastructura.NominaDAO;

import java.sql.SQLException;
import java.util.UUID;

public class NominaService {
    private static NominaService instancia;
    private final NominaDAO nominaDao;
    private final ContabilidadService contabilidadService;

    private NominaService(NominaDAO dao, ContabilidadService contabilidadService) {
        this.nominaDao = dao;
        this.contabilidadService = contabilidadService;
    }

    public static NominaService getInstance(NominaDAO dao, ContabilidadService contabilidadService) {
        if (instancia == null) instancia = new NominaService(dao, contabilidadService);
        return instancia;
    }

    public void procesarPagoNomina(String idEmpleado, String iban, double bruto, double retenciones) 
            throws ValidationException, SQLException {
        
        if (bruto <= 0 || bruto <= retenciones) {
            throw new ValidationException("El importe bruto debe ser superior a las retenciones.");
        }

        String idNomina = "NOM-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        Nomina nueva = new Nomina(idNomina, idEmpleado, iban, bruto, retenciones, "PAGADA");
        nominaDao.insertar(nueva);

        String concepto = "Pago nómina empleado: " + idEmpleado;
        contabilidadService.crearRegistro(iban, concepto, "EGRESO", nueva.getImporteNeto());
    }
}
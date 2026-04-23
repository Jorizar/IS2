package aeropuertois2.incidencia.aplicaciones;

import aeropuertois2.comun.excepciones.ValidationException; 

import java.util.regex.Pattern;

public class ValidadorIncidencia {
    private static final Pattern PATRON_ID = Pattern.compile("^[0-9]{9}$"); //exactamente 9 digitos

    private ValidadorIncidencia() {
    	
    }

    public static void validarNuevaIncidencia(String id, String descrip, String causa) throws ValidationException {
        //validar ID
    	if (id == null) 
            throw new ValidationException("Es necesario introducir un id.  Recuerde, debe tener exactamante 9 digitos");
    	else if (!PATRON_ID.matcher(id.trim()).matches()) 
            throw new ValidationException("El ID debe tener exactamente 9 digitos (0-9).");

        //validar descrip
        if (descrip.length() > 200) 
            throw new ValidationException("La descripcion no puede superar los 150 caracteres.");
        
        //validar causa
        if (causa == null || causa.trim().isBlank()) 
            throw new ValidationException("Es necesario introducir una causa.");
        
        if (causa.length() > 100) 
            throw new ValidationException("La causa no puede superar los 100 caracteres.");
        
    }
}
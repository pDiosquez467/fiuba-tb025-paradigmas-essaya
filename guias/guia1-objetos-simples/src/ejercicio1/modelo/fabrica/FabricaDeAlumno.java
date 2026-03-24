package ejercicio1.modelo.fabrica;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.validacion.Validaciones;

public class FabricaDeAlumno {

    public static Alumno crear(String padron, String nombre) {
        Validaciones.validarNotBlank(padron, "padrón");
        Validaciones.validarNotBlank(nombre, "nombre");
        return new Alumno(padron, nombre);
    }
}

package ejercicio2.modelo.validaciones;

public class Validaciones {

    public static void validarNotNull(Object obj, String mensaje) {
        if (obj == null) {
            throw new RuntimeException(mensaje);
        }
    }
}

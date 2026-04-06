package ejercicio2.modelo.validaciones;

public class Validaciones {

    public static void validarNotNull(Object obj, String mensaje) {
        if (obj == null) {
            throw new RuntimeException(mensaje);
        }
    }

    public static void validarNotBlank(String string, String mensaje) {
        if ((string == null) || (string.isBlank())) {
            throw new RuntimeException(mensaje);
        }
    }
}

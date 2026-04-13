package validaciones;

import java.util.Collection;

public class Validaciones {

    private Validaciones() {}

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

    public static <T> void validarNotEmpty(Collection<T> collection, String mensaje) {
        if (collection.isEmpty()) {
            throw new RuntimeException(mensaje);
        }
    }

    public static void validarNumeroPositivo(Number numero, String mensaje) {
        if (numero.doubleValue() <= 0) {
            throw new RuntimeException(mensaje);
        }
    }
}

package ejercicio5.modelo.validaciones;

import java.util.Collection;

public class Validaciones {

    public static void validarNotNull(Object o, String mensaje) {
        if (o == null) {
            throw new RuntimeException(mensaje);
        }
    }

    public static void validarNotBlank(String str, String mensaje) {
        if (str == null || str.isBlank()) {
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

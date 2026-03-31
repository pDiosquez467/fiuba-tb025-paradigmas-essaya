package ejercicio6;

public class Validaciones {

    public static void validarNotNull(Object objeto, String mensaje) {
        if (objeto == null) {
            throw new RuntimeException(mensaje);
        }
    }

    public static void validarNotBlank(String texto, String mensaje) {
        if (texto == null || texto.isBlank()) {
            throw new RuntimeException(mensaje);
        }
    }

    public static void validarNumeroNoNegativo(Number numero, String mensaje) {
        if (numero.intValue() < 0) {
            throw new RuntimeException(mensaje);
        }
    }

    public static void validarNumeroEntreInclusive(Number numero, Number desde, Number hasta, String mensaje) {
        if (numero.intValue() < desde.intValue() || numero.intValue() > hasta.intValue()) {
            throw new RuntimeException(mensaje);
        }
    }
}
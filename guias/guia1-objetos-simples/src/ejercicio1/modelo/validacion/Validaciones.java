package ejercicio1.modelo.validacion;

public class Validaciones {

    public static void validarNotBlank(String padron, String mensaje) {
        if (padron == null || padron.isBlank()) {
            throw new RuntimeException(mensaje);
        }
    }

    public static void validarNumeroPositivo(Number numero, String mensaje) {
        if (numero.doubleValue() <= 0) {
            throw new RuntimeException(mensaje);
        }
    }
}

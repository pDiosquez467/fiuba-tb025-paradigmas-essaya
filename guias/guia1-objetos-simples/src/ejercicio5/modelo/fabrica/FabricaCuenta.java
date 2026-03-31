package ejercicio5.modelo.fabrica;

import ejercicio5.modelo.Cuenta;
import ejercicio5.modelo.validaciones.Validaciones;

public class FabricaCuenta {

    public static Cuenta crear(String id, double saldo) {
        Validaciones.validarNotBlank(id, "id");
        Validaciones.validarNumeroPositivo(saldo, "saldo");
        return new Cuenta(
                id,
                saldo
        );
    }

    public static Cuenta crear(String id) {
        return crear(id, 0);
    }
}

package ejercicio5.modelo.fabrica;

import ejercicio5.modelo.Cuenta;
import ejercicio5.modelo.Transferencia;
import ejercicio5.modelo.validaciones.Validaciones;

import java.time.LocalDate;

public class FabricaTransferencia {

    public static Transferencia crear(
            String id,
            Cuenta origen,
            Cuenta destino,
            double monto,
            LocalDate fecha,
            String mensaje
    ) {
        Validaciones.validarNotBlank(id, "id");
        Validaciones.validarNotNull(origen, "origen");
        Validaciones.validarNotNull(destino, "destino");
        Validaciones.validarNumeroPositivo(monto, "monto");
        Validaciones.validarNotNull(fecha, "fecha");
        Validaciones.validarNotNull(mensaje, "mensaje");
        return new Transferencia(
                id,
                origen,
                destino,
                monto,
                fecha,
                mensaje
        );
    }
}

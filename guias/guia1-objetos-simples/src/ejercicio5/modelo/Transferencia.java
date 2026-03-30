package ejercicio5.modelo;

import java.time.LocalDate;
import java.util.Objects;

public record Transferencia(
        String id,
        Cuenta origen,
        Cuenta destino,
        double monto,
        LocalDate fecha,
        String mensaje) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transferencia that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

package ejercicio2.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public record Mensaje(
        String id,
        Usuario remitente,
        Usuario destinatario,
        LocalDateTime fecha,
        String contenido) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Mensaje mensaje)) return false;
        return Objects.equals(id, mensaje.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}

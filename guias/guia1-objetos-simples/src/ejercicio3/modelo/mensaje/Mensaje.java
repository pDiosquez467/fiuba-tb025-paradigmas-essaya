package ejercicio3.modelo.mensaje;

import ejercicio3.modelo.us.Usuario;
import validaciones.Validaciones;

import java.time.LocalDateTime;
import java.util.Objects;

public record Mensaje(
        String id,
        Usuario remitente,
        Usuario destinatario,
        LocalDateTime fecha,
        String contenido
) {

    public Mensaje {
        Validaciones.validarNotBlank(id, "id");
        Validaciones.validarNotNull(remitente, "remitente");
        Validaciones.validarNotNull(destinatario, "destinatario");
        Validaciones.validarNotNull(fecha, "fecha");
        Validaciones.validarNotBlank(contenido, "contenido");
    }

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

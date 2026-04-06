package ejercicio2.modelo.fabrica;

import ejercicio2.modelo.Mensaje;
import ejercicio2.modelo.Usuario;
import ejercicio2.modelo.validaciones.Validaciones;

import java.time.LocalDateTime;

public class FabricaMensaje {

    public static Mensaje crear(
            String id,
            Usuario usuario,
            Usuario otro,
            LocalDateTime fecha,
            String contenido) {
        Validaciones.validarNotBlank(id, "El identificador del mensaje es obligatorio.");
        Validaciones.validarNotNull(usuario, "El remitente del mensaje no puede ser nulo.");
        Validaciones.validarNotNull(otro, "El destinatario del mensaje no puede ser nulo.");
        Validaciones.validarNotNull(fecha, "La fecha de envío del mensaje no puede ser nula.");
        Validaciones.validarNotBlank(contenido, "El contenido del mensaje no puede ser nulo ni estar vacío.");
        return new Mensaje(
                id,
                usuario,
                otro,
                fecha,
                contenido
        );
    }
}
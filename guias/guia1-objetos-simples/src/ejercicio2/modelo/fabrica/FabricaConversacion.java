package ejercicio2.modelo.fabrica;

import ejercicio2.modelo.Conversacion;
import ejercicio2.modelo.Usuario;
import ejercicio2.modelo.validaciones.Validaciones;

public class FabricaConversacion {

    public static Conversacion crear(
            String id,
            Usuario usuario,
            Usuario otro) {
        Validaciones.validarNotBlank(id, "El identificador de la conversación es obligatorio.");
        Validaciones.validarNotNull(usuario, "El primer participante de la conversación no puede ser nulo.");
        Validaciones.validarNotNull(otro, "El segundo participante de la conversación no puede ser nulo.");
        return new Conversacion(
                id,
                usuario,
                otro
        );
    }
}
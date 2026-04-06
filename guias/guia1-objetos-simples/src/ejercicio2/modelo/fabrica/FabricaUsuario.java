package ejercicio2.modelo.fabrica;

import ejercicio2.modelo.Usuario;
import ejercicio2.modelo.validaciones.Validaciones;

public class FabricaUsuario {

    public static Usuario crear(
            String id,
            String nombre,
            String email,
            String telefono) {
        Validaciones.validarNotBlank(id, "El identificador del usuario es obligatorio.");
        Validaciones.validarNotBlank(nombre, "El nombre del usuario es obligatorio.");
        Validaciones.validarNotBlank(email, "El correo electrónico del usuario es obligatorio.");
        Validaciones.validarNotBlank(telefono, "El teléfono del usuario es obligatorio.");
        return new Usuario(
                id,
                nombre,
                email,
                telefono
        );
    }
}
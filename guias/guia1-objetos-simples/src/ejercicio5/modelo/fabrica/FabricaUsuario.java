package ejercicio5.modelo.fabrica;


import ejercicio5.modelo.Usuario;
import ejercicio5.modelo.validaciones.Validaciones;

public class FabricaUsuario {

    public static Usuario crear(String id, String nombre, String direccion, String telefono) {
        Validaciones.validarNotBlank(id, "El identificador del usuario es obligatorio.");
        Validaciones.validarNotBlank(nombre, "El nombre del usuario es obligatorio.");
        Validaciones.validarNotBlank(direccion, "La dirección del usuario es obligatoria.");
        Validaciones.validarNotBlank(telefono, "El teléfono del usuario es obligatorio.");
        return new Usuario(
                id,
                nombre,
                direccion,
                telefono
        );
    }

}

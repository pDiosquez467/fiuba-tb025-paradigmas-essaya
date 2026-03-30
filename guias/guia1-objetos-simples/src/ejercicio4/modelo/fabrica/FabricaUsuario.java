package ejercicio4.modelo.fabrica;

import ejercicio4.modelo.Usuario;
import ejercicio4.modelo.validaciones.Validaciones;

import java.time.LocalDate;

public class FabricaUsuario {

    public static Usuario crear(
            String id,
            String nombre,
            String direccion,
            LocalDate fechaDeNacimiento) {
        Validaciones.validarNotBlank(id, "id");
        Validaciones.validarNotBlank(nombre, "nombre");
        Validaciones.validarNotBlank(direccion, "dirección");
        Validaciones.validarNotNull(fechaDeNacimiento, "fechaDeNacimiento");
        return new Usuario(
                id,
                nombre,
                direccion,
                fechaDeNacimiento
        );
    }
}

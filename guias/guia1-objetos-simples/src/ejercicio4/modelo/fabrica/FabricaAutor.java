package ejercicio4.modelo.fabrica;

import ejercicio4.modelo.Autor;
import ejercicio4.modelo.validaciones.Validaciones;

import java.time.LocalDate;

public class FabricaAutor {

    public static Autor crear(
            String id,
            String nombre,
            LocalDate fechaDeNacimiento) {
        Validaciones.validarNotBlank(id, "id");
        Validaciones.validarNotBlank(nombre, "nombre");
        Validaciones.validarNotNull(fechaDeNacimiento, "fechaDeNacimiento");
        return new Autor(
                id,
                nombre,
                fechaDeNacimiento
        );
    }

}

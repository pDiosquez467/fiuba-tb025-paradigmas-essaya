package ejercicio1.modelo.fabrica;

import ejercicio1.modelo.Materia;
import ejercicio1.modelo.validacion.Validaciones;

public class FabricaDeMateria {

    public static Materia crear(String codigo, String nombre, int creditos) {
        Validaciones.validarNotBlank(codigo, "codigo");
        Validaciones.validarNotBlank(nombre, "nombre");
        Validaciones.validarNumeroPositivo(creditos, "creditos");
        return new Materia(
                codigo,
                nombre,
                creditos
        );
    }
}

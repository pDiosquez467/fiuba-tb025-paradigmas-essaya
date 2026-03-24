package ejercicio1.modelo.fabrica;

import ejercicio1.modelo.Carrera;
import ejercicio1.modelo.validacion.Validaciones;

import java.util.HashSet;

public class FabricaDeCarrera {

    public static Carrera crear(String codigo, String nombre, int creditosMinimos) {
        Validaciones.validarNotBlank(codigo, "código");
        Validaciones.validarNotBlank(nombre, "nombre");
        Validaciones.validarNumeroPositivo(creditosMinimos, "créditos");
        return new Carrera(
                codigo,
                nombre,
                new HashSet<>(),
                new HashSet<>(),
                creditosMinimos
        );
    }

}

package ejercicio2.repositorio.validaciones;

import ejercicio2.repositorio.Repositorio;

public class Validaciones {

    public static <T> void validarExistenciaEnLaBD(Repositorio<T> repositorio, String id, String mensajeError) {
        validarCondicion(!repositorio.existe(id), mensajeError);
    }

    public static <T> void validarNoExistenciaEnLaBD(Repositorio<T> repositorio, String id, String mensajeError) {
        validarCondicion(repositorio.existe(id), mensajeError);
    }

    public static void validarCondicion(boolean condicion, String mensajeError) {
        if (!condicion) {
            throw new RuntimeException(mensajeError);
        }
    }
}

package ejercicio5.repositorio.validaciones;

import ejercicio5.repositorio.Repositorio;

public class Validaciones {

    public static <T> void validarExistenciaEnLaBD(Repositorio<T> repositorio, String id, String mensajeError) {
        validarCondicion(!repositorio.existe(id), mensajeError);
    }

    public static <T> void validarNoExistenciaEnLaBD(Repositorio<T> repositorio, String id, String mensajeError) {
        validarCondicion(repositorio.existe(id), mensajeError);
    }

    private static void validarCondicion(boolean condicion, String mensajeError) {
        if (!condicion) {
            throw new RuntimeException(mensajeError);
        }
    }
}

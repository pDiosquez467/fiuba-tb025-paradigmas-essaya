package ejercicio3.repositorio.shared;

import ejercicio3.repositorio.Repositorio;

public class ValidacionesDePersistencia {

    private ValidacionesDePersistencia() {
    }

    public static <T> void validarExistencia(Repositorio<T> repositorio, String id, String mensajeError) {
        if (!repositorio.existe(id)) {
            throw new RuntimeException(mensajeError);
        }
    }

    public static <T> void validarNoExistencia(Repositorio<T> repositorio, String id, String mensajeError) {
        if (repositorio.existe(id)) {
            throw new RuntimeException(mensajeError);
        }
    }
}

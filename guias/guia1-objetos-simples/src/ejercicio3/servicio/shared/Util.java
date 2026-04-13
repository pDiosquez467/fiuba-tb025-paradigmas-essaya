package ejercicio3.servicio.shared;

import ejercicio3.repositorio.Repositorio;

public class Util {

    private Util() {
    }

    public static <T> T obtenerExistente(Repositorio<T> repositorio, String id, String mensajeError) {
        return repositorio
                .obtener(id)
                .orElseThrow(() -> new RuntimeException(mensajeError));
    }
}

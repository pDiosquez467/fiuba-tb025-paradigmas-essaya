package ejercicio2.repositorio;

import java.util.Optional;

public interface Repositorio<T> {

    void agregar(T t);
    void remover(String id);
    Optional<T> obtener(String id);
    boolean existe(String id);
}

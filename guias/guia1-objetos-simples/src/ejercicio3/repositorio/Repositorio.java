package ejercicio3.repositorio;

import java.util.Optional;

public interface Repositorio<T> {

    void agregar(T t);

    T quitar(String id);

    boolean existe(String id);

    Optional<T> obtener(String id);
}

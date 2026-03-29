package ejercicio4.repositorio;

import java.util.Optional;

public interface Repositorio<T> {
    public Optional<T> obtener(String id);
}

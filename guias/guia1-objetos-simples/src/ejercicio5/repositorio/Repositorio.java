package ejercicio5.repositorio;

import ejercicio5.modelo.Cuenta;

import java.util.Optional;

public interface Repositorio<T> {
    void agregar(T t);
    void quitar(String id);
    boolean existe(String id);
    Optional<T> obtener(String id);
}

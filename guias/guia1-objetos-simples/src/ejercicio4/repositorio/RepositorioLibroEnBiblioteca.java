package ejercicio4.repositorio;

import ejercicio4.modelo.Autor;
import ejercicio4.modelo.LibroEnBiblioteca;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class RepositorioLibroEnBiblioteca implements Repositorio<LibroEnBiblioteca> {

    private final Map<String, LibroEnBiblioteca> db;

    public RepositorioLibroEnBiblioteca() {
        this.db = new HashMap<>();
    }

    public void agregar(LibroEnBiblioteca libroEnBiblioteca) {
        String isbn = libroEnBiblioteca.getLibro().isbn();
        validarNoExistenciaEnLaBD(isbn);
        db.put(isbn, libroEnBiblioteca);
    }

    public void quitar(String isbn) {
        validarExistenciaEnLaBD(isbn);
        db.remove(isbn);
    }

    public Optional<LibroEnBiblioteca> obtener(String isbn) {
        return Optional.ofNullable(db.get(isbn));
    }

    public boolean existe(String isbn) {
        return db.containsKey(isbn);
    }

    public List<LibroEnBiblioteca> obtenerTodos() {
        return db.values().stream().toList();
    }

    public List<LibroEnBiblioteca> buscarPorTitulo(String titulo) {
        return buscarPor(libroEnBiblioteca
                -> libroEnBiblioteca
                .getLibro()
                .titulo()
                .toLowerCase()
                .contains(titulo.toLowerCase())
        );
    }

    public List<LibroEnBiblioteca> buscarPorAutor(Autor autor) {
        return buscarPor(libroEnBiblioteca
                -> libroEnBiblioteca.getLibro().autores().contains(autor));
    }

    public List<LibroEnBiblioteca> buscarPorFechaDePublicacion(LocalDate fechaDePublicacion) {
        return buscarPor(libroEnBiblioteca
                -> libroEnBiblioteca.getLibro().fechaDePublicacion().equals(fechaDePublicacion));
    }

    private List<LibroEnBiblioteca> buscarPor(Predicate<LibroEnBiblioteca> predicado) {
        return db.values()
                .stream()
                .filter(predicado)
                .toList();
    }

    private void validarExistenciaEnLaBD(String isbn) {
        if (!db.containsKey(isbn)) {
            throw new RuntimeException("El libro no existe en la BD");
        }
    }

    private void validarNoExistenciaEnLaBD(String isbn) {
        if (db.containsKey(isbn)) {
            throw new RuntimeException("El libro existe en la BD");
        }
    }
}

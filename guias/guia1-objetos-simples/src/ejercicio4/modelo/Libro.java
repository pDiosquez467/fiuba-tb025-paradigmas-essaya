package ejercicio4.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public record Libro(String isbn, String titulo, LocalDate fechaDePublicacion, List<Autor> autores) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Libro libro)) return false;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public List<Autor> autores() {
        return List.copyOf(autores);
    }
}

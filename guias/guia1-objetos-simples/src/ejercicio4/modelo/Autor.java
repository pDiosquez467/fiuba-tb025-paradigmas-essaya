package ejercicio4.modelo;

import java.time.LocalDate;
import java.util.Objects;

public record Autor(String id, String nombre, LocalDate fechaDeNacimiento) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Autor autor)) return false;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

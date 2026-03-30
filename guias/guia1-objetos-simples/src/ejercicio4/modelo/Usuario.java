package ejercicio4.modelo;

import java.time.LocalDate;
import java.util.Objects;

public record Usuario(String id, String nombre, String direccion, LocalDate fechaDeNacimiento) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

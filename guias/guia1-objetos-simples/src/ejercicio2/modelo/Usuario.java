package ejercicio2.modelo;

import java.util.Objects;

public record Usuario(
        String id,
        String nombre,
        String email,
        String telefono) {

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

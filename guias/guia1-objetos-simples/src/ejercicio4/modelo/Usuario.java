package ejercicio4.modelo;

import java.time.LocalDate;
import java.util.Objects;

public record Usuario(String numeroDeUsuario, String nombre, String direccion, LocalDate fechaDeNacimiento) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(numeroDeUsuario, usuario.numeroDeUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroDeUsuario);
    }
}

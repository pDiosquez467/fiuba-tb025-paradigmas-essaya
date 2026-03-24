package ejercicio1.modelo;

import java.util.Objects;

public record Alumno(String padron, String nombre) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Alumno alumno)) return false;
        return Objects.equals(padron, alumno.padron);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(padron);
    }
}

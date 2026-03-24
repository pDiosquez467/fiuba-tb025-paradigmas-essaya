package ejercicio1.modelo;

import java.util.Objects;

public record Materia(String codigo, String nombre, int creditos) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Materia materia)) return false;
        return Objects.equals(codigo, materia.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}

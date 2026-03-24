package ejercicio1.modelo;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Carrera {
    private final String codigo;
    private final String nombre;
    private final Set<Materia> obligatorias;
    private final Set<Materia> optativas;
    private final int creditosMinimos;

    public Carrera(String codigo,
                   String nombre,
                   Set<Materia> obligatorias,
                   Set<Materia> optativas,
                   int creditosMinimos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.obligatorias = obligatorias;
        this.optativas = optativas;
        this.creditosMinimos = creditosMinimos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Carrera carrera)) return false;
        return Objects.equals(codigo, carrera.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public List<Materia> obligatorias() {
        return List.copyOf(obligatorias);
    }

    public List<Materia> optativas() {
        return List.copyOf(optativas);
    }

    public boolean contiene(Materia materia) {
        return obligatorias.contains(materia)
                || optativas.contains(materia);
    }

    public boolean esObligatoria(Materia materia) {
        return obligatorias.contains(materia);
    }

    public String codigo() {
        return codigo;
    }

    public String nombre() {
        return nombre;
    }

    public int creditosMinimos() {
        return creditosMinimos;
    }

    @Override
    public String toString() {
        return "Carrera[" +
                "codigo=" + codigo + ", " +
                "nombre=" + nombre + ", " +
                "obligatorias=" + obligatorias + ", " +
                "optativas=" + optativas + ", " +
                "creditosMinimos=" + creditosMinimos + ']';
    }

}

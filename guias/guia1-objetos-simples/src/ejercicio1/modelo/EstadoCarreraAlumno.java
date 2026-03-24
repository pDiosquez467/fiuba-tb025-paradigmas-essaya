package ejercicio1.modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EstadoCarreraAlumno {

    private final Alumno alumno;
    private final Carrera carrera;
    private EstadoCarrera estadoCarrera;
    private final Set<Materia> materiasAprobadas;

    public EstadoCarreraAlumno(Alumno alumno, Carrera carrera) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.materiasAprobadas = new HashSet<>();
        this.estadoCarrera = EstadoCarrera.ACTIVO;
    }

    public void aprobarMateria(Materia materia) {
        validarMateriaPerteneceALaCarrera(materia);
        validarEstadoActivo();
        validarMateriaNoDuplicada(materia);
        materiasAprobadas.add(materia);
    }

    public List<Materia> getMateriasAprobadas() {
        return List.copyOf(materiasAprobadas);
    }

    public int creditosObtenidos() {
        return materiasAprobadas.stream()
                .mapToInt(Materia::creditos)
                .sum();
    }

    public void darDeBaja() {
        validarEstadoActivo();
        this.estadoCarrera = EstadoCarrera.BAJA;
    }

    public boolean completoCarrera() {
        return materiasAprobadas.stream()
                                    .filter(carrera::esObligatoria)
                                    .count() >= carrera.obligatorias().size()
                && creditosObtenidos() >= carrera.creditosMinimos();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EstadoCarreraAlumno that)) return false;
        return Objects.equals(alumno, that.alumno) && Objects.equals(carrera, that.carrera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno, carrera);
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public EstadoCarrera getEstadoCarrera() {
        return estadoCarrera;
    }

    private void validarEstadoActivo() {
        if (estadoCarrera == EstadoCarrera.BAJA) {
            throw new RuntimeException("El alumno está dado de baja");
        }
    }

    private void validarMateriaNoDuplicada(Materia materia) {
        if (materiasAprobadas.contains(materia)) {
            throw new RuntimeException("La materia ya fue aprobada");
        }
    }

    private void validarMateriaPerteneceALaCarrera(Materia materia) {
        if (!carrera.contiene(materia)) {
            throw new RuntimeException("La materia no pertenece al plan de estudios de la carrera");
        }
    }
}

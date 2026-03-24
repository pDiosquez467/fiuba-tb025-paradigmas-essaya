package ejercicio1.repositorio;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.Carrera;
import ejercicio1.modelo.EstadoCarreraAlumno;

import java.util.*;

public class RepositorioEstadosCarrera {

    private final Map<String, Set<EstadoCarreraAlumno>> db;

    public RepositorioEstadosCarrera() {
        this.db = new HashMap<>();
    }

    public void agregar(EstadoCarreraAlumno estadoCarreraAlumno) {
        Alumno alumno = estadoCarreraAlumno.getAlumno();
        if (!db.containsKey(alumno.padron())) {
            db.put(alumno.padron(), new HashSet<>());
        }
        validarNoDuplicados(estadoCarreraAlumno);
        db.get(alumno.padron()).add(estadoCarreraAlumno);
    }

    public EstadoCarreraAlumno obtener(String padron, Carrera carrera) {
        validarEstadosCargadosDelAlumno(padron);
        validarAlumnoInscriptoEnCarrera(padron, carrera);
        return db.get(padron).stream()
                .filter(estado -> estado.getCarrera().equals(carrera))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("..."));
    }

    public List<EstadoCarreraAlumno> obtener(String padron) {
        validarEstadosCargadosDelAlumno(padron);
        return List.copyOf(db.get(padron));
    }

    public void darDeBaja(String padron, Carrera carrera) {
        validarEstadosCargadosDelAlumno(padron);
        Set<EstadoCarreraAlumno> estados   = db.get(padron);
        EstadoCarreraAlumno estadoGuardado =
                estados.stream()
                        .filter(estado -> estado.getCarrera().equals(carrera))
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
        estadoGuardado.darDeBaja();
    }

    public boolean existeInscripcion(String padron, Carrera carrera) {
        validarEstadosCargadosDelAlumno(padron);
        return db.get(padron).stream()
                .anyMatch(estado -> estado.getCarrera().equals(carrera));
    }

    private void validarEstadosCargadosDelAlumno(String padron) {
        if (!db.containsKey(padron)) {
            throw new RuntimeException("El alumno no tiene inscripciones");
        }
    }

    private void validarAlumnoInscriptoEnCarrera(String padron, Carrera carrera) {
        if (db.get(padron).stream().noneMatch(estado -> estado.getCarrera().equals(carrera))) {
            throw new RuntimeException("El alumno no está inscripto en la carrera dada");
        }
    }

    private void validarNoDuplicados(EstadoCarreraAlumno estado) {
        String padron = estado.getAlumno().padron();
        if (db.get(padron).contains(estado)) {
            throw new RuntimeException("El estado de carrera está duplicado");
        }
    }
}

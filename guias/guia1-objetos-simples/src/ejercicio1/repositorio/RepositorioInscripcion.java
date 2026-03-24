package ejercicio1.repositorio;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.Carrera;
import ejercicio1.modelo.EstadoInscripcion;

import java.util.*;

public class RepositorioInscripcion {

    private final Map<String, Set<EstadoInscripcion>> db;

    public RepositorioInscripcion() {
        this.db = new HashMap<>();
    }

    public void agregar(EstadoInscripcion estadoInscripcion) {
        Alumno alumno = estadoInscripcion.getAlumno();
        if (!db.containsKey(alumno.padron())) {
            db.put(alumno.padron(), new HashSet<>());
        }
        validarNoDuplicados(estadoInscripcion);
        db.get(alumno.padron()).add(estadoInscripcion);
    }

    public EstadoInscripcion obtener(String padron, Carrera carrera) {
        return db.get(padron).stream()
                .filter(estado -> estado.getCarrera().equals(carrera))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El alumno no está inscripto en la carrera"));
    }

    public List<EstadoInscripcion> obtener(String padron) {
        validarEstadosCargadosDelAlumno(padron);
        return List.copyOf(db.get(padron));
    }

    public void darDeBaja(String padron, Carrera carrera) {
        validarEstadosCargadosDelAlumno(padron);
        Set<EstadoInscripcion> estados   = db.get(padron);
        EstadoInscripcion estadoGuardado =
                estados.stream()
                        .filter(estado -> estado.getCarrera().equals(carrera))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("El alumno no está inscripto en la carrera"));
        estadoGuardado.darDeBaja();
    }

    public boolean existeInscripcion(String padron, Carrera carrera) {
        if (!db.containsKey(padron)) return false;
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

    private void validarNoDuplicados(EstadoInscripcion estado) {
        String padron = estado.getAlumno().padron();
        if (db.get(padron).contains(estado)) {
            throw new RuntimeException("El estado de carrera está duplicado");
        }
    }
}

package ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroFiuba {

    private final Map<String, Persona> personasPorDni;
    private final Map<String, Alumno> alumnosPorPadron;
    private final Map<String, Docente> docentesPorLegajo;

    public RegistroFiuba() {
        this.personasPorDni    = new HashMap<>();
        this.alumnosPorPadron  = new HashMap<>();
        this.docentesPorLegajo = new HashMap<>();
    }

    public void agregarAlumno(Alumno alumno) {
        validarAlumnoNoNulo(alumno);
        validarDniDisponible(alumno.getDni());
        validarLegajoAlumnoDisponible(alumno.getPadron());
        registrarAlumno(alumno);
    }

    public void agregarDocente(Docente docente) {
        validarDocenteNoNulo(docente);
        validarDniDisponible(docente.getDni());
        validarLegajoDocenteDisponible(docente.getLegajo());
        registrarDocente(docente);
    }

    public List<Alumno> obtenerAlumnos() {
        return List.copyOf(alumnosPorPadron.values());
    }

    public List<Docente> obtenerDocentes() {
        return List.copyOf(docentesPorLegajo.values());
    }

    private void registrarAlumno(Alumno alumno) {
        personasPorDni.put(alumno.getDni(), alumno);
        alumnosPorPadron.put(alumno.getPadron(), alumno);
    }

    private void registrarDocente(Docente docente) {
        personasPorDni.put(docente.getDni(), docente);
        docentesPorLegajo.put(docente.getLegajo(), docente);
    }

    private void validarDniDisponible(String dni) {
        lanzarSi(personasPorDni.containsKey(dni),
                "Ya existe una persona registrada con el DNI " + dni + ".");
    }

    private void validarLegajoAlumnoDisponible(String padron) {
        lanzarSi(alumnosPorPadron.containsKey(padron),
                "Ya existe un alumno registrado con el padrón " + padron + ".");
    }

    private void validarLegajoDocenteDisponible(String legajo) {
        lanzarSi(docentesPorLegajo.containsKey(legajo),
                "Ya existe un docente registrado con el legajo " + legajo + ".");
    }

    private void validarAlumnoNoNulo(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }
    }

    private void validarDocenteNoNulo(Docente docente) {
        if (docente == null) {
            throw new IllegalArgumentException("El docente no puede ser nulo.");
        }
    }

    private void lanzarSi(boolean condicion, String mensaje) {
        if (condicion) {
            throw new RuntimeException(mensaje);
        }
    }
}
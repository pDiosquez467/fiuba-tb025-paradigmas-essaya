package ejercicio1.servicio;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.Carrera;
import ejercicio1.modelo.EstadoInscripcion;
import ejercicio1.modelo.Materia;
import ejercicio1.repositorio.RepositorioInscripcion;

import java.util.*;

public class ServicioInscripciones {

    private final RepositorioInscripcion repositorioInscripcion;

    public ServicioInscripciones(RepositorioInscripcion repositorioInscripcion) {
        this.repositorioInscripcion = repositorioInscripcion;
    }

    public void inscribir(Alumno alumno, Carrera carrera) {
        validarNoInscripcionDuplicada(alumno.padron(), carrera);
        repositorioInscripcion.agregar(new EstadoInscripcion(alumno, carrera));
    }

    public void darDeBaja(Alumno alumno, Carrera carrera) {
        validarInscripcionExistente(alumno.padron(), carrera);
        repositorioInscripcion.darDeBaja(alumno.padron(), carrera);
    }

    public void aprobarMateria(Alumno alumno, Carrera carrera, Materia materia) {
        validarInscripcionExistente(alumno.padron(), carrera);
        validarMateriaPerteneceACarrera(alumno.padron(), carrera, materia);
        EstadoInscripcion estado = repositorioInscripcion.obtener(alumno.padron(), carrera);
        estado.aprobarMateria(materia);
    }

    public EstadoInscripcion consultarEstadoDeCarrera(Alumno alumno, Carrera carrera) {
        validarInscripcionExistente(alumno.padron(), carrera);
        return repositorioInscripcion
                .obtener(alumno.padron(), carrera);
    }

    public List<EstadoInscripcion> obtenerInscripciones(Alumno alumno) {
        return repositorioInscripcion.obtener(alumno.padron());
    }

    public EstadoInscripcion obtenerInscripcion(Alumno alumno, Carrera carrera) {
        validarInscripcionExistente(alumno.padron(), carrera);
        return repositorioInscripcion.obtener(alumno.padron(), carrera);
    }

    private void validarInscripcionExistente(String padron, Carrera carrera) {
        if (!repositorioInscripcion.existeInscripcion(padron, carrera)) {
            throw new RuntimeException("El alumno no está inscripto en la carrera");
        }
    }

    private void validarNoInscripcionDuplicada(String padron, Carrera carrera) {
        if (repositorioInscripcion.existeInscripcion(padron, carrera)) {
            throw new RuntimeException("El alumno está inscripto en la carrera");
        }
    }

    private void validarMateriaPerteneceACarrera(String padron, Carrera carrera, Materia materia) {
        if (!repositorioInscripcion.obtener(padron, carrera).getCarrera().contiene(materia)) {
            throw new RuntimeException("La materia no pertenece a la carrera");
        }
    }

}

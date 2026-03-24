package ejercicio1.servicio;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.Carrera;
import ejercicio1.modelo.EstadoCarreraAlumno;
import ejercicio1.modelo.Materia;
import ejercicio1.repositorio.RepositorioAlumnos;
import ejercicio1.repositorio.RepositorioCarreras;
import ejercicio1.repositorio.RepositorioEstadosCarrera;

import java.util.*;

public class ServicioInscripciones {

    private final RepositorioAlumnos repositorioAlumnos;

    private final RepositorioCarreras repositorioCarreras;

    private final RepositorioEstadosCarrera repositorioEstadosCarrera;

    public ServicioInscripciones(RepositorioAlumnos repositorioAlumnos, RepositorioCarreras repositorioCarreras, RepositorioEstadosCarrera repositorioEstadosCarrera) {
        this.repositorioAlumnos = repositorioAlumnos;
        this.repositorioCarreras = repositorioCarreras;
        this.repositorioEstadosCarrera = repositorioEstadosCarrera;
    }

    public void inscribir(Alumno alumno, Carrera carrera) {
        validarExistenciaAlumno(alumno.padron());
        validarExistenciaCarrera(carrera.codigo());
        validarNoInscripcionDuplicada(alumno.padron(), carrera);
        repositorioEstadosCarrera.agregar(new EstadoCarreraAlumno(alumno, carrera));
    }

    public void darDeBaja(Alumno alumno, Carrera carrera) {
        validarExistenciaAlumno(alumno.padron());
        validarExistenciaCarrera(carrera.codigo());
        validarInscripcionExistente(alumno.padron(), carrera);
        repositorioEstadosCarrera.darDeBaja(alumno.padron(), carrera);
    }

    public void aprobarMateria(Alumno alumno, Carrera carrera, Materia materia) {
        validarExistenciaAlumno(alumno.padron());
        validarExistenciaCarrera(carrera.codigo());
        validarInscripcionExistente(alumno.padron(), carrera);
        EstadoCarreraAlumno estado = repositorioEstadosCarrera.obtener(alumno.padron(), carrera);
        estado.aprobarMateria(materia);
    }


    public EstadoCarreraAlumno consultarEstadoDeCarrera(Alumno alumno, Carrera carrera) {
        validarExistenciaAlumno(alumno.padron());
        validarExistenciaCarrera(carrera.codigo());
        validarInscripcionExistente(alumno.padron(), carrera);
        return repositorioEstadosCarrera
                .obtener(alumno.padron(), carrera);
    }

    public List<EstadoCarreraAlumno> obtenerInscripciones(Alumno alumno) {
        validarExistenciaAlumno(alumno.padron());
        return repositorioEstadosCarrera.obtener(alumno.padron());
    }

    private void validarExistenciaAlumno(String padron) {
        if (!repositorioAlumnos.existe(padron)) {
            throw new RuntimeException("El alumno no existe en la BD");
        }
    }

    private void validarExistenciaCarrera(String codigo) {
        if (!repositorioCarreras.existe(codigo)) {
            throw new RuntimeException("La carrera no existe en la BD");
        }
    }

    private void validarInscripcionExistente(String padron, Carrera carrera) {
        if (!repositorioEstadosCarrera.existeInscripcion(padron, carrera)) {
            throw new RuntimeException("El alumno no está inscripto en la carrera");
        }
    }

    private void validarNoInscripcionDuplicada(String padron, Carrera carrera) {
        if (repositorioEstadosCarrera.existeInscripcion(padron, carrera)) {
            throw new RuntimeException("El alumno está inscripto en la carrera");
        }
    }
}

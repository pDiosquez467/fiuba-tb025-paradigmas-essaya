package ejercicio1.app;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.Carrera;
import ejercicio1.modelo.EstadoInscripcion;
import ejercicio1.modelo.Materia;
import ejercicio1.modelo.fabrica.FabricaDeAlumno;
import ejercicio1.modelo.fabrica.FabricaDeCarrera;
import ejercicio1.modelo.fabrica.FabricaDeMateria;
import ejercicio1.repositorio.RepositorioAlumno;
import ejercicio1.repositorio.RepositorioCarrera;
import ejercicio1.repositorio.RepositorioMateria;
import ejercicio1.servicio.ServicioInscripciones;

import java.util.List;

public class AppFIUBA {

    private final RepositorioAlumno repositorioAlumno;

    private final RepositorioCarrera repositorioCarrera;

    private final RepositorioMateria repositorioMateria;

    private final ServicioInscripciones servicioInscripciones;

    public AppFIUBA(RepositorioAlumno repositorioAlumno,
                    RepositorioCarrera repositorioCarrera,
                    RepositorioMateria repositorioMateria,
                    ServicioInscripciones servicioInscripciones) {
        this.repositorioAlumno = repositorioAlumno;
        this.repositorioCarrera = repositorioCarrera;
        this.repositorioMateria = repositorioMateria;
        this.servicioInscripciones = servicioInscripciones;
    }

    public void agregarAlumno(String padron, String nombre) {
        repositorioAlumno.agregar(FabricaDeAlumno.crear(padron, nombre));
    }

    public void agregarCarrera(String codigo, String nombre, int creditosMinimos) {
        repositorioCarrera.agregar(FabricaDeCarrera.crear(codigo, nombre, creditosMinimos));
    }

    public void agregarMateria(String codigo, String nombre, int creditos) {
        repositorioMateria.agregar(FabricaDeMateria.crear(codigo, nombre, creditos));
    }

    public void inscribirAlumnoEnCarrera(String padron, String codigoDeCarrera) {
        servicioInscripciones.inscribir(obtenerAlumnoExistente(padron), obtenerCarreraExistente(codigoDeCarrera));
    }

    public void darDeBajaAlumnoDeCarrera(String padron, String codigoDeCarrera) {
        servicioInscripciones.darDeBaja(obtenerAlumnoExistente(padron), obtenerCarreraExistente(codigoDeCarrera));
    }

    public void aprobarMateria(String padron, String codigoDeCarrera, String codigoDeMateria) {
        Alumno alumno   = obtenerAlumnoExistente(padron);
        Carrera carrera = obtenerCarreraExistente(codigoDeCarrera);
        Materia materia = obtenerMateriaExistente(codigoDeMateria);
        servicioInscripciones.aprobarMateria(
                alumno,
                carrera,
                materia
        );
    }

    public EstadoInscripcion consultarEstadoDeCarrera(String padron, String codigoDeCarrera) {
        return servicioInscripciones
                .consultarEstadoDeCarrera(obtenerAlumnoExistente(padron), obtenerCarreraExistente(codigoDeCarrera));
    }

    public List<EstadoInscripcion> obtenerInscripciones(String padron) {
        return servicioInscripciones
                .obtenerInscripciones(obtenerAlumnoExistente(padron));
    }

    public List<Materia> obtenerMateriasAprobadas(String padron, String codigoCarrera) {
        return servicioInscripciones
                .obtenerInscripcion(obtenerAlumnoExistente(padron), obtenerCarreraExistente(codigoCarrera))
                .getMateriasAprobadas();
    }

    private Alumno obtenerAlumnoExistente(String padron) {
        return repositorioAlumno.obtener(padron);
    }

    private Carrera obtenerCarreraExistente(String codigoCarrera) {
        return repositorioCarrera.obtener(codigoCarrera);
    }

    private Materia obtenerMateriaExistente(String codigoMateria) {
        return repositorioMateria.obtener(codigoMateria);
    }

}

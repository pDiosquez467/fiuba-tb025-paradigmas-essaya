package ejercicio1.app;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.Carrera;
import ejercicio1.modelo.EstadoCarreraAlumno;
import ejercicio1.modelo.fabrica.FabricaDeAlumno;
import ejercicio1.modelo.fabrica.FabricaDeCarrera;
import ejercicio1.repositorio.RepositorioAlumnos;
import ejercicio1.repositorio.RepositorioCarreras;
import ejercicio1.servicio.ServicioInscripciones;

public class Sistema {

    private RepositorioAlumnos repositorioAlumnos;

    private RepositorioCarreras repositorioCarreras;

    private ServicioInscripciones servicioInscripciones;

    public void agregarAlumno(String padron, String nombre) {
        repositorioAlumnos.agregar(FabricaDeAlumno.crear(padron, nombre));
    }

    public void agregarCarrera(String codigo, String nombre, int creditosMinimos) {
        repositorioCarreras.agregar(FabricaDeCarrera.crear(codigo, nombre, creditosMinimos));
    }

    public void inscribirAlumnoEnCarrera(String padron, String codigoDeCarrera) {
        Alumno alumno   = repositorioAlumnos.obtener(padron);
        Carrera carrera = repositorioCarreras.obtener(codigoDeCarrera);
        servicioInscripciones.inscribir(alumno, carrera);
    }

    public void aprobarMateria(String padron, String codigoDeCarrera, String codigoDeMateria) {
        // TODO: implementar RepositorioDeMaterias...
    }

    public EstadoCarreraAlumno consultarEstadoDeCarrera(String padron, String codigoDeCarrera) {
        Alumno alumno   = repositorioAlumnos.obtener(padron);
        Carrera carrera = repositorioCarreras.obtener(codigoDeCarrera);
        return servicioInscripciones.consultarEstadoDeCarrera(alumno, carrera);
    }
}

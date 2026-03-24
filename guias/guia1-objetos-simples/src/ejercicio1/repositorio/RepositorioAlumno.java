package ejercicio1.repositorio;

import ejercicio1.modelo.Alumno;

import java.util.HashMap;
import java.util.Map;

public class RepositorioAlumno {
    private final Map<String, Alumno> db;

    public RepositorioAlumno() {
        this.db = new HashMap<>();
    }

    public void agregar(Alumno alumno) {
        validarNoExistenciaEnBD(alumno.padron());
        db.put(alumno.padron(), alumno);
    }

    public void remover(Alumno alumno) {
        validarExistenciaEnBD(alumno.padron());
        db.remove(alumno.padron());
    }

    public Alumno obtener(String padron) {
        validarExistenciaEnBD(padron);
        return db.get(padron);
    }

    public boolean existe(String padron) {
        return db.containsKey(padron);
    }

    private void validarExistenciaEnBD(String padron) {
        if (!existe(padron)) {
            throw new RuntimeException("El alumno no existe en la base de datos");
        }
    }

    private void validarNoExistenciaEnBD(String padron) {
        if (existe(padron)) {
            throw new RuntimeException("El alumno existe en la base de datos");
        }
    }
}

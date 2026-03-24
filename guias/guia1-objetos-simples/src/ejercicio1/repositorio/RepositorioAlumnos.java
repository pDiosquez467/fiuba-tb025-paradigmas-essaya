package ejercicio1.repositorio;

import ejercicio1.modelo.Alumno;

import java.util.HashMap;
import java.util.Map;

public class RepositorioAlumnos {
    private final Map<String, Alumno> db;

    public RepositorioAlumnos() {
        this.db = new HashMap<>();
    }

    public void agregar(Alumno alumno) {
        db.put(alumno.padron(), alumno);
    }

    public void remover(Alumno alumno) {
        db.remove(alumno.padron());
    }

    public Alumno obtener(String padron) {
        if (!existe(padron)) {
            return null;
        }
        return db.get(padron);
    }

    public boolean existe(String padron) {
        return db.containsKey(padron);
    }
}

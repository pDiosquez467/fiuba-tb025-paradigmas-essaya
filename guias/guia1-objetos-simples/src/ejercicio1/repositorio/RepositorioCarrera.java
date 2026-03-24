package ejercicio1.repositorio;

import ejercicio1.modelo.Carrera;

import java.util.HashMap;
import java.util.Map;

public class RepositorioCarrera {
    private final Map<String, Carrera> db;

    public RepositorioCarrera() {
        this.db = new HashMap<>();
    }

    public void agregar(Carrera carrera) {
        validarNoExistenciaEnBD(carrera.codigo());
        db.put(carrera.codigo(), carrera);
    }

    public void remover(Carrera carrera) {
        validarExistenciaEnBD(carrera.codigo());
        db.remove(carrera.codigo());
    }

    public Carrera obtener(String codigo) {
        validarExistenciaEnBD(codigo);
        return db.get(codigo);
    }

    public boolean existe(String codigo) {
        return db.containsKey(codigo);
    }

    private void validarExistenciaEnBD(String codigo) {
        if (!existe(codigo)) {
            throw new RuntimeException("La carrera no existe en la base de datos");
        }
    }

    private void validarNoExistenciaEnBD(String codigo) {
        if (existe(codigo)) {
            throw new RuntimeException("La carrera existe en la base de datos");
        }
    }
}

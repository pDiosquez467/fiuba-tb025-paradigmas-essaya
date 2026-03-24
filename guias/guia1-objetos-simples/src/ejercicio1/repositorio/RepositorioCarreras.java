package ejercicio1.repositorio;

import ejercicio1.modelo.Carrera;

import java.util.HashMap;
import java.util.Map;

public class RepositorioCarreras {
    private final Map<String, Carrera> db;

    public RepositorioCarreras() {
        this.db = new HashMap<>();
    }

    public void agregar(Carrera carrera) {
        db.put(carrera.codigo(), carrera);
    }

    public void remover(Carrera carrera) {
        db.remove(carrera.codigo());
    }

    public Carrera obtener(String codigo) {
        if (!existe(codigo)) {
            return null;
        }
        return db.get(codigo);
    }

    public boolean existe(String codigo) {
        return db.containsKey(codigo);
    }

}

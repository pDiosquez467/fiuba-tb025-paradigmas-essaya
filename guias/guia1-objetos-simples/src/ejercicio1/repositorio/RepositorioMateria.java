package ejercicio1.repositorio;

import ejercicio1.modelo.Alumno;
import ejercicio1.modelo.Materia;

import java.util.HashMap;
import java.util.Map;

public class RepositorioMateria {

    private final Map<String, Materia> db;

    public RepositorioMateria() {
        this.db = new HashMap<>();
    }

    public void agregar(Materia materia) {
        validarNoExistenciaEnBD(materia.codigo());
        db.put(materia.codigo(), materia);
    }

    public void remover(Materia materia) {
        validarExistenciaEnBD(materia.codigo());
        db.remove(materia.codigo());
    }

    public Materia obtener(String codigo) {
        validarExistenciaEnBD(codigo);
        return db.get(codigo);
    }

    public boolean existe(String codigo) {
        return db.containsKey(codigo);
    }

    private void validarExistenciaEnBD(String codigo) {
        if (!existe(codigo)) {
            throw new RuntimeException("La materia no existe en la base de datos");
        }
    }

    private void validarNoExistenciaEnBD(String codigo) {
        if (existe(codigo)) {
            throw new RuntimeException("La materia existe en la base de datos");
        }
    }
}

package ejercicio4.repositorio;

import ejercicio4.modelo.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioUsuario {

    private final Map<String, Usuario> db;

    public RepositorioUsuario() {
        this.db = new HashMap<>();
    }

    public void agregar(Usuario usuario) {
        validarNoExistenciaEnLaBD(usuario.id());
        db.put(usuario.id(), usuario);
    }

    public void quitar(String id) {
        validarExistenciaEnLaBD(id);
        db.remove(id);
    }

    public Optional<Usuario> obtener(String id) {
        Usuario us = db.get(id);
        return (us == null) ? Optional.empty() : Optional.of(us);
    }

    public boolean existe(String id) {
        return db.containsKey(id);
    }

    private void validarExistenciaEnLaBD(String id) {
        if (!db.containsKey(id)) {
            throw new RuntimeException("El usuario no existe en la BD");
        }
    }

    private void validarNoExistenciaEnLaBD(String id) {
        if (db.containsKey(id)) {
            throw new RuntimeException("El usuario existe en la BD");
        }
    }
}

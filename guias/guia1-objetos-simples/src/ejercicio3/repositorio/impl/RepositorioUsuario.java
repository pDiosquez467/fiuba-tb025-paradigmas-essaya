package ejercicio3.repositorio.impl;

import ejercicio3.modelo.us.Usuario;
import ejercicio3.repositorio.Repositorio;
import ejercicio3.repositorio.shared.ValidacionesDePersistencia;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioUsuario implements Repositorio<Usuario> {

    private final Map<String, Usuario> db;

    public RepositorioUsuario() {
        this.db = new HashMap<>();
    }

    @Override
    public void agregar(Usuario usuario) {
        ValidacionesDePersistencia.validarNoExistencia(this, usuario.getId(), "El usuario existe en la DB");
        db.put(usuario.getId(), usuario);
    }

    @Override
    public Usuario quitar(String id) {
        ValidacionesDePersistencia.validarExistencia(this, id, "El usuario no existe en la DB");
        return db.remove(id);
    }

    @Override
    public boolean existe(String id) {
        return db.containsKey(id);
    }

    @Override
    public Optional<Usuario> obtener(String id) {
        return Optional.ofNullable(db.get(id));
    }
}

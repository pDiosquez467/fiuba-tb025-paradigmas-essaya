package ejercicio2.repositorio.impl;

import ejercicio2.modelo.Usuario;
import ejercicio2.repositorio.Repositorio;
import ejercicio2.repositorio.validaciones.Validaciones;

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
        Validaciones.validarNoExistenciaEnLaBD(this, usuario.id(), "El usuario existe en la BD");
        db.put(usuario.id(), usuario);
    }

    @Override
    public void remover(String id) {
        Validaciones.validarExistenciaEnLaBD(this, id, "El usuario no existe en la BD");
        db.remove(id);
    }

    @Override
    public Optional<Usuario> obtener(String id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public boolean existe(String id) {
        return db.containsKey(id);
    }
}

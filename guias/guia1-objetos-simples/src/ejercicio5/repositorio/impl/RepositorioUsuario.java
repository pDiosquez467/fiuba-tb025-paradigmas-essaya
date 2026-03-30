package ejercicio5.repositorio.impl;

import ejercicio5.modelo.Usuario;
import ejercicio5.repositorio.Repositorio;
import ejercicio5.repositorio.validaciones.Validaciones;

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
        Validaciones.validarNoExistenciaEnLaBD(this, usuario.getId(), "El usuario existe en la BD");
        db.put(usuario.getId(), usuario);
    }

    @Override
    public void quitar(String id) {
        Validaciones.validarExistenciaEnLaBD(this, id, "El usuario no existe en la BD");
        db.remove(id);
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

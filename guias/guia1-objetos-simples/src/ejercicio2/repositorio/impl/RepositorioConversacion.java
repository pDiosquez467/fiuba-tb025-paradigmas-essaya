package ejercicio2.repositorio.impl;

import ejercicio2.modelo.Conversacion;
import ejercicio2.modelo.Usuario;
import ejercicio2.repositorio.Repositorio;
import ejercicio2.repositorio.validaciones.Validaciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RepositorioConversacion implements Repositorio<Conversacion> {

    private final Map<String, Conversacion> db;

    public RepositorioConversacion() {
        this.db = new HashMap<>();
    }

    @Override
    public void agregar(Conversacion conversacion) {
        Validaciones.validarNoExistenciaEnLaBD(this, conversacion.getId(), "La conversación ya existe en la BD.");
        Validaciones.validarCondicion(
                existeConversacionEntre(
                        conversacion.getParticipanteA(),
                        conversacion.getParticipanteB()),
                "Ya existe la conversación entre los usuarios dados.");
        db.put(conversacion.getId(), conversacion);
    }

    @Override
    public void remover(String id) {
        Validaciones.validarExistenciaEnLaBD(this, id, "La conversación no existe en la BD.");
        db.remove(id);
    }

    @Override
    public Optional<Conversacion> obtener(String id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public boolean existe(String id) {
        return db.containsKey(id);
    }

    public Optional<Conversacion> obtener(Usuario usuario, Usuario otro) {
        return db.values().stream()
                .filter(conversacion -> conversacion.involucra(usuario, otro))
                .findFirst();
    }

    public boolean existeConversacionEntre(Usuario usuario, Usuario otro) {
        return db.values().stream()
                .anyMatch(conversacion -> conversacion.involucra(usuario, otro));
    }

    public List<Conversacion> obtenerConversacionesDelUsuario(Usuario usuario) {
        return db.values().stream()
                .filter(conversacion -> conversacion.participa(usuario))
                .toList();
    }

}

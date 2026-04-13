package ejercicio3.repositorio.impl;

import ejercicio3.modelo.mensaje.Mensaje;
import ejercicio3.modelo.us.Usuario;
import ejercicio3.repositorio.Repositorio;
import ejercicio3.repositorio.shared.ValidacionesDePersistencia;

import java.util.*;
import java.util.function.Predicate;

public class RepositorioMensaje implements Repositorio<Mensaje> {

    private final Map<String, Mensaje> db;

    public RepositorioMensaje() {
        this.db = new HashMap<>();
    }

    @Override
    public void agregar(Mensaje mensaje) {
        ValidacionesDePersistencia.validarNoExistencia(this, mensaje.id(), "El mensaje existe en la DB");
        db.put(mensaje.id(), mensaje);
    }

    @Override
    public Mensaje quitar(String id) {
        ValidacionesDePersistencia.validarExistencia(this, id, "El mensaje no existe en la DB");
        return db.remove(id);
    }

    @Override
    public boolean existe(String id) {
        return db.containsKey(id);
    }

    @Override
    public Optional<Mensaje> obtener(String id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<Mensaje> obtenerMensajesEnviados(Usuario us) {
        return obtenerMensajes(
                mensaje -> mensaje.remitente().equals(us)
        );
    }

    public List<Mensaje> obtenerMensajesRecibidos(Usuario us) {
        return obtenerMensajes(
                mensaje -> mensaje.destinatario().equals(us)
        );
    }

    private List<Mensaje> obtenerMensajes(Predicate<Mensaje> pred) {
        return db.values().stream()
                .filter(pred)
                .sorted(Comparator.comparing(Mensaje::fecha).reversed())
                .toList();
    }


}

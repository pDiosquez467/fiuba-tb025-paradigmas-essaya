package ejercicio3.servicio;

import ejercicio3.modelo.mensaje.Mensaje;
import ejercicio3.modelo.us.Usuario;
import ejercicio3.repositorio.impl.RepositorioMensaje;
import ejercicio3.repositorio.impl.RepositorioUsuario;
import ejercicio3.servicio.shared.Util;
import validaciones.Validaciones;

import java.util.List;
import java.util.UUID;

import java.time.LocalDateTime;

public class ServicioMensajeria {

    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioMensaje repositorioMensaje;

    public ServicioMensajeria(
            RepositorioUsuario repositorioUsuario,
            RepositorioMensaje repositorioMensaje
    ) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioMensaje = repositorioMensaje;
    }

    public Mensaje enviarMensaje(
            String idRemitente,
            String idDestinatario,
            LocalDateTime fecha,
            String contenido) {
        Validaciones.validarNotBlank(idRemitente, "idRemitente");
        Validaciones.validarNotBlank(idDestinatario, "idDestinatario");
        Validaciones.validarNotNull(fecha, "fecha");
        Validaciones.validarNotBlank(contenido, "contenido");
        Usuario remitente    = obtenerUsuarioExistente(idRemitente);
        Usuario destinatario = obtenerUsuarioExistente(idDestinatario);
        Mensaje nuevoMensaje = new Mensaje(
                UUID.randomUUID().toString(),
                remitente,
                destinatario,
                fecha,
                contenido
        );
        repositorioMensaje.agregar(nuevoMensaje);
        return nuevoMensaje;
    }

    public List<Mensaje> obtenerMensajesEnviados(String idUsuario) {
        Usuario usuario = obtenerUsuarioExistente(idUsuario);
        return repositorioMensaje.obtenerMensajesEnviados(usuario);
    }

    public List<Mensaje> obtenerMensajesRecibidos(String idUsuario) {
        Usuario usuario = obtenerUsuarioExistente(idUsuario);
        return repositorioMensaje.obtenerMensajesRecibidos(usuario);
    }

    private Usuario obtenerUsuarioExistente(String idUsuario) {
        return Util.obtenerExistente(repositorioUsuario, idUsuario, "No existe un usuario con el identificador proporcionado.");
    }

    private Mensaje obtenerMensajeExistente(String idUsuario) {
        return Util.obtenerExistente(repositorioMensaje, idUsuario, "No existe un mensaje con el identificador proporcionado.");
    }

}

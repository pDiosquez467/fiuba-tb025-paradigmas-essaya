package ejercicio2.servicio;

import ejercicio2.modelo.Conversacion;
import ejercicio2.modelo.Mensaje;
import ejercicio2.modelo.Usuario;
import ejercicio2.modelo.fabrica.FabricaConversacion;
import ejercicio2.modelo.fabrica.FabricaMensaje;
import ejercicio2.repositorio.Repositorio;
import ejercicio2.repositorio.impl.RepositorioConversacion;
import ejercicio2.repositorio.impl.RepositorioUsuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ServicioConversacion {

    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioConversacion repositorioConversacion;

    public ServicioConversacion(
            RepositorioUsuario repositorioUsuario,
            RepositorioConversacion repositorioConversacion
    ) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioConversacion = repositorioConversacion;
    }

    public Mensaje enviarMensaje(
            String idRemitente,
            String idDestinatario,
            String contenido,
            LocalDateTime fecha
    ) {
        validarUsuariosDistintos(idRemitente, idDestinatario);
        validarContenido(contenido);
        validarFecha(fecha);

        Usuario remitente = obtenerUsuarioExistente(idRemitente);
        Usuario destinatario = obtenerUsuarioExistente(idDestinatario);
        Conversacion conversacion = obtenerORegistrarConversacion(remitente, destinatario);

        Mensaje nuevo = FabricaMensaje.crear(
                UUID.randomUUID().toString(),
                remitente,
                destinatario,
                fecha,
                contenido
        );
        conversacion.agregarMensaje(nuevo);
        return nuevo;
    }

    public List<Mensaje> obtenerHistorialDeMensajesEntre(String idUsuarioA, String idUsuarioB) {
        validarUsuariosDistintos(idUsuarioA, idUsuarioB);

        Usuario usuarioA = obtenerUsuarioExistente(idUsuarioA);
        Usuario usuarioB = obtenerUsuarioExistente(idUsuarioB);

        return obtenerConversacionExistenteEntre(usuarioA, usuarioB)
                .getHistorial();
    }

    public List<Conversacion> obtenerConversacionesDelUsuario(String idUsuario) {
        return repositorioConversacion.obtenerConversacionesDelUsuario(
                obtenerUsuarioExistente(idUsuario)
        );
    }

    private Usuario obtenerUsuarioExistente(String id) {
        return obtenerExistente(
                repositorioUsuario,
                id,
                "No existe un usuario con el identificador proporcionado."
        );
    }

    private Conversacion obtenerConversacionExistenteEntre(Usuario usuarioA, Usuario usuarioB) {
        Optional<Conversacion> conversacion = repositorioConversacion.obtener(usuarioA, usuarioB);
        if (conversacion.isEmpty()) {
            throw new RuntimeException("No existe una conversación entre los usuarios proporcionados.");
        }
        return conversacion.get();
    }

    private Conversacion obtenerORegistrarConversacion(Usuario usuarioA, Usuario usuarioB) {
        Optional<Conversacion> conversacion = repositorioConversacion.obtener(usuarioA, usuarioB);
        if (conversacion.isPresent()) {
            return conversacion.get();
        }

        Conversacion nueva = FabricaConversacion.crear(
                UUID.randomUUID().toString(),
                usuarioA,
                usuarioB
        );
        repositorioConversacion.agregar(nueva);
        return nueva;
    }

    private <T> T obtenerExistente(Repositorio<T> repositorio, String id, String mensajeError) {
        Optional<T> wrapper = repositorio.obtener(id);
        if (wrapper.isEmpty()) {
            throw new RuntimeException(mensajeError);
        }
        return wrapper.get();
    }

    private void validarUsuariosDistintos(String idUsuarioA, String idUsuarioB) {
        if (idUsuarioA.equals(idUsuarioB)) {
            throw new RuntimeException("El remitente y el destinatario deben ser usuarios distintos.");
        }
    }

    private void validarContenido(String contenido) {
        if (contenido == null || contenido.isBlank()) {
            throw new RuntimeException("El contenido del mensaje no puede ser nulo ni estar vacío.");
        }
    }

    private void validarFecha(LocalDateTime fecha) {
        if (fecha == null) {
            throw new RuntimeException("La fecha de envío del mensaje no puede ser nula.");
        }
    }
}
package ejercicio3.app;

import ejercicio3.modelo.mensaje.Mensaje;
import ejercicio3.modelo.sesion.SesionActual;
import ejercicio3.modelo.us.Cargo;
import ejercicio3.modelo.us.Huesped;
import ejercicio3.modelo.us.Personal;
import ejercicio3.modelo.us.Usuario;
import ejercicio3.repositorio.impl.RepositorioUsuario;
import ejercicio3.servicio.ServicioAutenticacion;
import ejercicio3.servicio.ServicioMensajeria;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Cartelera {

    private final ServicioAutenticacion servicioAutenticacion;
    private final ServicioMensajeria servicioMensajeria;
    private final RepositorioUsuario repositorioUsuario;

    public Cartelera(
            ServicioMensajeria servicioMensajeria,
            RepositorioUsuario repositorioUsuario
    ) {
        SesionActual sesionActual  = SesionActual.crearSesion();
        this.servicioAutenticacion = ServicioAutenticacion.crearServicio(repositorioUsuario, sesionActual);
        this.servicioMensajeria    = servicioMensajeria;
        this.repositorioUsuario    = repositorioUsuario;
    }

    public Usuario crearUsuarioPersonal(
            String nombre,
            String credencial,
            Cargo cargo) {

        Usuario usuario = new Personal(
                UUID.randomUUID().toString(),
                nombre,
                credencial,
                cargo
        );
        repositorioUsuario.agregar(usuario);
        return usuario;
    }

    public Usuario crearUsuarioHuesped(
            String nombre,
            String credencial,
            int nroDeHabitacion) {

        Usuario usuario = new Huesped(
                UUID.randomUUID().toString(),
                nombre,
                credencial,
                nroDeHabitacion
        );
        repositorioUsuario.agregar(usuario);
        return usuario;
    }

    public Usuario abrirSesion(String idUsuario, String credencial) {
        return servicioAutenticacion.abrirSesion(idUsuario, credencial);
    }

    public Usuario cerrarSesion() {
        return servicioAutenticacion.cerrarSesion();
    }

    public void cerrarSistema() {
        servicioAutenticacion.cerrarSistema();
        System.exit(0);
    }

    public Mensaje enviarMensaje(String idDestinatario,
                                 String contenido) {
        String idUsuarioActual = obtenerIdUsuarioActual();
        LocalDateTime now      = LocalDateTime.now();
        return servicioMensajeria.enviarMensaje(
                idUsuarioActual,
                idDestinatario,
                now,
                contenido
        );
    }

    public List<Mensaje> obtenerMensajesEnviados() {
        return servicioMensajeria.obtenerMensajesEnviados(
                obtenerIdUsuarioActual()
        );
    }

    public List<Mensaje> obtenerMensajesRecibidos() {
        return servicioMensajeria.obtenerMensajesRecibidos(
                obtenerIdUsuarioActual()
        );
    }

    private String obtenerIdUsuarioActual() {
        return servicioAutenticacion
                .obtenerUsuarioAutenticadoActual()
                .getId();
    }

}

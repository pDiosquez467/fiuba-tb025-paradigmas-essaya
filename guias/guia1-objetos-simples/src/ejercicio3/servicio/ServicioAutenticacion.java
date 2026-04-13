package ejercicio3.servicio;

import ejercicio3.modelo.sesion.SesionActual;
import ejercicio3.modelo.us.Usuario;
import ejercicio3.repositorio.impl.RepositorioUsuario;
import ejercicio3.servicio.shared.Util;
import validaciones.Validaciones;

public class ServicioAutenticacion {

    private final RepositorioUsuario repositorioUsuario;
    private final SesionActual sesionActual;

    public ServicioAutenticacion(
            RepositorioUsuario repositorioUsuario,
            SesionActual sesionActual) {
        this.repositorioUsuario = repositorioUsuario;
        this.sesionActual = sesionActual;
    }

    public Usuario abrirSesion(String idUsuario, String credencial) {
        Validaciones.validarNotBlank(idUsuario, "El identificador del usuario es obligatorio.");
        Validaciones.validarNotBlank(credencial, "La credencial de acceso es obligatoria.");

        Usuario usuario = autenticar(idUsuario, credencial);
        sesionActual.abrir(usuario);
        return usuario;
    }

    public Usuario cerrarSesion() {
        return sesionActual.cerrar();
    }

    public boolean haySesionAbierta() {
        return sesionActual.estaAbierta();
    }

    public Usuario obtenerUsuarioAutenticadoActual() {
        return sesionActual.obtenerUsuarioActual();
    }

    public void cerrarSistema() {
        validarSesionAbierta();
        Usuario usuarioAutenticado = obtenerUsuarioAutenticadoActual();
        validarPermisosDelUsuario(usuarioAutenticado);
        cerrarSesion();
        // System.exit(0);
    }

    private Usuario obtenerUsuarioExistente(String idUsuario) {
        return Util.obtenerExistente(
                repositorioUsuario,
                idUsuario,
                "No existe un usuario con el identificador proporcionado."
        );
    }

    private Usuario autenticar(String idUsuario, String credencial) {
        Usuario usuario = obtenerUsuarioExistente(idUsuario);
        validarCredenciales(usuario, credencial);
        return usuario;
    }

    private void validarCredenciales(Usuario usuario, String credencial) {
        if (!usuario.getCredencial().equals(credencial)) {
            throw new RuntimeException("La credencial proporcionada no es válida para el usuario indicado.");
        }
    }

    private void validarPermisosDelUsuario(Usuario usuario) {
        if (!usuario.puedeCerrarSistema()) {
            throw new RuntimeException("El usuario autenticado no posee permisos para cerrar el sistema.");
        }
    }

    private void validarSesionAbierta() {
        if (!haySesionAbierta()) {
            throw new RuntimeException("No existe una sesión activa en este momento.");
        }
    }
}
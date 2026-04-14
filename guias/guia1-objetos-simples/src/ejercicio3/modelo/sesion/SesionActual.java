package ejercicio3.modelo.sesion;

import ejercicio3.modelo.us.Usuario;
import validaciones.Validaciones;

public class SesionActual {
    private Usuario usuario;
    private EstadoSesionActual estado;

    public static SesionActual crearSesion() {
        return new SesionActual();
    }

    private SesionActual() {
        this.usuario = null;
        this.estado  = EstadoSesionActual.CERRADA;
    }

    public boolean estaAbierta() {
        return this.estado == EstadoSesionActual.ABIERTA;
    }

    public void abrir(Usuario usuario) {
        Validaciones.validarNotNull(usuario, "El usuario no debe ser null.");
        validarEstadoSesionCerrado();
        this.usuario = usuario;
        this.estado  = EstadoSesionActual.ABIERTA;
    }

    public Usuario cerrar() {
        validarEstadoSesionAbierto();
        Usuario oldie = this.usuario;
        this.estado = EstadoSesionActual.CERRADA;
        this.usuario  = null;
        return oldie;
    }

    public Usuario obtenerUsuarioActual() {
        validarEstadoSesionAbierto();
        return this.usuario;
    }

    public EstadoSesionActual consultarEstado() {
        return this.estado;
    }

    private void validarEstadoSesionAbierto() {
        if (!estaAbierta()) {
            throw new RuntimeException("No hay una sesión abierta en este momento.");
        }
    }

    private void validarEstadoSesionCerrado() {
        if (estaAbierta()) {
            throw new RuntimeException("Ya existe una sesión abierta en el sistema.");
        }
    }
}

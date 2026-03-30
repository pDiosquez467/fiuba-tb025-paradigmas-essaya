package ejercicio4.servicio;

import ejercicio4.modelo.Usuario;
import ejercicio4.repositorio.RepositorioUsuario;

import java.util.Optional;

public class ServicioUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final ServicioDePrestamos servicioDePrestamos;

    public ServicioUsuario(RepositorioUsuario repositorioUsuario, ServicioDePrestamos servicioDePrestamos) {
        this.repositorioUsuario = repositorioUsuario;
        this.servicioDePrestamos = servicioDePrestamos;
    }

    public void agregar(Usuario usuario) {
        repositorioUsuario.agregar(usuario);
    }

    public void quitar(String id) {
        validarQueElUsuarioNoTengaPrestamosActivos(id);
        repositorioUsuario.quitar(id);
    }

    public Optional<Usuario> obtener(String id) {
        return repositorioUsuario.obtener(id);
    }

    private void validarQueElUsuarioNoTengaPrestamosActivos(String id) {
        if (servicioDePrestamos.tieneElUsuarioPrestamosActivos(id)) {
            throw new RuntimeException("El usuario tiene préstamos activos");
        }
    }
}

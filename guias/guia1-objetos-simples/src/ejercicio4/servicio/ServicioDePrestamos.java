package ejercicio4.servicio;

import ejercicio4.modelo.LibroEnBiblioteca;
import ejercicio4.modelo.Prestamo;
import ejercicio4.modelo.Usuario;
import ejercicio4.repositorio.Repositorio;
import ejercicio4.repositorio.RepositorioLibroEnBiblioteca;
import ejercicio4.repositorio.RepositorioPrestamo;
import ejercicio4.repositorio.RepositorioUsuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ServicioDePrestamos {

    private final RepositorioPrestamo repositorioPrestamo;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioLibroEnBiblioteca repositorioLibroEnBiblioteca;

    public ServicioDePrestamos(
            RepositorioPrestamo repositorioPrestamo,
            RepositorioUsuario repositorioUsuario,
            RepositorioLibroEnBiblioteca repositorioLibroEnBiblioteca
    ) {
        this.repositorioPrestamo = repositorioPrestamo;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioLibroEnBiblioteca = repositorioLibroEnBiblioteca;
    }

    public void prestarLibro(String usuarioId, String isbn, LocalDate fechaActual) {
        Usuario usuario         = obtenerUsuarioExistente(usuarioId);
        LibroEnBiblioteca libro = obtenerLibroExistente(isbn);
        validarNoDuplicacionDePrestamo(usuarioId, isbn);
        validarStockDisponible(libro);
        Prestamo prestamo = new Prestamo(
                usuario,
                libro.getLibro(),
                fechaActual
        );
        libro.quitar();
        repositorioPrestamo.agregar(prestamo);
    }

    public Prestamo devolverLibro(
            String usuarioId,
            String isbn,
            LocalDate fechaDePrestamo,
            LocalDate fechaActual) {
        Prestamo prestamo =
                obtenerPrestamoExistente(usuarioId, isbn, fechaDePrestamo);
        LibroEnBiblioteca libroEnBiblioteca =
                obtenerLibroExistente(prestamo.getLibro().isbn());
        prestamo.finalizar(fechaActual);
        libroEnBiblioteca.agregar();
        return prestamo;
    }

    public Prestamo renovarLibro(
            String usuarioId,
            String isbn,
            LocalDate fechaDePrestamo,
            LocalDate fechaActual) {
        Prestamo prestamo =
                obtenerPrestamoExistente(usuarioId, isbn, fechaDePrestamo);
        prestamo.renovar(fechaActual);
        return prestamo;
    }

    public List<Prestamo> obtenerPrestamosDelUsuario(String usuarioId) {
        return repositorioPrestamo.obtenerPrestamosDelUsuario(usuarioId);
    }

    public List<Prestamo> obtenerPrestamosActivosDelUsuario(String usuarioId) {
        return repositorioPrestamo.obtenerPrestamosActivosDelUsuario(usuarioId);
    }

    public List<Prestamo> obtenerPrestamosDelLibro(String isbn) {
        return repositorioPrestamo.obtenerPrestamosDeLibro(isbn);
    }

    private Usuario obtenerUsuarioExistente(String usuarioId) {
        return obtener(usuarioId, repositorioUsuario, "El usuario no existe en la BD");
    }

    private LibroEnBiblioteca obtenerLibroExistente(String isbn) {
        return obtener(isbn, repositorioLibroEnBiblioteca, "El libro no existe en la BD");
    }

    private Prestamo obtenerPrestamoExistente(String usuarioId, String isbn, LocalDate fechaDePrestamo) {
        Optional<Prestamo> prestamo = repositorioPrestamo.obtener(usuarioId, isbn, fechaDePrestamo);
        if (prestamo.isEmpty()) {
            throw new RuntimeException("El préstamo no existe en la BD");
        }
        return prestamo.get();
    }

    private <T> T obtener(String id, Repositorio<T> repositorio, String mensajeError) {
        Optional<T> wrapper = repositorio.obtener(id);
        if (wrapper.isEmpty()) {
            throw new RuntimeException(mensajeError);
        }
        return wrapper.get();
    }

    private void validarNoDuplicacionDePrestamo(String usuarioId, String isbn) {
        if (repositorioPrestamo.tieneElUsuarioPrestamoActivoDeEsteLibro(usuarioId, isbn)) {
            throw new RuntimeException("El usuario tiene un préstamo activo de este libro");
        }
    }

    private void validarStockDisponible(LibroEnBiblioteca libroEnBiblioteca) {
        if (!libroEnBiblioteca.hayStock()) {
            throw new RuntimeException("El libro dado no tiene disponibilidad");
        }
    }
}

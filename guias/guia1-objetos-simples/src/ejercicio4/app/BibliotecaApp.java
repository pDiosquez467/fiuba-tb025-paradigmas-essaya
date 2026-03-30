package ejercicio4.app;

import ejercicio4.modelo.*;
import ejercicio4.modelo.fabrica.FabricaLibroEnBiblioteca;
import ejercicio4.modelo.fabrica.FabricaUsuario;
import ejercicio4.modelo.validaciones.Validaciones;
import ejercicio4.servicio.ServicioDeCatalogo;
import ejercicio4.servicio.ServicioDePrestamos;
import ejercicio4.servicio.ServicioUsuario;

import java.time.LocalDate;
import java.util.List;

public class BibliotecaApp {

    private final ServicioUsuario servicioUsuario;
    private final ServicioDeCatalogo servicioDeCatalogo;
    private final ServicioDePrestamos servicioDePrestamos;

    public BibliotecaApp(
            ServicioUsuario servicioUsuario,
            ServicioDeCatalogo servicioDeCatalogo,
            ServicioDePrestamos servicioDePrestamos
    ) {
        this.servicioUsuario = servicioUsuario;
        this.servicioDeCatalogo = servicioDeCatalogo;
        this.servicioDePrestamos = servicioDePrestamos;
    }

    public void agregarUsuario(
            String id,
            String nombre,
            String direccion,
            LocalDate fechaDeNacimiento) {
        servicioUsuario.agregar(
                FabricaUsuario.crear(
                        id,
                        nombre,
                        direccion,
                        fechaDeNacimiento)
        );
    }

    public void quitarUsuario(String id) {
        Validaciones.validarNotBlank(id, "id");
        servicioUsuario.quitar(id);
    }

    public void agregarLibro(
            String isbn,
            String titulo,
            LocalDate fechaDePublicacion,
            List<Autor> autores,
            int stock) {
        LibroEnBiblioteca nuevo = FabricaLibroEnBiblioteca.crear(
                isbn,
                titulo,
                fechaDePublicacion,
                autores,
                stock);
        servicioDeCatalogo.agregar(nuevo);
    }

    public void quitarLibro(String isbn) {
        Validaciones.validarNotBlank(isbn, "ISBN");
        servicioDeCatalogo.quitar(isbn);
    }

    public int consultarStock(String isbn) {
        Validaciones.validarNotBlank(isbn, "ISBN");
        return servicioDeCatalogo.consultarStock(isbn);
    }

    public List<LibroEnBiblioteca> consultarStockPorTitulo(String titulo) {
        Validaciones.validarNotBlank(titulo, "título");
        return servicioDeCatalogo.buscarPorTitulo(titulo);
    }

    public List<LibroEnBiblioteca> consultarStockPorFechaDePublicacion(LocalDate fechaDePublicacion) {
        Validaciones.validarNotNull(fechaDePublicacion, "fechaDePublicación");
        return servicioDeCatalogo.buscarPorFechaDePublicacion(fechaDePublicacion);
    }

    public List<LibroEnBiblioteca> consultarStockPorAutor(Autor autor) {
        Validaciones.validarNotNull(autor, "autor");
        return servicioDeCatalogo.buscarPorAutor(autor);
    }

    public void prestarLibro(String usuarioId, String isbn, LocalDate fecha) {
        Validaciones.validarNotBlank(usuarioId, "usuarioId");
        Validaciones.validarNotBlank(isbn, "ISBN");
        Validaciones.validarNotNull(fecha, "fecha");
        servicioDePrestamos.prestarLibro(usuarioId, isbn, fecha);
    }

    public Prestamo devolverLibro(
            String usuarioId,
            String isbn,
            LocalDate fechaDePrestamo,
            LocalDate fechaActual) {
        Validaciones.validarNotBlank(usuarioId, "usuarioId");
        Validaciones.validarNotBlank(isbn, "ISBN");
        Validaciones.validarNotNull(fechaDePrestamo, "fechaDePréstamo");
        Validaciones.validarNotNull(fechaActual, "fechaActual");
        return servicioDePrestamos.devolverLibro(usuarioId, isbn, fechaDePrestamo, fechaActual);
    }

    public Prestamo renovarLibro(
            String usuarioId,
            String isbn,
            LocalDate fechaDePrestamo,
            LocalDate fechaActual) {
        Validaciones.validarNotBlank(usuarioId, "usuarioId");
        Validaciones.validarNotBlank(isbn, "ISBN");
        Validaciones.validarNotNull(fechaDePrestamo, "fechaDePréstamo");
        Validaciones.validarNotNull(fechaActual, "fechaActual");
        return servicioDePrestamos.renovarLibro(usuarioId, isbn, fechaDePrestamo, fechaActual);
    }

    public List<Prestamo> obtenerPrestamosDelUsuario(String usuarioId) {
        Validaciones.validarNotBlank(usuarioId, "usuarioId");
        return servicioDePrestamos.obtenerPrestamosDelUsuario(usuarioId);
    }

    public List<Libro> obtenerLibrosPrestadosAlUsuario(String usuarioId) {
        Validaciones.validarNotBlank(usuarioId, "usuarioId");
        return servicioDePrestamos
                .obtenerPrestamosDelUsuario(usuarioId)
                .stream()
                .map(Prestamo::getLibro)
                .toList();
    }

}

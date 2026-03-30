package ejercicio4.servicio;

import ejercicio4.modelo.Autor;
import ejercicio4.modelo.LibroEnBiblioteca;
import ejercicio4.repositorio.RepositorioLibroEnBiblioteca;
import ejercicio4.repositorio.RepositorioPrestamo;

import java.time.LocalDate;
import java.util.List;

public class ServicioDeCatalogo {

    private final RepositorioLibroEnBiblioteca repositorioLibroEnBiblioteca;

    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioDeCatalogo(
            RepositorioLibroEnBiblioteca repositorioLibroEnBiblioteca,
            RepositorioPrestamo repositorioPrestamo
    ) {
        this.repositorioLibroEnBiblioteca = repositorioLibroEnBiblioteca;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void agregar(LibroEnBiblioteca libro) {
        repositorioLibroEnBiblioteca.agregar(libro);
    }

    public void quitar(String isbn) {
        validarNoExistenciaDePrestamosActivos(isbn);
        repositorioLibroEnBiblioteca.quitar(isbn);
    }

    public LibroEnBiblioteca obtenerLibro(String isbn) {
        return repositorioLibroEnBiblioteca
                .obtener(isbn)
                .orElseThrow(() -> new RuntimeException("No existe un libro en el catálogo con el ISBN proporcionado"));
    }

    public int consultarStock(String isbn) {
        return obtenerLibro(isbn)
                .getStock();
    }

    public void aumentarStock(String isbn) {
        aumentarStock(isbn, 1);
    }

    public void disminuirStock(String isbn) {
        disminuirStock(isbn, 1);
    }

    public void aumentarStock(String isbn, int cantidad) {
        validarCantidadPositivaEnStock(cantidad);
        obtenerLibro(isbn).agregar(cantidad);
    }

    public void disminuirStock(String isbn, int cantidad) {
        validarCantidadPositivaEnStock(cantidad);
        obtenerLibro(isbn).quitar(cantidad);
    }

    public List<LibroEnBiblioteca> buscarPorTitulo(String titulo) {
        return repositorioLibroEnBiblioteca.buscarPorTitulo(titulo);
    }

    public List<LibroEnBiblioteca> buscarPorAutor(Autor autor) {
        return repositorioLibroEnBiblioteca.buscarPorAutor(autor);
    }

    public List<LibroEnBiblioteca> buscarPorFechaDePublicacion(LocalDate fechaBuscada) {
        return repositorioLibroEnBiblioteca.buscarPorFechaDePublicacion(fechaBuscada);
    }

    private void validarNoExistenciaDePrestamosActivos(String isbn) {
        if (repositorioPrestamo.tieneElLibroPrestamosActivos(isbn)) {
            throw new RuntimeException("No se puede eliminar el libro porque posee préstamos activos");
        }
    }

    private void validarCantidadPositivaEnStock(int cantidad) {
        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser un número entero positivo");
        }
    }
}

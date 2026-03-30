package ejercicio4.modelo.fabrica;

import ejercicio4.modelo.Autor;
import ejercicio4.modelo.Libro;
import ejercicio4.modelo.LibroEnBiblioteca;
import ejercicio4.modelo.validaciones.Validaciones;

import java.time.LocalDate;
import java.util.List;

public class FabricaLibroEnBiblioteca {

    public static LibroEnBiblioteca crear(
            String isbn,
            String titulo,
            LocalDate fechaDePublicacion,
            List<Autor> autores,
            int stock) {
        Validaciones.validarNotBlank(isbn, "ISBN");
        Validaciones.validarNotBlank(titulo, "titulo");
        Validaciones.validarNotNull(fechaDePublicacion, "fechaDePublicación");
        Validaciones.validarNotNull(autores, "autores");
        Validaciones.validarNotEmpty(autores, "autores");
        Validaciones.validarNumeroPositivo(stock, "stock");
        Libro libro = new Libro(
                isbn,
                titulo,
                fechaDePublicacion,
                autores
        );
        return new LibroEnBiblioteca(
                libro,
                stock
        );
    }
}

package ejercicio4.modelo;

import java.util.Objects;

public class LibroEnBiblioteca {

    private final Libro libro;
    private int stock;

    public LibroEnBiblioteca(Libro libro, int stock) {
        this.libro = libro;
        this.stock = stock;
    }

    public LibroEnBiblioteca(Libro libro) {
        this(libro, 0);
    }

    public boolean hayStock() {
        return stock > 0;
    }

    public void agregar() {
        agregar(1);
    }

    public void quitar() {
        quitar(1);
    }

    public void agregar(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Debe ser 'cantidad' > 0");
        }
        stock += cantidad;
    }

    public void quitar(int cantidad) {
        if (cantidad <= 0 || stock - cantidad < 0) {
            throw new IllegalArgumentException("Debe ser 'stock' >= 'cantidad' > 0");
        }
        stock -= cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LibroEnBiblioteca that)) return false;
        return Objects.equals(libro, that.libro);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(libro);
    }

    public Libro getLibro() {
        return libro;
    }

    public int getStock() {
        return stock;
    }
}

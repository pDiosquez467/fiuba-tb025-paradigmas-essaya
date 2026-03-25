package ejercicio4.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {

    private final static int DIAS_PRESTAMO = 15;
    private final static int DIAS_EXTENSION = 7;

    private final Usuario usuario;
    private final Libro libro;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;
    private EstadoDelPrestamo estadoDelPrestamo;

    public Prestamo(
            Usuario usuario,
            Libro libro,
            LocalDate fechaPrestamo) {
        this.usuario = usuario;
        this.libro   = libro;
        this.fechaPrestamo   = fechaPrestamo;
        fechaVencimiento     = fechaPrestamo.plusDays(DIAS_PRESTAMO);
        this.fechaDevolucion = null;
        this.estadoDelPrestamo = EstadoDelPrestamo.ACTIVO;
    }

    public void finalizar(LocalDate fechaActual) {
        validarEstadoActivo();
        fechaDevolucion   = fechaActual;
        estadoDelPrestamo = EstadoDelPrestamo.FINALIZADO;
    }

    public void renovar(LocalDate fecha) {
        validarEstadoParaRenovacion(fecha);
        extenderPrestamo();
    }

    public boolean estaRetrasado(LocalDate fechaActual) {
        return estadoDelPrestamo == EstadoDelPrestamo.ACTIVO
                && fechaActual.isAfter(fechaVencimiento);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Prestamo prestamo)) return false;
        return Objects.equals(usuario, prestamo.usuario) && Objects.equals(libro, prestamo.libro) && Objects.equals(fechaPrestamo, prestamo.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, libro, fechaPrestamo);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public EstadoDelPrestamo getEstadoDelPrestamo() {
        return estadoDelPrestamo;
    }

    private void extenderPrestamo() {
        fechaVencimiento = fechaVencimiento.plusDays(DIAS_EXTENSION);
    }

    private void validarEstadoActivo() {
        if (estadoDelPrestamo != EstadoDelPrestamo.ACTIVO) {
            throw new RuntimeException("El préstamo no está activo");
        }
    }

    private void validarEstadoParaRenovacion(LocalDate fecha) {
        if (estadoDelPrestamo != EstadoDelPrestamo.ACTIVO
                || estaRetrasado(fecha)) {
            throw new RuntimeException("El préstamo no puede ser renovado");
        }
    }
}

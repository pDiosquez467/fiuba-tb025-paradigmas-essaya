package ejercicio4.repositorio;

import ejercicio4.modelo.Prestamo;

import java.time.LocalDate;
import java.util.*;

public class RepositorioPrestamo {

    private final Map<String, Set<Prestamo>> db;

    public RepositorioPrestamo() {
        this.db = new HashMap<>();
    }

    public void agregar(Prestamo prestamo) {
        String id = prestamo.getUsuario().id();
        if(!db.containsKey(id)) {
            db.put(id, new HashSet<>());
        }
        validarNoExistenciaDePrestamo(prestamo);
        db.get(id).add(prestamo);
    }

    public void remover(Prestamo prestamo) {
        String id = prestamo.getUsuario().id();
        validarExistenciaDePrestamosAlUsuario(id);
        validarExistenciaDePrestamo(prestamo);
        Set<Prestamo> prestamos = db.get(id);
        prestamos.remove(prestamo);
        if (prestamos.isEmpty()) {
            db.remove(id);
        }
    }

    public Optional<Prestamo> obtener(String id, String isbn, LocalDate fecha) {
        if (!db.containsKey(id)) return Optional.empty();
        return db.get(id).stream()
                .filter(p
                        -> p.getLibro().isbn().equalsIgnoreCase(isbn)
                        && p.getFechaPrestamo().equals(fecha))
                .findFirst();
    }

    public List<Prestamo> obtenerTodos() {
        List<Prestamo> todos = new ArrayList<>();
        for (String id: db.keySet()) {
            todos.addAll(db.get(id));
        }
        return todos;
    }

    public List<Prestamo> obtenerPrestamosDeLibro(String isbn) {
        List<Prestamo> prestamos = new ArrayList<>();
        for (String id: db.keySet()) {
            prestamos.addAll(
                    db.get(id).stream()
                    .filter(prestamo
                            -> prestamo.getLibro().isbn().equalsIgnoreCase(isbn)).toList()
            );
        }
        return prestamos;
    }

    public List<Prestamo> obtenerPrestamosActivosDelUsuario(String id) {
        if (!db.containsKey(id)) return List.of();
        return db.get(id).stream()
                .filter(Prestamo::estaActivo)
                .toList();
    }

    public boolean tieneElUsuarioPrestamosActivos(String id) {
        return obtenerPrestamosDelUsuario(id).stream()
                .anyMatch(Prestamo::estaActivo);
    }

    public boolean tieneElUsuarioPrestamosConRetraso(String id, LocalDate fecha) {
        return obtenerPrestamosDelUsuario(id).stream()
                .anyMatch(prestamo -> prestamo.estaRetrasado(fecha));
    }

    public boolean tieneElUsuarioPrestamoActivoDeEsteLibro(String id, String isbn) {
        if (!db.containsKey(id)) return false;
        return db.get(id).stream()
                .anyMatch(prestamo
                        -> prestamo.getLibro().isbn().equalsIgnoreCase(isbn)
                        && prestamo.estaActivo());
    }

    public boolean tieneElLibroPrestamosActivos(String isbn) {
        return obtenerPrestamosDeLibro(isbn).stream()
                .anyMatch(Prestamo::estaActivo);
    }

    public List<Prestamo> obtenerPrestamosDelUsuario(String id) {
        return (db.containsKey(id)) ? db.get(id).stream().toList() : List.of();
    }

    public boolean existe(Prestamo prestamo) {
        String id = prestamo.getUsuario().id();
        return db.containsKey(id)
                && db.get(id).contains(prestamo);
    }

    private void validarExistenciaDePrestamo(Prestamo prestamo) {
        String id = prestamo.getUsuario().id();
        if ((!db.containsKey(id))
                || (!db.get(id).contains(prestamo))) {
            throw new RuntimeException("El prestamo no existe en la BD");
        }
    }

    private void validarNoExistenciaDePrestamo(Prestamo prestamo) {
        String id = prestamo.getUsuario().id();
        if ((db.containsKey(id))
                && (db.get(id).contains(prestamo))) {
            throw new RuntimeException("El prestamo ya existe en la BD");
        }
    }

    private void validarExistenciaDePrestamosAlUsuario(String id) {
        if (!db.containsKey(id)) {
            throw new RuntimeException("No hay prestamos registrados para el usuario dado");
        }
    }
}

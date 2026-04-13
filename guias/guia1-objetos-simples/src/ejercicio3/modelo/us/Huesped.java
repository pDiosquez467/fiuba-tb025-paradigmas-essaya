package ejercicio3.modelo.us;

public class Huesped extends Usuario {

    private final int numeroHabitacion;

    public Huesped(
            String id,
            String nombre,
            String credencial,
            int numeroHabitacion
    ) {
        super(id, nombre, credencial);
        this.numeroHabitacion = numeroHabitacion;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    @Override
    public boolean puedeCerrarSistema() {
        return false;
    }
}

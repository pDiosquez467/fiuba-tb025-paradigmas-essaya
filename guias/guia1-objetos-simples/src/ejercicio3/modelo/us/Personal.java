package ejercicio3.modelo.us;

public class Personal extends Usuario {
    private final Cargo cargo;

    public Personal(
            String id,
            String nombre,
            String credencial,
            Cargo cargo
    ) {
        super(id, nombre, credencial);
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }
}

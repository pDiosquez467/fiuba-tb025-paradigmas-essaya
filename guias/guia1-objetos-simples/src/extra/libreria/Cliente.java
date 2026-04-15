package extra.libreria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {

    private final String id;
    private final String nombre;
    private final String email;
    private final String direccion;
    private final List<Compra> compras;
    private final List<Suscripcion> suscripciones;

    public Cliente(String id, String nombre, String email, String direccion) {
        this.id            = id;
        this.nombre        = nombre;
        this.email         = email;
        this.direccion     = direccion;
        this.compras       = new ArrayList<>();
        this.suscripciones = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Compra> getCompras() {
        return List.copyOf(compras);
    }

    public List<Suscripcion> getSuscripciones() {
        return List.copyOf(suscripciones);
    }
}

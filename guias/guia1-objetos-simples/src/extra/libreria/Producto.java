package extra.libreria;

public abstract class Producto {

    private final double precio;

    public Producto(double precio) {
        this.precio = precio;
    }

    public abstract double obtenerPrecio();

    public double obtenerPrecioBase() {
        return precio;
    }
}

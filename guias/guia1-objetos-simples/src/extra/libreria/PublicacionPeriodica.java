package extra.libreria;

public class PublicacionPeriodica extends Producto {

    private final int periodicidadMensual;

    public PublicacionPeriodica(double precio, int periodicidadMensual) {
        super(precio);
        this.periodicidadMensual = periodicidadMensual;
    }

    public int obtenerPeriodicidad() {
        return periodicidadMensual;
    }

    @Override
    public double obtenerPrecio() {
        // Devuelve el precio del ejemplar en el mes completo.
        return obtenerPrecioBase() * periodicidadMensual;
    }
}

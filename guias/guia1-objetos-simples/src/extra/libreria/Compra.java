package extra.libreria;

public class Compra {

    private final Cliente cliente;
    private final Producto producto;
    private final Mes mes;

    public Compra(Cliente cliente, Producto producto, Mes mes) {
        this.cliente = cliente;
        this.producto = producto;
        this.mes = mes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public Mes getMes() {
        return mes;
    }
}

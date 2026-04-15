package extra.libreria;

public class Suscripcion {

    private final Cliente cliente;
    private final PublicacionPeriodica publicacionPeriodica;
    private final ModalidadSuscripcion modalidad;

    public Suscripcion(Cliente cliente, PublicacionPeriodica publicacionPeriodica, ModalidadSuscripcion modalidad) {
        this.cliente = cliente;
        this.publicacionPeriodica = publicacionPeriodica;
        this.modalidad = modalidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public PublicacionPeriodica getPublicacionPeriodica() {
        return publicacionPeriodica;
    }

    public ModalidadSuscripcion getModalidad() {
        return modalidad;
    }
}

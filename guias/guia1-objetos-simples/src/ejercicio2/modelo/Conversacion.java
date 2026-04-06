package ejercicio2.modelo;

import ejercicio2.modelo.validaciones.Validaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Conversacion {

    private final String id;
    private final Usuario participanteA;
    private final Usuario participanteB;
    private final List<Mensaje> historial;

    public Conversacion(
            String id,
            Usuario participanteA,
            Usuario participanteB) {
        this.id            = id;
        this.participanteA = participanteA;
        this.participanteB = participanteB;
        this.historial = new ArrayList<>();
    }

    public void agregarMensaje(Mensaje mensaje) {
        Validaciones.validarNotNull(mensaje, "El mensaje no debe ser null.");
        validarParticipantesInvolucrados(mensaje);
        historial.add(mensaje);
    }

    public boolean participa(Usuario usuario) {
        return Objects.equals(participanteA, usuario)
                || Objects.equals(participanteB, usuario);
    }

    public boolean involucra(Usuario usuario, Usuario otro) {
        return !Objects.equals(usuario, otro)
                && participa(usuario)
                && participa(otro);
    }

    public String getId() {
        return id;
    }

    public Usuario getParticipanteA() {
        return participanteA;
    }

    public Usuario getParticipanteB() {
        return participanteB;
    }

    public List<Mensaje> getHistorial() {
        return List.copyOf(historial);
    }

    private void validarParticipantesInvolucrados(Mensaje mensaje) {
        if (!involucra(mensaje.remitente(), mensaje.destinatario())) {
            throw new RuntimeException("El mensaje no corresponde a los participantes de esta conversación.");
        }
    }
}

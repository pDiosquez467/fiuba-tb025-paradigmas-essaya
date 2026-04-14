package extra.dibujando;

public class Compas implements Guardable {

    public Compas() {
    }

    public Circulo dibujarCirculoConRadio(Integer radio) {
        // Dibujando un círculo de radio 'radio'
        if (radio <= 0) throw new RuntimeException("El radio del círculo debe ser mayor que cero.");
        return new Circulo(
                radio
        );
    }
}

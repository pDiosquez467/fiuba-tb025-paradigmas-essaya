package extra.dibujando;

public class Pincel implements Guardable {

    private Color colorActual;

    public Pincel() {
    }

    public void seleccionarColor(Color color) {
        this.colorActual = color;
    }

    public void pintar(Coloreable coloreable) {
        if (!estaCargado()) {
            throw new RuntimeException("Asegúrese de seleccionar un color");
        }
        coloreable.colorearse(colorActual);
    }

    public boolean estaCargado() {
        return colorActual != null;
    }

}

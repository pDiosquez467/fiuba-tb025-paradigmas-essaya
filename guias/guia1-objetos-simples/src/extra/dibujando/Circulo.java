package extra.dibujando;

public class Circulo implements FiguraGeometrica, Coloreable {

    private final Integer radio;
    private Color color;

    public Circulo(Integer radio) {
        this.radio = radio;
    }

    public Circulo(Integer radio, Color color) {
        this(radio);
        this.color = color;
    }

    public Integer getRadio() {
        return radio;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Integer calcularSuperficie() {
        return (int) (Math.PI * radio * radio);
    }

    @Override
    public void colorearse(Color color) {
        this.color = color;
    }

    public boolean estaColoreado() {
        return this.color != null;
    }
}

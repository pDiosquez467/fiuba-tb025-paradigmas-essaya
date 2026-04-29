package extra.instrumentos;

public abstract class InstrumentoDeCuerda extends Instrumento implements Afinable {

    private final int cantidadDeCuerdas;

    public InstrumentoDeCuerda(Material material, int cantidadDeCuerdas) {
        super(material);
        this.cantidadDeCuerdas = cantidadDeCuerdas;
    }

    public int getCantidadDeCuerdas() {
        return cantidadDeCuerdas;
    }
}

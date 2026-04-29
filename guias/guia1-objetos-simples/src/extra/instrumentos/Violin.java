package extra.instrumentos;

public class Violin extends InstrumentoDeCuerda {

    private final static int CANTIDAD_CUERDAS_DEFAULT = 4;

    public Violin(Material material, int cantidadDeCuerdas) {
        super(material, cantidadDeCuerdas);
    }

    public Violin(Material material) {
        this(material, CANTIDAD_CUERDAS_DEFAULT);
    }

    @Override
    public void tocar() {
        // Tocando...
    }

    @Override
    public void afinar() {
        // Afinando...
    }
}

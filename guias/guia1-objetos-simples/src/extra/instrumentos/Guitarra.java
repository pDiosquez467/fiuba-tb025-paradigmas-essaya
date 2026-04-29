package extra.instrumentos;

public class Guitarra extends InstrumentoDeCuerda {

    private final static int CANTIDAD_CUERDAS_DEFAULT = 6;

    public Guitarra(Material material, int cantidadDeCuerdas) {
        super(material, cantidadDeCuerdas);
    }

    public Guitarra(Material material) {
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

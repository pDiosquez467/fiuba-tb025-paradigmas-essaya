package extra.instrumentos;

public abstract class Instrumento implements Tocable {
    private final Material material;

    public Instrumento(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}

package ejercicio3;

public class Docente extends Persona{

    private final String legajo;

    public Docente(String dni, String nombre, String legajo) {
        super(dni, nombre);
        this.legajo = legajo;
    }

    public String getLegajo() {
        return legajo;
    }

    @Override
    public String toString() {
        return super.toString() + "Legajo: " + this.legajo;
    }
}

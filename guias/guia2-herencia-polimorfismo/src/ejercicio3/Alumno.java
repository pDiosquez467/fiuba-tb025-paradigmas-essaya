package ejercicio3;

public class Alumno extends Persona{

    private final String padron;

    public Alumno(String dni, String nombre, String padron) {
        super(dni, nombre);
        this.padron = padron;
    }

    public String getPadron() {
        return padron;
    }

    @Override
    public String toString() {
        return super.toString() + "Legajo: " + this.padron;
    }
}

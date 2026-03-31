package ejercicio6;

public class FormatoFechaNumerico implements FormatoFecha {
    @Override
    public String convertir(Fecha fecha) {
        return fecha.getMes() + "/" + fecha.getDia() + "/" + fecha.getAnio();
    }
}

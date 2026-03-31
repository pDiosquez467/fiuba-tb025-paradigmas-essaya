package ejercicio6;

public class FormatoFechaTexto implements FormatoFecha {
    @Override
    public String convertir(Fecha fecha) {
        String mesString = switch (fecha.getMes()) {
            case 1  -> "Enero";
            case 2  -> "Febrero";
            case 3  -> "Marzo";
            case 4  -> "Abril";
            case 5  -> "Mayo";
            case 6  -> "Junio";
            case 7  -> "Julio";
            case 8  -> "Agosto";
            case 9  -> "Septiembre";
            case 10 -> "Octubre";
            case 11 -> "Noviembre";
            case 12 -> "Diciembre";
            default -> throw new IllegalStateException("Valor no esperado: " + fecha.getMes());
        };
        return mesString + " " + fecha.getDia() + ", " + fecha.getAnio();
    }
}

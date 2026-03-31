package ejercicio6;

public class Fecha {

    private int dia;
    private int mes;
    private final int anio;
    private FormatoFecha formato
            = new FormatoFechaNumerico();

    public Fecha(int dia, int mes, int anio) {
        Validaciones.validarNumeroEntreInclusive(dia, 1, 31,
                "El día debe estar entre 1 y 31.");
        Validaciones.validarNumeroEntreInclusive(mes, 1, 12,
                "El mes debe estar entre 1 y 12.");
        Validaciones.validarNumeroNoNegativo(anio,
                "El año no puede ser negativo.");
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Fecha(String mesString, int dia, int anio) {
        Validaciones.validarNotBlank(mesString, "Mes");
        Validaciones.validarNumeroEntreInclusive(dia, 1, 31, "Día");
        Validaciones.validarNumeroNoNegativo(anio, "Año");
        this.mes  = convertirMes(mesString);
        this.dia  = dia;
        this.anio = anio;
    }

    public Fecha(int diasEnAnio, int anio) {
        Validaciones.validarNumeroNoNegativo(diasEnAnio, "Día");
        Validaciones.validarNumeroNoNegativo(anio, "Año");
        this.anio = anio;
        int[] diasPorMes = {0, 31, 59, 90, 120, 151, 181, 212, 243,
                273, 304, 334};

        for (int i = 1; i <= 12; i++) {
            if (diasEnAnio <= diasPorMes[i]) {
                this.mes = i;
                this.dia = diasEnAnio - diasPorMes[i - 1];
                break;
            }
        }
    }

    public void imprimir() {
        System.out.println(convertirAString());
    }

    private String convertirAString() {
        return formato.convertir(this);
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public FormatoFecha getFormato() {
        return formato;
    }

    public void setFormato(FormatoFecha formato) {
        Validaciones.validarNotNull(formato, "El formato de la fecha no debe ser null.");
        this.formato = formato;
    }

    private int convertirMes(String mesString) {
        return switch (mesString.toUpperCase()) {
            case "ENERO"      -> 1;
            case "FEBRERO"    -> 2;
            case "MARZO"      -> 3;
            case "ABRIL"      -> 4;
            case "MAYO"       -> 5;
            case "JUNIO"      -> 6;
            case "JULIO"      -> 7;
            case "AGOSTO"     -> 8;
            case "SEPTIEMBRE" -> 9;
            case "OCTUBRE"    -> 10;
            case "NOVIEMBRE"  -> 11;
            case "DICIEMBRE"  -> 12;
            default -> throw new IllegalStateException("Valor no esperado: " + mesString);
        };
    }
}

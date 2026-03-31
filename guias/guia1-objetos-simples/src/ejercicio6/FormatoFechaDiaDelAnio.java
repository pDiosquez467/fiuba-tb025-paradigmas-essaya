package ejercicio6;

public class FormatoFechaDiaDelAnio implements FormatoFecha {
    @Override
    public String convertir(Fecha fecha) {
        int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31,
                30, 31, 30, 31};
        int diaDelAnio = fecha.getDia();
        for (int i = 1; i < fecha.getMes(); i++) {
            diaDelAnio += diasPorMes[i];
        }
        return diaDelAnio + " " + fecha.getAnio();
    }
}

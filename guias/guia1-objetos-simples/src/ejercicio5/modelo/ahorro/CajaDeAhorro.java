package ejercicio5.modelo.ahorro;

import ejercicio5.modelo.validaciones.Validaciones;

public class CajaDeAhorro {

    private final EstrategiaDeInteres estrategiaDeInteres;
    private final double tasaDeInteres;

    public CajaDeAhorro(
            EstrategiaDeInteres estrategiaDeInteres,
            double tasaDeInteres) {
        Validaciones.validarNotNull(estrategiaDeInteres, "estrategiaDeInterés");
        Validaciones.validarNumeroPositivo(tasaDeInteres, "tasaDeInterés");
        this.estrategiaDeInteres = estrategiaDeInteres;
        this.tasaDeInteres = tasaDeInteres;
    }

    public double calcularSaldoFinal(double monto, double meses) {
        return estrategiaDeInteres.calcularSaldoFinal(monto, tasaDeInteres, meses);
    }
}

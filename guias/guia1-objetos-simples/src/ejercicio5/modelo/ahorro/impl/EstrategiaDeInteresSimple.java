package ejercicio5.modelo.ahorro.impl;

import ejercicio5.modelo.ahorro.EstrategiaDeInteres;

public class EstrategiaDeInteresSimple implements EstrategiaDeInteres {
    @Override
    public double calcularSaldoFinal(double montoInicial, double tasaDeInteres, double meses) {
        return montoInicial * (1 + tasaDeInteres * meses);
    }
}

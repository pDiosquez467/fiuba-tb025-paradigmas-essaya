package ejercicio5.modelo.ahorro.impl;

import ejercicio5.modelo.ahorro.EstrategiaDeInteres;

public class EstrategiaDeInteresCompuesto implements EstrategiaDeInteres {
    @Override
    public double calcularSaldoFinal(double montoInicial, double tasaDeInteres, double meses) {
        return montoInicial * Math.pow(1 + tasaDeInteres, meses);
    }
}

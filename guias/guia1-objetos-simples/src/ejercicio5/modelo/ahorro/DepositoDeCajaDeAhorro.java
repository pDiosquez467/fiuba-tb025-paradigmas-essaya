package ejercicio5.modelo.ahorro;

import ejercicio5.modelo.Cuenta;
import ejercicio5.modelo.Usuario;

import java.time.LocalDate;
import java.util.Objects;

public record DepositoDeCajaDeAhorro(String id,
                                     Usuario usuario,
                                     Cuenta cuenta,
                                     CajaDeAhorro cajaDeAhorro,
                                     double montoInicial,
                                     double meses,
                                     LocalDate fechaDeInicio) {

    public double calcularSaldoFinal() {
        return cajaDeAhorro.calcularSaldoFinal(montoInicial, meses);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DepositoDeCajaDeAhorro that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

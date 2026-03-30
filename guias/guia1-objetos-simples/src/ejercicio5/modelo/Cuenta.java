package ejercicio5.modelo;

import java.util.Objects;

public class Cuenta {

    private final String id;
    private double saldo;

    public Cuenta(String id, double saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public Cuenta(String id) {
        this(id,0);
    }

    public void acreditar(double saldo) {
        validarSaldoPositivo(saldo);
        this.saldo += saldo;
    }

    public void debitar(double saldo) {
        validarSaldoPositivo(saldo);
        validarSaldoSuficiente(saldo);
        this.saldo -= saldo;
    }

    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cuenta cuenta)) return false;
        return Objects.equals(id, cuenta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getId() {
        return id;
    }

    private void validarSaldoSuficiente(double saldo) {
        if (this.saldo - saldo < 0) {
            throw new RuntimeException("La cuenta no posee saldo suficiente para realizar el débito solicitado.");
        }
    }

    private void validarSaldoPositivo(double saldo) {
        if (saldo <= 0) {
            throw new RuntimeException("El monto de la operación debe ser mayor que cero.");
        }
    }
}
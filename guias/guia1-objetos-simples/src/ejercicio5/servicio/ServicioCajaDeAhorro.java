package ejercicio5.servicio;

import ejercicio5.modelo.Cuenta;
import ejercicio5.modelo.Usuario;
import ejercicio5.modelo.ahorro.CajaDeAhorro;
import ejercicio5.modelo.ahorro.DepositoDeCajaDeAhorro;
import ejercicio5.repositorio.Repositorio;
import ejercicio5.repositorio.impl.RepositorioCuenta;
import ejercicio5.repositorio.impl.RepositorioDepositoDeCajaDeAhorro;
import ejercicio5.repositorio.impl.RepositorioUsuario;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class ServicioCajaDeAhorro {

    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioCuenta repositorioCuenta;
    private final CajaDeAhorro cajaDeAhorro;
    private final RepositorioDepositoDeCajaDeAhorro repositorioDepositoDeCajaDeAhorro;

    public ServicioCajaDeAhorro(RepositorioUsuario repositorioUsuario,
                                RepositorioCuenta repositorioCuenta,
                                CajaDeAhorro cajaDeAhorro,
                                RepositorioDepositoDeCajaDeAhorro repositorioDepositoDeCajaDeAhorro) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioCuenta = repositorioCuenta;
        this.cajaDeAhorro = cajaDeAhorro;
        this.repositorioDepositoDeCajaDeAhorro = repositorioDepositoDeCajaDeAhorro;
    }

    public double calcularSaldoFinal(double monto, double meses) {
        return cajaDeAhorro.calcularSaldoFinal(monto, meses);
    }

    // @Transactional
    public DepositoDeCajaDeAhorro crearDeposito(
            String idUsuario,
            String idCuenta,
            double monto,
            double meses,
            LocalDate fechaDeInicio) {
        Usuario us = obtenerUsuarioExistente(idUsuario);
        Cuenta cuenta = obtenerCuentaExistente(idCuenta);
        validarCuentaPertenecienteAlUsuario(us, cuenta);
        cuenta.debitar(monto);

        DepositoDeCajaDeAhorro deposito = new DepositoDeCajaDeAhorro(
                UUID.randomUUID().toString(),
                us,
                cuenta,
                cajaDeAhorro,
                monto,
                meses,
                fechaDeInicio
        );

        us.agregarDepositoDeCajaDeAhorro(deposito);
        repositorioDepositoDeCajaDeAhorro.agregar(deposito);
        return deposito;
    }

    private Usuario obtenerUsuarioExistente(String id) {
        return obtenerExistente(repositorioUsuario, id,"El usuario con el ID dado no existe en la BD");
    }

    private Cuenta obtenerCuentaExistente(String id) {
        return obtenerExistente(repositorioCuenta, id, "La cuenta con el ID dado no existe en la BD");
    }

    private <T> T obtenerExistente(Repositorio<T> repositorio,
                                   String id,
                                   String mensaje) {
        Optional<T> wrapper = repositorio.obtener(id);
        if (wrapper.isEmpty()) {
            throw new RuntimeException(mensaje);
        }
        return wrapper.get();
    }

    private void validarCuentaPertenecienteAlUsuario(Usuario usuario, Cuenta cuenta) {
        if (!usuario.esTitularDe(cuenta)) {
            throw new RuntimeException("La cuenta con el ID dado no pertenece al usuario.");
        }
    }

}

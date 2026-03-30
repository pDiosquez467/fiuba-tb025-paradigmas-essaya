package ejercicio5.servicio;

import ejercicio5.modelo.Cuenta;
import ejercicio5.modelo.Transferencia;
import ejercicio5.modelo.fabrica.FabricaTransferencia;
import ejercicio5.repositorio.impl.RepositorioCuenta;
import ejercicio5.repositorio.impl.RepositorioTransferencia;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import java.time.LocalDate;

public class ServicioTransferencia {

    private final RepositorioTransferencia repositorioTransferencia;
    private final RepositorioCuenta repositorioCuenta;

    public ServicioTransferencia(
            RepositorioTransferencia repositorioTransferencia,
            RepositorioCuenta repositorioCuenta) {
        this.repositorioTransferencia = repositorioTransferencia;
        this.repositorioCuenta = repositorioCuenta;
    }

    public Transferencia transferir(
            String origenId,
            String destinoId,
            double monto,
            LocalDate fecha,
            String mensaje) {
        validarCuentasDiferentes(origenId, destinoId);
        Cuenta origen = obtenerCuentaExistente(origenId);
        Cuenta destino = obtenerCuentaExistente(destinoId);
        origen.debitar(monto);
        destino.acreditar(monto);
        Transferencia nuevaTransferencia = FabricaTransferencia.crear(
                UUID.randomUUID().toString(),
                origen,
                destino,
                monto,
                fecha,
                mensaje
        );
        repositorioTransferencia.agregar(nuevaTransferencia);
        return nuevaTransferencia;
    }


    private Cuenta obtenerCuentaExistente(String cuentaId) {
        Optional<Cuenta> cuenta = repositorioCuenta.obtener(cuentaId);
        if (cuenta.isEmpty()) {
            throw new RuntimeException("No existe una cuenta con el identificador proporcionado.");
        }
        return cuenta.get();
    }

    private void validarCuentasDiferentes(String id , String otro) {
        if (Objects.equals(id, otro)) {
            throw new RuntimeException("La cuenta de origen y la cuenta de destino deben ser diferentes.");
        }
    }

}

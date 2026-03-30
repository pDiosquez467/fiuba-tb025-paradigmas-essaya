package ejercicio5.repositorio.impl;

import ejercicio5.modelo.Transferencia;
import ejercicio5.repositorio.Repositorio;
import ejercicio5.repositorio.validaciones.Validaciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioTransferencia implements Repositorio<Transferencia> {

    private final Map<String, Transferencia> db;

    public RepositorioTransferencia() {
        this.db = new HashMap<>();
    }

    @Override
    public void agregar(Transferencia transferencia) {
        Validaciones.validarNoExistenciaEnLaBD(this, transferencia.id(), "La transferencia existe en la BD");
        db.put(transferencia.id(), transferencia);
    }

    @Override
    public void quitar(String id) {
        Validaciones.validarExistenciaEnLaBD(this, id, "La transferencia no existe en la BD");
        db.remove(id);
    }

    @Override
    public boolean existe(String id) {
        return db.containsKey(id);
    }

    @Override
    public Optional<Transferencia> obtener(String id) {
        return Optional.ofNullable(db.get(id));
    }
}
